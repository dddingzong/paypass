package project.paypass.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class DetailLogDto {

    private Long id;
    private String mainId;
    private LocalDateTime fenceInTime;
    private LocalDateTime fenceOutTime;
    private Long stationNumber;

    public DetailLogDto(Long id, String mainId, LocalDateTime fenceInTime, LocalDateTime fenceOutTime, Long stationNumber) {
        this.id = id;
        this.mainId = mainId;
        this.fenceInTime = fenceInTime;
        this.fenceOutTime = fenceOutTime;
        this.stationNumber = stationNumber;
    }
}
