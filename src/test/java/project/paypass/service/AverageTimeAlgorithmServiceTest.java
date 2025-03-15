package project.paypass.service;

import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static project.paypass.utils.Constants.*;


@SpringBootTest
@Transactional
class AverageTimeAlgorithmServiceTest {
    // 평균 시간 알고리즘 작동 테스트

    @Autowired
    private AverageTimeAlgorithmService averageTimeAlgorithmService;

    @Test
    @DisplayName("테스트_데이터_1번_평균시간알고리즘")
    void algorithmStart() {
        // when
        Map<String, List<Long>> averageMap = averageTimeAlgorithmService.algorithmStart(testCase1_basicAlgorithm, testCase1_GeofenceLocation);
        // then
        Assertions.assertThat(averageMap).isEqualTo(testCase1_AverageAlgorithm);
    }
}