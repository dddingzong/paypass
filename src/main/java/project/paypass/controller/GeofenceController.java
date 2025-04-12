package project.paypass.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.paypass.domain.GeofenceLocation;
import project.paypass.domain.dto.LogDto;
import project.paypass.domain.dto.UserGeofenceDto;
import project.paypass.service.GeofenceService;
import project.paypass.service.LogService;
import project.paypass.service.StationService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class GeofenceController {

    private final StationService stationService;
    private final GeofenceService geofenceService;
    private final LogService logService;

    @PostMapping("/userFenceIn")
    public ResponseEntity<Void> userGeofenceIn(@RequestBody UserGeofenceDto userGeofenceDto){
        log.info("****************************************************************");
        log.info("사용자가 geofence에 접근했기 때문에 userGeofenceIn method를 실행합니다.");
        log.info("****************************************************************");

        // mainId랑 stationNumber 받기
        String mainId = userGeofenceDto.getMainId();
        Long stationNumber = userGeofenceDto.getStationNumber();

        // stationNumber 활용해서 busInfo 가져오기
        String busInfo = stationService.findBusInfoByStationNumber(stationNumber);

        // geofenceLocation entity 생성
        GeofenceLocation geofenceLocation = geofenceService.createGeofenceLocation(mainId, stationNumber, busInfo);
        geofenceService.save(geofenceLocation);
        log.info("geofenceLocation 데이터를 생성 후 저장했습니다.");
        log.info("geofenceLocation: {}", geofenceLocation);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/userFenceOut")
    public ResponseEntity<Void> userGeofenceOut(@RequestBody UserGeofenceDto userGeofenceDto){
        log.info("****************************************************************");
        log.info("사용자가 geofence에서 이탈했기 때문에 userGeofenceOut method를 실행합니다.");
        log.info("****************************************************************");

        // mainId랑 stationNumber 받기
        String mainId = userGeofenceDto.getMainId();
        Long stationNumber = userGeofenceDto.getStationNumber();

        // mainId와 stationNumber를 활용해서 해당 entity 가져오기
        List<GeofenceLocation> userOutStations = geofenceService.findByMainIdAndStationNumber(mainId, stationNumber);

        // entity가 존재하지 않으면 2분 전 fenceIn을 한다고 가정한다.
        if (userOutStations.isEmpty()){
            updateFenceOutTimeNoEntity(mainId, stationNumber);
        }

        // entity가 하나라면 해당 entity의 fenceOutTime 추가
        if (userOutStations.size() == 1){
            updateFenceOutTimeOneEntity(userOutStations);
        }

        // 조회하였을 때 두개 이상인 경우에는 userFenceOut이 null 값인 엔티티 찾기
        if (userOutStations.size() > 1){
            updateFenceOutTimeEntities(userOutStations);
        }

        return ResponseEntity.ok().build();
    }

    @PostMapping("/getCurrentGeofence")
    public ResponseEntity<List<String>> checkCurrentGeofence(@RequestBody Map<String, String> request){
        String mainId = request.get("mainId");

        // mainId를 활용해서 fenceInTime은 존재하고 fenceOutTime은 null 값인 stationNumber 확인하기
        List<GeofenceLocation> geofenceLocations = geofenceService.findByMainId(mainId);

        List<String> activeStationNumbers = geofenceLocations.stream()
                .filter(geofenceLocation -> geofenceLocation.getFenceOutTime() == null) // 아직 나가지 않은 상태만 필터링
                .map(geofenceLocation -> String.valueOf(geofenceLocation.getStationNumber())) // stationNumber를 문자열로 변환
                .toList();

        return ResponseEntity.ok(activeStationNumbers);
    }

    private void updateFenceOutTimeNoEntity(String mainId, Long stationNumber){
        log.info("stationNumber에 부합하는 entity가 존재하지 않기 때문에 2분 전 fenceIn을 가정하고 entity를 생성합니다.");

        // stationNumber 활용해서 busInfo 가져오기
        String busInfo = stationService.findBusInfoByStationNumber(stationNumber);

        // geofenceLocation entity 생성
        GeofenceLocation geofenceLocation = geofenceService.userFenceOutWithoutEntity(mainId, stationNumber, busInfo, LocalDateTime.now());

        geofenceService.save(geofenceLocation);
        log.info("geofenceLocation 데이터를 생성 후 저장했습니다. (entity 없이 fenceOut한 경우)");
        log.info("geofenceLocation: {}", geofenceLocation);
    }

    private void updateFenceOutTimeOneEntity(List<GeofenceLocation> userOutStations){
        GeofenceLocation geofenceLocation = userOutStations.get(0);
        geofenceService.userFenceOut(geofenceLocation);
        log.info("하나의 entity를 발견하여 fenceOutTime을 추가합니다.");
    }

    private void updateFenceOutTimeEntities(List<GeofenceLocation> userOutStations) {
        userOutStations.stream()
                .filter(geofenceService::fenceOutTimeIsNull)
                .findFirst()
                .ifPresent(geofenceService::userFenceOut);
        log.info("여러 개의 entity 중 첫 번째 null fenceOutTime을 업데이트했습니다.");
    }

}
