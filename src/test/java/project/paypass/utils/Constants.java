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


    public static final Map<String, List<Long>> testCase1_basicAlgorithm = Map.ofEntries(
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
    );

    public static final Map<String, List<Long>> testCase1_AverageAlgorithm = Map.ofEntries(
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
    );

    public static final Map<List<GeofenceLocation>, List<String>> testCase1_resultMap = Map.ofEntries(
            Map.entry(List.of(test1_geofenceLocation1, test1_geofenceLocation2, test1_geofenceLocation3),
                    List.of("100100057", "100100083", "100100093", "100100234", "100100237", "100100392", "100100454", "100100579", "113000002", "114000002")),
            Map.entry(List.of(test1_geofenceLocation5, test1_geofenceLocation6, test1_geofenceLocation7, test1_geofenceLocation8),
                    List.of("100100057", "100100093", "100100454", "114000002"))
            );
}
