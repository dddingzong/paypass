package project.paypass.service;

import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.paypass.domain.UserLocation;
import project.paypass.domain.dto.UserLocationDto;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class LocationWebSocketServiceTest {

    @Autowired
    private LocationWebSocketService locationWebSocketService;

    @Autowired
    private UserLocationService userLocationService;

    @Test
    @DisplayName("사용자위치_저장확인")
    void saveUserLocation() {
        // given
        String mainId = "example@test.com";
        double latitude = 10;
        double longitude = 20;
        UserLocationDto userLocationDto = new UserLocationDto(mainId, latitude, longitude);
        // when
        locationWebSocketService.saveUserLocation(userLocationDto);
        List<UserLocation> userLocations = userLocationService.findByMainId(mainId);
        userLocations.sort(Comparator.comparing(UserLocation::getTime).reversed());
        // then
        UserLocation userLocation = userLocations.get(0);
        Assertions.assertThat(userLocation.getLatitude()).isEqualTo(latitude);
        Assertions.assertThat(userLocation.getLongitude()).isEqualTo(longitude);
    }
}