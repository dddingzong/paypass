package project.paypass.utils;

import project.paypass.domain.GeofenceLocation;

import java.util.*;

import static project.paypass.utils.geofenceLocationConstants.*;


public class Constants {
    // 1번 테스트 케이스
    public static final List<GeofenceLocation> testCase1_GeofenceLocation = new ArrayList<>(List.of(
            test1_geofenceLocation1,
            test1_geofenceLocation2,
            test1_geofenceLocation3,
            test1_geofenceLocation4,
            test1_geofenceLocation5,
            test1_geofenceLocation6,
            test1_geofenceLocation7,
            test1_geofenceLocation8,
            test1_geofenceLocation9,
            test1_geofenceLocation10
    ));

    public static final Map<String, List<Long>> testCase1_basicAlgorithm = new TreeMap<>(Map.ofEntries(
            Map.entry("100100057_1", List.of(27L, 28L, 29L)),
            Map.entry("100100057_2", List.of(31L, 32L, 33L, 34L)),
            Map.entry("100100057_3", List.of(59L, 60L)),
            Map.entry("100100083_1", List.of(48L, 49L, 50L)),
            Map.entry("100100093_1", List.of(46L, 47L, 48L)),
            Map.entry("100100093_2", List.of(50L, 51L, 52L, 53L)),
            Map.entry("100100093_3", List.of(29L, 30L)),
            Map.entry("100100234_1", List.of(61L, 62L, 63L)),
            Map.entry("100100237_1", List.of(96L, 97L, 98L)),
            Map.entry("100100260_1", List.of(46L, 47L)),
            Map.entry("100100392_1", List.of(60L, 61L, 62L)),
            Map.entry("100100454_1", List.of(37L, 38L, 39L)),
            Map.entry("100100454_2", List.of(41L, 42L, 43L, 44L)),
            Map.entry("100100454_3", List.of(53L, 54L)),
            Map.entry("100100579_1", List.of(43L, 44L, 45L)),
            Map.entry("113000002_1", List.of(56L, 57L, 58L)),
            Map.entry("113000002_2", List.of(60L, 61L)),
            Map.entry("114000002_1", List.of(80L, 81L, 82L)),
            Map.entry("114000002_2", List.of(84L, 85L, 86L, 87L)),
            Map.entry("114000002_3", List.of(33L, 34L))
    ));

    public static final Map<String, List<Long>> testCase1_AverageAlgorithm = new TreeMap<>(Map.ofEntries(
            Map.entry("100100057_1", List.of(27L, 28L, 29L)),
            Map.entry("100100057_2", List.of(31L, 32L, 33L, 34L)),
            Map.entry("100100083_1", List.of(48L, 49L, 50L)),
            Map.entry("100100093_1", List.of(46L, 47L, 48L)),
            Map.entry("100100093_2", List.of(50L, 51L, 52L, 53L)),
            Map.entry("100100234_1", List.of(61L, 62L, 63L)),
            Map.entry("100100237_1", List.of(96L, 97L, 98L)),
            Map.entry("100100260_1", List.of(46L, 47L)),
            Map.entry("100100392_1", List.of(60L, 61L, 62L)),
            Map.entry("100100454_1", List.of(37L, 38L, 39L)),
            Map.entry("100100454_2", List.of(41L, 42L, 43L, 44L)),
            Map.entry("100100579_1", List.of(43L, 44L, 45L)),
            Map.entry("113000002_1", List.of(56L, 57L, 58L)),
            Map.entry("113000002_2", List.of(60L, 61L)),
            Map.entry("114000002_1", List.of(80L, 81L, 82L)),
            Map.entry("114000002_2", List.of(84L, 85L, 86L, 87L))
    ));

    public static final Map<List<GeofenceLocation>, List<String>> testCase1_resultMap = Map.ofEntries(
            Map.entry(List.of(test1_geofenceLocation1, test1_geofenceLocation2, test1_geofenceLocation3),
                    List.of("100100057", "100100083", "100100093", "100100234", "100100237", "100100392", "100100454", "100100579", "113000002", "114000002")),
            Map.entry(List.of(test1_geofenceLocation5, test1_geofenceLocation6, test1_geofenceLocation7, test1_geofenceLocation8),
                    List.of("100100057", "100100093", "100100454", "114000002"))
            );

    // 2번 테스트 케이스

    public static final List<GeofenceLocation> testCase2_GeofenceLocation = new ArrayList<>(List.of(
            test2_geofenceLocation1,
            test2_geofenceLocation2,
            test2_geofenceLocation3,
            test2_geofenceLocation4,
            test2_geofenceLocation5,
            test2_geofenceLocation6,
            test2_geofenceLocation7,
            test2_geofenceLocation8,
            test2_geofenceLocation9,
            test2_geofenceLocation10,
            test2_geofenceLocation11,
            test2_geofenceLocation12,
            test2_geofenceLocation13,
            test2_geofenceLocation14,
            test2_geofenceLocation15,
            test2_geofenceLocation16
    ));

    public static final Map<String, List<Long>> testCase2_basicAlgorithm = new TreeMap<>(Map.ofEntries(
            Map.entry("100100298_1", List.of(26L, 27L, 28L)),
            Map.entry("100100298_2", List.of(35L, 36L, 37L)),
            Map.entry("100100298_3", List.of(54L, 55L)),
            Map.entry("100100298_4", List.of(61L, 62L, 63L)),
            Map.entry("100100312_1", List.of(62L, 63L, 64L)),
            Map.entry("100100313_1", List.of(50L, 51L, 52L)),
            Map.entry("100100451_1", List.of(12L, 13L)),
            Map.entry("100100453_1", List.of(76L, 77L)),
            Map.entry("100100453_2", List.of(68L, 69L, 70L)),
            Map.entry("100100601_1", List.of(43L, 44L)),
            Map.entry("116000002_1", List.of(38L, 39L))
    ));

    public static final Map<List<GeofenceLocation>, List<String>> testCase2_resultMap = Map.ofEntries(
            Map.entry(List.of(test2_geofenceLocation1, test2_geofenceLocation2, test2_geofenceLocation3),
                    List.of("100100298")),
            Map.entry(List.of(test2_geofenceLocation4, test2_geofenceLocation5, test2_geofenceLocation6),
                    List.of("100100298", "100100453")),
            Map.entry(List.of(test2_geofenceLocation7, test2_geofenceLocation8),
                    List.of("100100298")),
            Map.entry(List.of(test2_geofenceLocation9, test2_geofenceLocation10, test2_geofenceLocation11),
                    List.of("100100298", "100100312", "100100313"))
    );
}
