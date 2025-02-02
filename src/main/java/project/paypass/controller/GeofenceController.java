package project.paypass.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.paypass.domain.GeofenceLocation;
import project.paypass.domain.dto.UserGeofenceInDto;
import project.paypass.service.GeofenceService;
import project.paypass.service.StationService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class GeofenceController {

    private final StationService stationService;
    private final GeofenceService geofenceService;

    @PostMapping("/userFenceIn")
    public ResponseEntity<Void> userGeofenceIn(@RequestBody UserGeofenceInDto userGeofenceInDto){
        log.info("사용자가 geofence에 접근했기 때문에 userGeofenceIn method를 실행합니다.");

        // mainId랑 stationNumber 받기
        String mainId = userGeofenceInDto.getMainId();
        Long stationNumber = userGeofenceInDto.getStationNumber();

        // stationNumber 활용해서 busInfo 가져오기
        String busInfo = stationService.findBusInfoByStationNumber(stationNumber);

        // geofenceLocation entity 생성
        GeofenceLocation geofenceLocation = geofenceService.createGeofenceLocation(mainId, stationNumber, busInfo);
        geofenceService.save(geofenceLocation);
        log.info("geofenceLocation 데이터를 생성 후 저장했습니다.");
        log.info("geofenceLocation: {}", geofenceLocation);

        return ResponseEntity.ok().build();
    }





}
