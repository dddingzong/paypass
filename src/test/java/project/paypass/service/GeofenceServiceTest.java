package project.paypass.service;

import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.paypass.domain.GeofenceLocation;

import java.util.List;
import java.util.Map;

import static project.paypass.utils.Constants.*;


@SpringBootTest
@Transactional
class GeofenceServiceTest {
    // 사용자 탑승 여부 알고리즘 전체 테스트

    @Autowired
    private GeofenceService geofenceService;

    @Test
    @DisplayName("테스트_데이터_1번_전체알고리즘")
    void startAlgorithmTestOne() {
        // then
        Map<List<GeofenceLocation>, List<String>> resultMap = geofenceService.mainAlgorithm(testCase1_GeofenceLocation);
        // given
        Assertions.assertThat(resultMap).isEqualTo(testCase1_resultMap);
    }

    @Test
    @DisplayName("테스트_데이터_2번_전체알고리즘")
    void startAlgorithmTestTwo() {
        // then
        Map<List<GeofenceLocation>, List<String>> resultMap = geofenceService.mainAlgorithm(testCase2_GeofenceLocation);
        // given
        Assertions.assertThat(resultMap).isEqualTo(testCase2_resultMap);
    }
}