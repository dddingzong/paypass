package project.paypass.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.paypass.domain.GeofenceLocation;

import java.util.*;

@Slf4j
@Service
public class DuplicateDeleteAlgorithm {

    // 평균 시간 데이터를 통과하고 난 이후의 geofenceLocation 데이터만 들어온다.
    public List<GeofenceLocation> algorithmStart(Map<String, List<Long>> BusInfoMap){

//        List<GeofenceLocation> sortedGeofenceLocations = sortByUserFenceInTime(geofenceLocations);
//
//        // 먼저 routeId : stationNumber 형식의 Map 작성
//        makeStationMap(sortedGeofenceLocations);

        return null;
    }

    private Map<String,List<String>> makeStationMap(List<GeofenceLocation> sortedGeofenceLocations) {
        Map<String, List<String>> stationMap = new TreeMap<>();

        // busInfo로만 이루어진 busInfoList 생성
        List<String> busInfoList = makeBusInfoList(sortedGeofenceLocations);

        // busInfoList를 활용하여 routeIdSet 생성
        Set<String> routeIdSet = makeRouteIdSet(busInfoList);

        // routeIdSet을 활용하여 Key 값 할당
        stationMapPlusKey(stationMap, routeIdSet);

        stationMapPlusValue(stationMap, sortedGeofenceLocations);

        return stationMap;
    }


    private List<String> makeBusInfoList(List<GeofenceLocation> sortedGeofenceLocations) {
        // busInfo만 존재하는 List 생성
        List<String> busInfoList = new ArrayList<>();

        for (GeofenceLocation geofenceLocation : sortedGeofenceLocations) {
            String busInfo = geofenceLocation.stationBusInfo();

            busInfoList.add(busInfo);
        }

        return busInfoList;
    }

    private Set<String> makeRouteIdSet(List<String> busInfoList) {
        // busInfoList에서 각 busInfo의 routeId만 추출하여 set에 추가
        Set<String> localSet = new HashSet<>();

        // busInfoList를 쪼개서 oneStationInfoList 생성
        for (String oneStationInfo : busInfoList) {
            List<String> oneStationInfoList = Arrays.asList(oneStationInfo.replaceAll("^\\{|\\}$", "").split("},\\{"));
            // oneStationInfoList를 쪼개서 routeIdAndSequence 생성
            for (String oneBusInfo : oneStationInfoList) {
                List<String> routeIdAndSequence = Arrays.asList(oneBusInfo.split(","));
                String routeId = routeIdAndSequence.get(0);
                localSet.add(routeId);
            }
        }
        return localSet;
    }

    private void stationMapPlusKey(Map<String, List<String>> stationMap, Set<String> routeIdSet) {
        for (String routeId : routeIdSet) {
            stationMap.put(routeId, new ArrayList<>());
        }
    }

    private void stationMapPlusValue(Map<String, List<String>> stationMap, List<GeofenceLocation> sortedGeofenceLocations) {

        for (String routeId : stationMap.keySet()) {

            for (GeofenceLocation geofenceLocation : sortedGeofenceLocations) {
                if (geofenceLocation.stationBusInfo().contains(routeId)){
//                    stationMap.get(routeId).add(geofenceLocation.getStationNumber());
                }
            }


        }

    }

    private List<GeofenceLocation> sortByUserFenceInTime(List<GeofenceLocation> geofenceLocationList) {
        return geofenceLocationList.stream()
                .sorted(Comparator.comparing(GeofenceLocation::userFenceInTime))
                .toList();
    }
}
