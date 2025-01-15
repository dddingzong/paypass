package project.paypass.domain.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserInfoDto {

    private String mainId;
    private String name;
    private String birth;
    private String phoneNumber;
}
