package project.paypass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.paypass.domain.GeofenceLocation;

public interface GeofenceLocationRepository extends JpaRepository<GeofenceLocation, Long> {
}
