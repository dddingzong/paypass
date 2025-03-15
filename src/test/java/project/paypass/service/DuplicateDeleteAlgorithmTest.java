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
class DuplicateDeleteAlgorithmTest {
    // 중복제거 알고리즘 작동 테스트

    @Autowired
    private DuplicateDeleteAlgorithm duplicateDeleteAlgorithm;

    @Test
    @DisplayName("테스트_데이터_1번_중복제거알고리즘")
    void algorithmStart() {
        // when
        Map<List<GeofenceLocation>, List<String>> resultMap = duplicateDeleteAlgorithm.algorithmStart(testCase1_AverageAlgorithm, testCase1_GeofenceLocation);
        // then
        Assertions.assertThat(resultMap).isEqualTo(testCase1_resultMap);
    }
}