package project.paypass.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import project.paypass.domain.GeofenceLocation;
import project.paypass.repository.GeofenceLocationRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class GeofenceService {

    private final GeofenceLocationRepository geofenceLocationRepository;
    // 알고리즘 관련
    private final BasicAlgorithmService basicAlgorithmService;
    private final AverageTimeAlgorithmService averageTimeAlgorithmService;
    private final DuplicateDeleteAlgorithm duplicateDeleteAlgorithm;
    private final LogService logService;


    @Transactional
    @Scheduled(cron = "0 * * * * *")
    public void algorithmTrigger(){
        log.info("algorithmTrigger가 작동합니다.");
        // 모든 유저에 대한 가장 최근 데이터를 가져오기
        List<GeofenceLocation> recentGeofenceLocations = geofenceLocationRepository.findMostRecentGeofenceLocationByFenceInTime();
        // fenceInTime이 현재 시간과 30분 차이나는지 확인
        LocalDateTime standardTime = LocalDateTime.now().minusMinutes(30);
        log.info("현재 시간에 30분을 차감하겠습니다. 차감 시간: " + standardTime);

        for (GeofenceLocation geofenceLocation : recentGeofenceLocations) {
            LocalDateTime userFenceInTime = geofenceLocation.getFenceInTime();
            // 30분 차이나면 메인 로직 실행
            if (standardTime.isAfter(userFenceInTime)){
                String mainId = geofenceLocation.getMainId();
                algorithmStart(mainId);
            }
        }
    }

    @Transactional
    public void algorithmStart(String mainId){ // 일단 dto를 사용하지 않고 Map으로 해결
        log.info("메인 알고리즘을 실행합니다. mainId = " + mainId);
        Map<List<GeofenceLocation>, List<String>> resultMap = startAlgorithm(mainId);

        // 이후 데이터 저장
        saveLogData(mainId, resultMap);

        // geofenceLocation 데이터 삭제
        deleteGeofenceLocations(mainId);
        log.info(mainId + "의 메인 알고리즘 실행 성공");
    }

    @Transactional
    private void saveLogData(String mainId,Map<List<GeofenceLocation>, List<String>> resultMap){
        logService.saveLogData(mainId, resultMap);
        log.info(mainId + "의 log data 저장 성공");
    }

    @Transactional
    private void deleteGeofenceLocations(String mainId){
        geofenceLocationRepository.deleteByMainId(mainId);
        log.info(mainId + "의 geofenceLocation 데이터 삭제 성공");
    }


    @Transactional
    public GeofenceLocation createGeofenceLocation(String mainId, Long stationNumber, String busInfo){
        return new GeofenceLocation(mainId,stationNumber,busInfo);
    }

    @Transactional
    public void save(GeofenceLocation geofenceLocation){
        geofenceLocationRepository.save(geofenceLocation);
    }

    @Transactional
    public List<GeofenceLocation> findByMainIdAndStationNumber(String mainId, Long stationNumber){
        return geofenceLocationRepository.findByMainIdAndStationNumber(mainId, stationNumber);
    }

    @Transactional
    public void userFenceOut(GeofenceLocation geofenceLocation) {
        geofenceLocation.userFenceOut();
    }

    @Transactional
    public boolean fenceOutTimeIsNull(GeofenceLocation geofenceLocation){
        return geofenceLocation.fenceOutTimeIsNull();
    }

    @Transactional
    public Map<List<GeofenceLocation>, List<String>> startAlgorithm(String mainId){
        // mainId로 geofenceLocation 조회
        List<GeofenceLocation> geofenceLocations = geofenceLocationRepository.findByMainId(mainId);

        Map<List<GeofenceLocation>, List<String>> resultMap = mainAlgorithm(geofenceLocations);

        return resultMap;
    }

    @Transactional
    public Map<List<GeofenceLocation>, List<String>> mainAlgorithm(List<GeofenceLocation> geofenceLocations){
        // 알고리즘 실행
        Map<String, List<Long>> basicMap = basicAlgorithmService.algorithmStart(geofenceLocations);
        Map<String, List<Long>> averageTimeMap = averageTimeAlgorithmService.algorithmStart(basicMap, geofenceLocations);
        Map<List<GeofenceLocation>, List<String>> resultMap = duplicateDeleteAlgorithm.algorithmStart(averageTimeMap, geofenceLocations);

        return resultMap;
    }

    @Transactional
    public List<GeofenceLocation> findByMainId(String mainId){
        return geofenceLocationRepository.findByMainId(mainId);
    }

    @Transactional
    public List<GeofenceLocation> findAll(){
        return geofenceLocationRepository.findAll();
    }

}
