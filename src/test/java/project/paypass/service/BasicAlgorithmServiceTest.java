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
class BasicAlgorithmServiceTest {
    // 기본 알고리즘 작동 테스트

    @Autowired
    private BasicAlgorithmService basicAlgorithmService;

    @Test
    @DisplayName("테스트_데이터_1번_기본알고리즘")
    void algorithmStart() {
        // then
        Map<String, List<Long>> busInfoMap = basicAlgorithmService.algorithmStart(testCase1_GeofenceLocation);
        // given
        Assertions.assertThat(busInfoMap).isEqualTo(testCase1_basicAlgorithm);
    }
}