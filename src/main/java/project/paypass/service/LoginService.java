package project.paypass.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.paypass.repository.LoginRepository;
import project.paypass.domain.Login;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginRepository loginRepository;

    @Transactional
    public void save(Login login){
        loginRepository.save(login);
    }



}
