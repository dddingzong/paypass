package project.paypass.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.paypass.domain.GeofenceLocation;
import project.paypass.repository.GeofenceLocationRepository;

@Service
@RequiredArgsConstructor
public class GeofenceService {

    private final GeofenceLocationRepository geofenceLocationRepository;

    @Transactional
    public GeofenceLocation createGeofenceLocation(String mainId, Long stationNumber, String busInfo){
        return new GeofenceLocation(mainId,stationNumber,busInfo);
    }

    @Transactional
    public void save(GeofenceLocation geofenceLocation){
        geofenceLocationRepository.save(geofenceLocation);
    }

}
