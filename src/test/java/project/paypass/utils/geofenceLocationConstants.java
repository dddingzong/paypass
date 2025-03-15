package project.paypass.utils;

import project.paypass.domain.GeofenceLocation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class geofenceLocationConstants {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");

    public static final GeofenceLocation test1_geofenceLocation1 = GeofenceLocation.createGeofenceLocationEntityForTest(1L, "chungjongin@gmail.com",
            LocalDateTime.parse("2025-03-08 15:17:26.000000", formatter),
            LocalDateTime.parse("2025-03-08 15:18:26.000000", formatter),
            121000019L,
            "{100100057,27},{100100083,48},{100100093,46},{100100234,61},{100100237,96},{100100392,60},{100100454,37},{100100579,43},{111000016,122},{113000002,56},{114000002,80}"
    );

    public static final GeofenceLocation test1_geofenceLocation2 = GeofenceLocation.createGeofenceLocationEntityForTest(2L, "chungjongin@gmail.com",
            LocalDateTime.parse("2025-03-08 15:35:34.000000", formatter),
            LocalDateTime.parse("2025-03-08 15:36:34.000000", formatter),
            121000021L,
            "{100100057,28},{100100083,49},{100100093,47},{100100234,62},{100100237,97},{100100392,61},{100100454,38},{100100579,44},{113000002,57},{114000002,81}"
    );

    public static final GeofenceLocation test1_geofenceLocation3 = GeofenceLocation.createGeofenceLocationEntityForTest(3L, "chungjongin@gmail.com",
            LocalDateTime.parse("2025-03-08 15:47:38.000000", formatter),
            LocalDateTime.parse("2025-03-08 15:47:58.000000", formatter),
            121000023L,
            "{100100057,29},{100100083,50},{100100093,48},{100100234,63},{100100237,98},{100100392,62},{100100454,39},{100100579,45},{113000002,58},{114000002,82}"
    );

    public static final GeofenceLocation test1_geofenceLocation4 = GeofenceLocation.createGeofenceLocationEntityForTest(4L, "chungjongin@gmail.com",
            LocalDateTime.parse("2025-03-08 15:48:58.000000", formatter),
            LocalDateTime.parse("2025-03-08 15:49:58.000000", formatter),
            121000023L,
            "{100100057,29},{100100083,50},{100100093,48},{100100234,63},{100100237,98},{100100392,62},{100100454,39},{100100579,45},{113000002,58},{114000002,82}"
    );

    public static final GeofenceLocation test1_geofenceLocation5 = GeofenceLocation.createGeofenceLocationEntityForTest(5L, "chungjongin@gmail.com",
            LocalDateTime.parse("2025-03-08 15:57:43.000000", formatter),
            LocalDateTime.parse("2025-03-08 16:07:43.000000", formatter),
            119000038L,
            "{100100057,31},{100100093,50},{100100260,46},{100100454,41},{113000002,60},{114000002,84}"
    );

    public static final GeofenceLocation test1_geofenceLocation6 = GeofenceLocation.createGeofenceLocationEntityForTest(6L, "chungjongin@gmail.com",
            LocalDateTime.parse("2025-03-08 16:08:43.000000", formatter),
            LocalDateTime.parse("2025-03-08 16:10:43.000000", formatter),
            119000037L,
            "{100100057,32},{100100093,51},{100100260,47},{100100454,42},{113000002,61},{114000002,85}"
    );

    public static final GeofenceLocation test1_geofenceLocation7 = GeofenceLocation.createGeofenceLocationEntityForTest(7L, "chungjongin@gmail.com",
            LocalDateTime.parse("2025-03-08 16:11:43.000000", formatter),
            LocalDateTime.parse("2025-03-08 16:13:43.000000", formatter),
            119000034L,
            "{100100057,33},{100100093,52},{100100454,43},{114000002,86}"
    );

    public static final GeofenceLocation test1_geofenceLocation8 = GeofenceLocation.createGeofenceLocationEntityForTest(8L, "chungjongin@gmail.com",
            LocalDateTime.parse("2025-03-08 16:20:43.000000", formatter),
            LocalDateTime.parse("2025-03-08 16:21:43.000000", formatter),
            119000031L,
            "{100100057,34},{100100093,53},{100100268,35},{100100454,44},{114000002,87}"
    );

    public static final GeofenceLocation test1_geofenceLocation9 = GeofenceLocation.createGeofenceLocationEntityForTest(9L, "chungjongin@gmail.com",
            LocalDateTime.parse("2025-03-08 16:23:43.000000", formatter),
            LocalDateTime.parse("2025-03-08 16:25:43.000000", formatter),
            119000030L,
            "{100100057,59},{100100093,29},{100100268,32},{100100454,53},{114000002,33}"
    );

    public static final GeofenceLocation test1_geofenceLocation10 = GeofenceLocation.createGeofenceLocationEntityForTest(10L, "chungjongin@gmail.com",
            LocalDateTime.parse("2025-03-08 17:18:03.000000", formatter),
            LocalDateTime.parse("2025-03-08 17:20:03.000000", formatter),
            119000033L,
            "{100100057,60},{100100093,30},{100100260,51},{100100454,54},{113000002,66},{114000002,34}"
    );







}
