package project.paypass.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.paypass.domain.GeofenceLocation;

import java.util.*;

@Slf4j
@Service
public class BasicAlgorithmService {

    public List<GeofenceLocation> algorithmStart(List<GeofenceLocation> geofenceLocations) {
        // 데이터 정렬(fenceInTime 기준)
        List<GeofenceLocation> sortedGeofenceLocations = sortByUserFenceInTime(geofenceLocations);

        Map<String, List<Integer>> indexMap = basicLogic(sortedGeofenceLocations);

        // indexMap을 참고하여 geofenceLocation 필터링
        List<GeofenceLocation> geofenceLocationList = filterGeofenceLocationList(sortedGeofenceLocations, indexMap);

        // list를 set으로 변환해서 test하기 (테스트용도)
        for (GeofenceLocation geofenceLocation : geofenceLocationList) {
            System.out.println("geofenceLocation = " + geofenceLocation);
        }
        System.out.println("geofenceLocationList.size() = " + geofenceLocationList.size());

        Set<GeofenceLocation> geofenceLocationSet = Set.copyOf(geofenceLocationList);
        List<GeofenceLocation> resultList = new ArrayList<>(geofenceLocationSet);
        List<GeofenceLocation> sortedResultList = sortByUserFenceInTime(resultList);

        System.out.println("------------------------------------------------------------------");
        System.out.println("중복 제거 후 정렬한 리스트");
        for (GeofenceLocation geofenceLocation : sortedResultList) {
            System.out.println("geofenceLocation = " + geofenceLocation);
        }
        System.out.println("sortedresultList.size() = " + sortedResultList.size());

        return sortedResultList;
    }

