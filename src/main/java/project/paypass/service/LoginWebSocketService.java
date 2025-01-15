package project.paypass.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.paypass.domain.User;
import project.paypass.domain.Wallet;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LoginWebSocketService {

    private final LoginService loginService;
    private final UserService userService;
    private final WalletService walletService;

    @Transactional
    public boolean checkNewUser(String mainId){
        return userService.checkNewUser(mainId);
    }

    @Transactional
    public void saveNewUser(Map<String, Object> outerMap){
        // userInfo 처리해야함
        // userInfo = {type=newUser, userInfo={name=cji, birth=2000-05-13, phone=01089099721}}

        Map<String, Object> userInfo = (Map<String, Object>) outerMap.get("userInfo");

        System.out.println("userInfo = " + userInfo);

        String mainId = (String) userInfo.get("mainId");
        String name = (String) userInfo.get("name");
        String phoneNumber = (String) userInfo.get("phoneNumber");

        // birth 데이터 변형
        String birthString = (String) userInfo.get("birth");
        LocalDateTime birth = LocalDateTime.parse(birthString+"T00:00:00");

        // 이후 회원가입 기능이 추가된다면 new Login() 필요함
        userService.save(new User(mainId,name,birth,phoneNumber));
        walletService.save(new Wallet(mainId));
    }

}
