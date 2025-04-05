package project.paypass.domain;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Entity
public class GeofenceLocation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String mainId;

    @Column(nullable = false)
    private LocalDateTime fenceInTime;

    private LocalDateTime fenceOutTime;

    @Column(nullable = false)
    private Long stationNumber;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String busInfo;


    public GeofenceLocation(String mainId, Long stationNumber, String busInfo) {
        this.mainId = mainId;
        this.fenceInTime = LocalDateTime.now();
        this.fenceOutTime = null;
        this.stationNumber = stationNumber;
        this.busInfo = busInfo;
    }

    public void userFenceOut(){
        this.fenceOutTime = LocalDateTime.now();
    }

    public boolean fenceOutTimeIsNull(){
        return fenceOutTime == null;
    }

    public LocalDateTime userFenceInTime(){
        return this.fenceInTime;
    }

    public String stationBusInfo(){
        return this.busInfo;
    }

    public Long geofenceLocationId(){
        return this.id;
    }

    public Long getStationNumber(){
        return this.stationNumber;
    }

    public LocalDateTime getFenceOutTime() {
        return this.fenceOutTime;
    }

    public LocalDateTime getFenceInTime() {
        return this.fenceInTime;
    }

    public static GeofenceLocation createGeofenceLocationEntityForTest(Long id, String mainId, LocalDateTime fenceInTime, LocalDateTime fenceOutTime, Long stationNumber, String busInfo){
        return new GeofenceLocation(id, mainId, fenceInTime, fenceOutTime, stationNumber, busInfo);
    }

    private GeofenceLocation(Long id, String mainId, LocalDateTime fenceInTime, LocalDateTime fenceOutTime, Long stationNumber, String busInfo) {
        this.id = id;
        this.mainId = mainId;
        this.fenceInTime = fenceInTime;
        this.fenceOutTime = fenceOutTime;
        this.stationNumber = stationNumber;
        this.busInfo = busInfo;
    }
}
