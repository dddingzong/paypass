package project.paypass.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class LocationWebSocketHandler extends TextWebSocketHandler {
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception{ // 이거 그냥 throw 처리해도 되나?
            System.out.println("Received location data: " + message.getPayload());
        // 여기에 데이터를 처리하거나 저장하는 로직 추가
    }
}
