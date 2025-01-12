package project.paypass.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class userLocation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(nullable = false)
    private String main_id;

    @Column(nullable = false)
    private double longitude;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private LocalDateTime currentTime;

    public userLocation(String main_id, double longitude, double latitude) {
        this.main_id = main_id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.currentTime = LocalDateTime.now();
    }
}
