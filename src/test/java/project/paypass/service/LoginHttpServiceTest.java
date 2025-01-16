package project.paypass.service;

import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.paypass.domain.User;
import project.paypass.domain.dto.UserInfoDto;

import java.time.LocalDateTime;

@SpringBootTest
class LoginHttpServiceTest {

    @Autowired
    private LoginHttpService loginHttpService;
    @Autowired
    private UserService userService;
    @Autowired
    private WalletService walletService;

    @Test
    @DisplayName("신규유저_여부확인_기존회원")
    @Transactional
    void checkNewUser() {
        // given
        saveOneUser();
        String mainId = "test@email.com";
        // when
        boolean result = loginHttpService.checkNewUser(mainId);
        // then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    @DisplayName("신규유저_여부확인_신규회원")
    @Transactional
    void checkExistingUser() {
        // given
        saveOneUser();
        String mainId = "chung@email.com";
        // when
        boolean result = loginHttpService.checkNewUser(mainId);
        // then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    @DisplayName("신규유저_데이터_저장")
    @Transactional
    void saveNewUser() {
        // given
        String mainId = "test@email.com";
        UserInfoDto userInfoDto = new UserInfoDto(mainId, "hi", "2000-01-01", "01089099721");
        // when
        loginHttpService.saveNewUser(userInfoDto);
        Long userId = userService.findIdByMainId(mainId);
        Long walletId = walletService.findIdByMainId(mainId);
        // then
        Assertions.assertThat(userService.findById(userId).getMainId()).isEqualTo(mainId);
        Assertions.assertThat(walletService.findById(walletId).getMainId()).isEqualTo(mainId);
    }

    private void saveOneUser(){
        String birthString = "2000-01-01T00:00:00"; // String 형태로 된 birth
        LocalDateTime birth = LocalDateTime.parse(birthString); // String을 LocalDateTime으로 변환
        userService.save(new User("test@email.com","hi", birth,"01089099721"));
    }
}