    private Map<String, List<Integer>> basicLogic(List<GeofenceLocation> sortedGeofenceLocations) {

        // busInfo만 존재하는 List 생성
        List<String> busInfoList = makeBusInfoList(sortedGeofenceLocations);
        log.info("busInfoList = " + busInfoList);

        // busInfoList를 통하여 busInfoMap 생성
        Map<String, List<Long>> busInfoMap = makeBusInfoMap(busInfoList);
        log.info("busInfoMap = " + busInfoMap);

        // sequence가 순차적으로 증가하는지 검사
        // sequence의 일정 부분만 조건 만족 시 해당 부분의 index만 추출
        // 조건 만족 시 해당 beginIndex와 endIndex를 가지는 map 추가
        Map<String, List<Integer>> indexMap = makeIndexMap(busInfoMap);
        log.info("indexMap = " + indexMap);

        return indexMap;
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

    private Map<String, List<Long>> makeBusInfoMap(List<String> busInfoList) {
        Map<String, List<Long>> busInfoMap = new TreeMap<>();

        // busInfoList에서 routeId만을 추출해서 set 생성
        Set<String> routeIdSet = makeRouteIdSet(busInfoList);

        // busInfoMap에서 key값 추가
        busInfoMapPlusKey(busInfoMap, routeIdSet);

        // busInfoMap에서 value값 추가
        busInfoMapPlusValue(busInfoMap, busInfoList);
        log.info("000이 제거되기 전 busInfoMap" + busInfoMap);

        // 중복되는 routeId를 해결하기 위해
        // sequence 값에 000이 있으면 경우의 수를 두개로 분리
        Map<String, List<Long>> finalBusInfoMap = divideBusInfoMap(busInfoMap);

        return finalBusInfoMap;
    }

    private Map<String, List<Integer>> makeIndexMap(Map<String, List<Long>> busInfoMap) {
        Map<String, List<Integer>> indexMap = new TreeMap<>();

        for (String routeId : busInfoMap.keySet()) {
            List<Long> sequenceList = busInfoMap.get(routeId);
            List<Integer> beginAndEndIndex = checkSequential(sequenceList);

            // 조건 만족시 실행 로직
            if (beginAndEndIndex.size() >= 2) {
                indexMap.put(routeId, beginAndEndIndex);
            }

            // 조건 불만족 시 실행 로직
            if (beginAndEndIndex.isEmpty()) {
                continue;
            }
        }

        return indexMap;
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

    private void busInfoMapPlusKey(Map<String, List<Long>> busInfoMap, Set<String> routeIdSet) {
        for (String routeId : routeIdSet) {
            busInfoMap.put(routeId, new ArrayList<>());
        }
    }

    private void busInfoMapPlusValue(Map<String, List<Long>> busInfoMap, List<String> busInfoList) {
        // busInfoList를 쪼개서 oneStationInfoList 생성
        for (String oneStationInfo : busInfoList) {
            List<String> oneStationInfoList = Arrays.asList(oneStationInfo.replaceAll("^\\{|\\}$", "").split("},\\{"));

            // oneStationInfoList에 중복의 routeId가 있다면
            // sequence 값을 000으로 결합
            // [{100100317,8},{100100307,14},{100100316,24},{100100316,53},{100100308,69}]
            // -> [{100100317,8},{100100307,14},{100100316,2400053},{100100308,69}]
            List<String> checkedOneStationInfoList = checkDuplicateRouteId(oneStationInfoList);

            // oneStationInfoList를 쪼개서 routeIdAndSequence 생성
            for (String oneBusInfo : checkedOneStationInfoList) {
                List<String> routeIdAndSequence = Arrays.asList(oneBusInfo.split(","));
                String routeId = routeIdAndSequence.get(0);
                Long sequence = stringToLong(routeIdAndSequence.get(1));

                // sequence 값 추가하기
                List<Long> sequenceList = busInfoMap.get(routeId);
                sequenceList.add(sequence);
            }
        }
    }

    private Map<String, List<Long>> divideBusInfoMap(Map<String, List<Long>> busInfoMap) {

        log.info("===========================================================================");
        log.info("busInfoMap = " + busInfoMap);

        // 수정하기 위한 복사본 생성
        Map<String, List<Long>> updatedBusInfoMap = new TreeMap<>(busInfoMap);
        // 삭제할 key를 저장할 리스트 생성
        List<String> keysToRemove = new ArrayList<>();

        for (var oneBusInfo : busInfoMap.entrySet()) {
            String routeId = oneBusInfo.getKey();
            List<Long> sequenceList = oneBusInfo.getValue();

            for (int i = 0; i < sequenceList.size(); i++) {
                Long sequence = sequenceList.get(i);
                String stringSequence = String.valueOf(sequence);

                if (stringSequence.contains("000")) {
                    List<String> twoSequence = Arrays.asList(stringSequence.split("000"));
                    Long firstSequence = Long.parseLong(twoSequence.get(0));
                    Long secondSequence = Long.parseLong(twoSequence.get(1));

                    // 해당 로직이 처음일 경우
                    if (!routeId.contains("_")) {
                        sequenceList.set(i, firstSequence);
                        updatedBusInfoMap.put(routeId + "_1", new ArrayList<>(sequenceList));
                        sequenceList.set(i, secondSequence);
                        updatedBusInfoMap.put(routeId + "_2", new ArrayList<>(sequenceList));

                        // 기존 routeId는 삭제해야 하므로 목록에 추가
                        keysToRemove.add(routeId);
                        break;
                    }

                    // 해당 로직이 다회차일 때
                    if (routeId.contains("_")) {
                        // ex) 123123123_1 -> [123123123,1]
                        List<String> routeIdAndCount = Arrays.asList(routeId.split("_"));
                        String pureRouteId = routeIdAndCount.get(0);
                        String currentCount = routeIdAndCount.get(1);

                        Long count = findMaxCount(updatedBusInfoMap, pureRouteId);

                        sequenceList.set(i, firstSequence);
                        updatedBusInfoMap.put(pureRouteId + "_" + currentCount, new ArrayList<>(sequenceList));

                        sequenceList.set(i, secondSequence);
                        updatedBusInfoMap.put(pureRouteId + "_" + (count+1), new ArrayList<>(sequenceList));
                        break;
                    }
                }
            }
        } // for문 종료

        // 기존 맵에서 첫 분리된 key 삭제
        for (String key : keysToRemove) {
            updatedBusInfoMap.remove(key);
        }

        // 원본 맵을 최신 데이터로 갱신
        busInfoMap.clear();
        busInfoMap.putAll(updatedBusInfoMap);

        log.info("===========================================================================");

        if(check000(busInfoMap)){
            return divideBusInfoMap(busInfoMap);
        }

        return busInfoMap;
    }


    private boolean check000(Map<String, List<Long>> busInfoMap) {
        for (List<Long> sequenceList : busInfoMap.values()) {
            for (Long sequence : sequenceList) {
                if (String.valueOf(sequence).contains("000")) {
                    return true;
                }
            }
        }
        return false;
    }

    private Long findMaxCount(Map<String, List<Long>> updatedBusInfoMap,String pureRouteId) {
        Set<String> keySet = updatedBusInfoMap.keySet();
        ArrayList<Long> countList = new ArrayList<>();

        for (String routeId : keySet) {
            if (routeId.contains(pureRouteId)){
                List<String> routeIdAndCount = Arrays.asList(routeId.split("_"));
                countList.add(Long.parseLong(routeIdAndCount.get(1)));
            }
        }
        return countList.stream().max(Long::compareTo).get();
    }

    private List<String> checkDuplicateRouteId(List<String> oneStationInfoList) {
        Map<String, StringBuilder> oneStationInfoMap = new TreeMap<>();

        for (String oneBusInfo : oneStationInfoList) {
            List<String> routeIdAndSequence = Arrays.asList(oneBusInfo.split(","));
            String routeId = routeIdAndSequence.get(0);
            String sequence = routeIdAndSequence.get(1);

            // 같은 routeId가 있으면 "000"을 추가하여 결합
            oneStationInfoMap.putIfAbsent(routeId, new StringBuilder());
            if (!oneStationInfoMap.get(routeId).isEmpty()) {
                oneStationInfoMap.get(routeId).append("000");
            }
            oneStationInfoMap.get(routeId).append(sequence);
        }

        List<String> checkedOneStationInfoList = new ArrayList<>();
        for (var oneBusInfo : oneStationInfoMap.entrySet()) {
            String routeId = oneBusInfo.getKey();
            String sequence = oneBusInfo.getValue().toString();
            checkedOneStationInfoList.add(routeId + "," + sequence);
        }

        return checkedOneStationInfoList;
    }

    private List<Integer> checkSequential(List<Long> sequenceList) {
        List<Integer> beginAndEndIndex = new ArrayList<>();

        // ex) sequenceList = [1,2,3,4,4,6,9] -> beginAndEndIndex = [0,3]
        // ex) sequenceList = [1,3,4,5,9,13,21,22] -> beginAndEndIndex = [1,3,6,7]
        // ex) sequenceList = [1,3,2,4,7,1,0,1,2,3,3,2,6] -> beginAndEndIndex = [6,9]
        // ex) sequenceList = [1,2,3,7,8,23,24,1,3] -> beginAndEndIndex = [0,2,3,4,5,6]
        // 연속되는 부분이 있다면 해당 index 값을 beginAndEndIndex 리스트에 넣기
        if (sequenceList.size() < 2) {
            return beginAndEndIndex;
        }

        int startIndex = 0;
        // 연속되는 구간의 시작과 끝 여부를 판단
        boolean isSequential = false;

        for (int i = 0; i < sequenceList.size() - 1; i++) {
            // 두 값이 연속적인지 여부를 판단
            boolean isContinuous = sequenceList.get(i + 1) - sequenceList.get(i) == 1;

            // i+1 값과 연속성 존재 + 첫 연속 구간
            if (isContinuous && !isSequential) {
                startIndex = i;
                isSequential = true;
            }

            // i+1 값과 연속성 존재하지 않음 + 연속구간 끝
            if (!isContinuous && isSequential) {
                beginAndEndIndex.add(startIndex);
                beginAndEndIndex.add(i);
                isSequential = false;
            }
        }

        if (isSequential) {
            beginAndEndIndex.add(startIndex);
            beginAndEndIndex.add(sequenceList.size() - 1);
        }

        return beginAndEndIndex;
    }

    private List<GeofenceLocation> filterGeofenceLocationList(List<GeofenceLocation> sortedgeofenceLocationList, Map<String, List<Integer>> indexMap) {
        List<GeofenceLocation> geofenceLocationList = new ArrayList<>();
        Set<String> keySet = indexMap.keySet();

        for (String routeId : keySet) {
            List<GeofenceLocation> containRouteIdList = new ArrayList<>();
            List<Integer> indexList = indexMap.get(routeId);

            // routeId 뒤에 _? 가 존재하는 경우 제거
            if (routeId.contains("_")) {
                routeId = routeId.substring(0, routeId.indexOf("_"));
            }

            // sortedGeofenceLocationList에서 geofenceLocation을 추출해서 하나하나 확인하기
            for (GeofenceLocation geofenceLocation : sortedgeofenceLocationList) {
                // 만약 포함한지 않는다면
                if (!geofenceLocation.stationBusInfo().contains(routeId)) {
                    continue;
                }

                // 만약 포함한다면
                if (geofenceLocation.stationBusInfo().contains(routeId)) {
                    containRouteIdList.add(geofenceLocation);
                }
            }

            // indexList에 존재하는 index를 활용하여 containList indexing하기
            List<GeofenceLocation> sequentialcontainRouteIdList = indexcontainRouteIdList(containRouteIdList, indexList);
            geofenceLocationList.addAll(sequentialcontainRouteIdList);
        }

        return geofenceLocationList;
    }

    private List<GeofenceLocation> indexcontainRouteIdList(List<GeofenceLocation> containRouteIdList, List<Integer> indexList) {
        List<GeofenceLocation> sequentialcontainRouteIdList = new ArrayList<>();
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        for (GeofenceLocation geofenceLocation : containRouteIdList) {
            System.out.println("containRouteIdList의 geofenceLocation = " + geofenceLocation);
        }
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        for (int i = 0; i < indexList.size(); i += 2) {
            Integer startIndex = indexList.get(i);
            Integer endIndex = indexList.get(i + 1);

            List<GeofenceLocation> localList = containRouteIdList.subList(startIndex, endIndex + 1);

            System.out.println("==========================================================");
            for (GeofenceLocation geofenceLocation : localList) {
                System.out.println("localList의 geofenceLocation = " + geofenceLocation);
            }
            System.out.println("==========================================================");
            sequentialcontainRouteIdList.addAll(localList);
        }
        return sequentialcontainRouteIdList;
    }

    private Long stringToLong(String string) {
        return Long.parseLong(string);
    }

    private List<GeofenceLocation> sortByUserFenceInTime(List<GeofenceLocation> geofenceLocationList) {
        return geofenceLocationList.stream()
                .sorted(Comparator.comparing(GeofenceLocation::userFenceInTime))
                .toList();
    }

}