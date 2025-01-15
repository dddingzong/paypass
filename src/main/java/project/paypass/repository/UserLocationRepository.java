package project.paypass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.paypass.domain.UserLocation;

@Repository
public interface UserLocationRepository extends JpaRepository<UserLocation, Long> {
}
