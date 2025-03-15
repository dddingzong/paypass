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

    public static final GeofenceLocation test2_geofenceLocation1 = GeofenceLocation.createGeofenceLocationEntityForTest(
            1L,
            "chungjongin@gmail.com",
            LocalDateTime.parse("2025-03-04 15:42:31.000000", formatter),
            LocalDateTime.parse("2025-03-04 15:44:31.000000", formatter),
            116000094L,
            "{100100298,26}"
    );

    public static final GeofenceLocation test2_geofenceLocation2 = GeofenceLocation.createGeofenceLocationEntityForTest(
            2L,
            "chungjongin@gmail.com",
            LocalDateTime.parse("2025-03-04 15:54:59.000000", formatter),
            LocalDateTime.parse("2025-03-04 15:56:39.000000", formatter),
            116000102L,
            "{100100298,27},{100100298,47},{100100451,12},{100100451,100},{100100453,16},{100100453,76},{100100601,43},{116000002,38}"
    );

    public static final GeofenceLocation test2_geofenceLocation3 = GeofenceLocation.createGeofenceLocationEntityForTest(
            3L,
            "chungjongin@gmail.com",
            LocalDateTime.parse("2025-03-04 16:06:43.000000", formatter),
            LocalDateTime.parse("2025-03-04 16:08:43.000000", formatter),
            116000104L,
            "{100100298,28},{100100298,48},{100100451,13},{100100451,101},{100100453,61},{100100453,77},{100100601,44},{116000002,39}"
    );

    public static final GeofenceLocation test2_geofenceLocation4 = GeofenceLocation.createGeofenceLocationEntityForTest(
            4L,
            "chungjongin@gmail.com",
            LocalDateTime.parse("2025-03-04 16:22:47.000000", formatter),
            LocalDateTime.parse("2025-03-04 16:24:47.000000", formatter),
            116000202L,
            "{100100298,35},{100100298,42},{100100453,11},{100100453,68}"
    );

    public static final GeofenceLocation test2_geofenceLocation5 = GeofenceLocation.createGeofenceLocationEntityForTest(
            5L,
            "chungjongin@gmail.com",
            LocalDateTime.parse("2025-03-04 16:34:50.000000", formatter),
            LocalDateTime.parse("2025-03-04 16:36:50.000000", formatter),
            116000101L,
            "{100100298,36},{100100298,43},{100100453,12},{100100453,69}"
    );

    public static final GeofenceLocation test2_geofenceLocation6 = GeofenceLocation.createGeofenceLocationEntityForTest(
            6L,
            "chungjongin@gmail.com",
            LocalDateTime.parse("2025-03-04 16:46:54.000000", formatter),
            LocalDateTime.parse("2025-03-04 16:48:54.000000", formatter),
            116000100L,
            "{100100298,37},{100100298,44},{100100453,13},{100100453,70}"
    );

    public static final GeofenceLocation test2_geofenceLocation7 = GeofenceLocation.createGeofenceLocationEntityForTest(
            7L,
            "chungjongin@gmail.com",
            LocalDateTime.parse("2025-03-04 17:02:58.000000", formatter),
            LocalDateTime.parse("2025-03-04 17:04:58.000000", formatter),
            116000095L,
            "{100100298,54}"
    );

    public static final GeofenceLocation test2_geofenceLocation8 = GeofenceLocation.createGeofenceLocationEntityForTest(
            8L,
            "chungjongin@gmail.com",
            LocalDateTime.parse("2025-03-04 17:14:58.000000", formatter),
            LocalDateTime.parse("2025-03-04 17:16:58.000000", formatter),
            116000093L,
            "{100100298,55},{100100453,19}"
    );

    public static final GeofenceLocation test2_geofenceLocation9 = GeofenceLocation.createGeofenceLocationEntityForTest(
            9L,
            "chungjongin@gmail.com",
            LocalDateTime.parse("2025-03-04 17:26:58.000000", formatter),
            LocalDateTime.parse("2025-03-04 17:30:58.000000", formatter),
            116000118L,
            "{100100298,61},{100100312,62},{100100313,50}"
    );

    public static final GeofenceLocation test2_geofenceLocation10 = GeofenceLocation.createGeofenceLocationEntityForTest(
            10L,
            "chungjongin@gmail.com",
            LocalDateTime.parse("2025-03-04 17:45:09.000000", formatter),
            LocalDateTime.parse("2025-03-04 17:46:09.000000", formatter),
            213000158L,
            "{100100298,62},{100100312,63},{100100313,51}"
    );

    public static final GeofenceLocation test2_geofenceLocation11 = GeofenceLocation.createGeofenceLocationEntityForTest(
            11L,
            "chungjongin@gmail.com",
            LocalDateTime.parse("2025-03-04 17:54:09.000000", formatter),
            LocalDateTime.parse("2025-03-04 17:55:09.000000", formatter),
            213000033L,
            "{100100298,63},{100100312,64},{100100313,52}"
    );

    public static final GeofenceLocation test2_geofenceLocation12 = GeofenceLocation.createGeofenceLocationEntityForTest(
            12L,
            "chungjongin@gmail.com",
            LocalDateTime.parse("2025-03-04 18:04:09.000000", formatter),
            LocalDateTime.parse("2025-03-04 18:06:09.000000", formatter),
            213000045L,
            "{100100298,66},{100100313,55}"
    );

    public static final GeofenceLocation test2_geofenceLocation13 = GeofenceLocation.createGeofenceLocationEntityForTest(
            13L,
            "chungjongin@gmail.com",
            LocalDateTime.parse("2025-03-04 18:16:09.000000", formatter),
            LocalDateTime.parse("2025-03-04 18:18:09.000000", formatter),
            213000128L,
            "{100100298,68},{100100313,57}"
    );

    public static final GeofenceLocation test2_geofenceLocation14 = GeofenceLocation.createGeofenceLocationEntityForTest(
            14L,
            "chungjongin@gmail.com",
            LocalDateTime.parse("2025-03-04 18:36:11.000000", formatter),
            LocalDateTime.parse("2025-03-04 18:38:11.000000", formatter),
            102000007L,
            "{100100079,41},{100100114,59},{100100410,63},{100100565,56}"
    );

    public static final GeofenceLocation test2_geofenceLocation15 = GeofenceLocation.createGeofenceLocationEntityForTest(
            15L,
            "chungjongin@gmail.com",
            LocalDateTime.parse("2025-03-04 18:46:11.000000", formatter),
            LocalDateTime.parse("2025-03-04 18:48:11.000000", formatter),
            102000011L,
            "{100100079,43},{100100080,46},{100100114,61},{100100410,65},{100100447,43},{100100565,58},{111000016,130}"
    );

    public static final GeofenceLocation test2_geofenceLocation16 = GeofenceLocation.createGeofenceLocationEntityForTest(
            16L,
            "chungjongin@gmail.com",
            LocalDateTime.parse("2025-03-04 18:56:11.000000", formatter),
            LocalDateTime.parse("2025-03-04 18:58:11.000000", formatter),
            220000006L,
            "{100100070,88},{100100083,64},{100100410,96},{100100574,80}"
    );

}
