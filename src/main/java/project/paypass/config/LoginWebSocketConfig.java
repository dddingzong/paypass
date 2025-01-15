package project.paypass.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import project.paypass.handler.LoginWebSocketHandler;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class LoginWebSocketConfig implements WebSocketConfigurer {

    private final LoginWebSocketHandler loginWebSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry){
        registry.addHandler(loginWebSocketHandler, "/login") // ws://주소:포트/auth으로 요청
                .setAllowedOrigins("*"); // CORS 설정 (필요 시 도메인 제한)
    }
}
