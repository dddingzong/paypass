package project.paypass.domain.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserLocationDto {
    private String mainId;
    private double latitude;
    private double longitude;
}
