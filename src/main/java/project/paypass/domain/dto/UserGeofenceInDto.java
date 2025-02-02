package project.paypass.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class UserGeofenceInDto {
    private String mainId;
    private Long stationNumber;

    public UserGeofenceInDto(String mainId, Long stationNumber) {
        this.mainId = mainId;
        this.stationNumber = stationNumber;
    }
}
