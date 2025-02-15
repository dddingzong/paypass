package project.paypass.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.paypass.domain.GeofenceLocation;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class AlgorithmService {

    public void basic(List<GeofenceLocation> geofenceLocations) {
        // 데이터 정렬(fenceInTime 기준)
        List<GeofenceLocation> sortedGeofenceLocations = geofenceLocations.stream()
                .sorted(Comparator.comparing(GeofenceLocation::userFenceInTime))
                .toList();

        basicLogic(sortedGeofenceLocations);
    }

    private void basicLogic(List<GeofenceLocation> sortedGeofenceLocations) {

        int count = 1;
        // busInfo만 존재하는 List 생성
        List<String> busInfoList = makeBusInfoList(sortedGeofenceLocations);
        log.info("busInfoList = " + busInfoList);

        for (String oneStationInfo : busInfoList) { // oneStationInfo -> 하나의 정류장에 대한 busInfo
            // busInfoList의 routeId만 추출하여 List로 변환 (나중에 메인로직에서 사용)
            List<String> routeIdList = makeRouteIdList(busInfoList, count);
            log.info("routeIdList = " + routeIdList);
            // busInfoList의 sequence만 추출해서 List로 변환 (나중에 메인로직에서 사용)
            List<String> sequenceList = makeSequenceList(busInfoList, count);
            log.info("sequenceList = " + sequenceList);

            // busInfo만 존재하는 list를 다시 하나씩 분류 (정류장마다 분류)
            List<String> oneStationInfoList = Arrays.asList(oneStationInfo.replaceAll("^\\{|\\}$", "").split("},\\{"));

            // 하나의 정류장에 대한 List를 가지고 기본 알고리즘 실행 (routeId의 동일과 sequence의 차이가 1인지 확인)
            // oneStationInfoList: 하나의 정류장에 대한 busInfo List
            checkDuplicateRouteId(oneStationInfoList, routeIdList, sequenceList);
            count++;
        }

    }

    private String checkDuplicateRouteId(List<String> oneStationInfoList, List<String> routeIdList, List<String> sequenceList) {
        // 하나의 정류장에 대한 busInfo를 하나씩 분리 (버스 하나씩 분리)

        List<String> checkedList = new ArrayList<>();

        for (String oneBusInfo : oneStationInfoList) {

            // routeId와 sequence 추출
            List<String> routeIdAndSequence = Arrays.asList(oneBusInfo.split(","));
            String routeId = routeIdAndSequence.get(0);
            String sequence = routeIdAndSequence.get(1);

            // contain 한다면 이후 geofenceLocation에서 같은 routeId가 존재
            if (routeIdList.contains(routeId)) {
                int routeIdIndex = routeIdList.indexOf(routeId);
                // true 값이 나오면 routeId와 sequence의 조건을 만족한다.
                boolean thereIsNextStation = checkSequencePlusOne(sequenceList, routeIdIndex, sequence);

                if (thereIsNextStation) {
                    checkedList.add(routeId); // 현재 검사하는 geofenceLocation 뿐만 아니라 다음 정류장도 같이 board를 업데이트해야된다 2025.02.12
                }
            }
            // contain 하지 않는다면 이후 geofenceLocation에서 같은 routeId가 존재하지 않음
        }

        return String.join(",",checkedList);
    }

    private boolean checkSequencePlusOne(List<String> sequenceList, int routeIdIndex, String sequence) {
        int nextStation = stringToInt(sequenceList.get(routeIdIndex));
        int checkStation = stringToInt(sequence) + 1;
        return nextStation == checkStation;
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

    private List<String> makeRouteIdList(List<String> busInfoList, int count) {
        // busInfoList에서 각 busInfo의 routeId만 추출하여 새로운 리스트에 추가
        List<String> localList = new ArrayList<>();

        // busInfo에서 routeId 추출
        for (String oneStationInfo : busInfoList.subList(count, busInfoList.size())) {
            List<String> oneStationInfoList = Arrays.asList(oneStationInfo.replaceAll("^\\{|\\}$", "").split("},\\{"));

            for (String oneBusInfo : oneStationInfoList) {
                List<String> routeIdAndSequence = Arrays.asList(oneBusInfo.split(","));
                String routeId = routeIdAndSequence.get(0);
                localList.add(routeId);
            }
        }

        return localList;
    }

    private List<String> makeSequenceList(List<String> busInfoList, int count) {
        // busInfoList에서 각 busInfo의 sequence만 추출하여 새로운 리스트에 추가
        List<String> localList = new ArrayList<>();

        // busInfo에서 sequence 추출
        for (String oneStationInfo : busInfoList.subList(count, busInfoList.size())) {
            List<String> oneStationInfoList = Arrays.asList(oneStationInfo.replaceAll("^\\{|\\}$", "").split("},\\{"));

            for (String oneBusInfo : oneStationInfoList) {
                List<String> routeIdAndSequence = Arrays.asList(oneBusInfo.split(","));
                String routeId = routeIdAndSequence.get(1);
                localList.add(routeId);
            }
        }

        return localList;
    }

    private int stringToInt(String string){
        return Integer.parseInt(string);
    }


}