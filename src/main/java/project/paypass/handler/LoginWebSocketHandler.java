package project.paypass.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import project.paypass.service.LoginWebSocketService;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class LoginWebSocketHandler extends TextWebSocketHandler {

    private final LoginWebSocketService loginWebSocketService;

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> data = objectMapper.readValue(payload, Map.class); // json to map
        String type = (String) data.get("type");

        if ("login".equals(type)){
            handleLogin(session, (String) data.get("googleId"));
        }

        if ("newUser".equals(type)){
            handleNewUser(session, data);
        }
    }

    private void handleLogin(WebSocketSession session, String googleId) throws Exception {
        System.out.println("Google ID: " + googleId);

        if (!loginWebSocketService.checkNewUser(googleId)) {
            System.out.println("서버로 EXISTING_USER 메세지 전송");
            session.sendMessage(new TextMessage("EXISTING_USER"));
        }

        if (loginWebSocketService.checkNewUser(googleId)){
            System.out.println("서버로 NEW_USER 메세지 전송");
            session.sendMessage(new TextMessage("NEW_USER"));
        }
    }

    private void handleNewUser(WebSocketSession session, Map<String, Object> userInfo) throws Exception {
        System.out.println("userInfo = " + userInfo);
        // 신규 회원 저장 로직 호출 필요
        loginWebSocketService.saveNewUser(userInfo);
        session.sendMessage(new TextMessage("USER_SAVED"));
    }
}