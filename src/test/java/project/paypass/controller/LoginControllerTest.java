package project.paypass.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import project.paypass.domain.dto.UserInfoDto;
import project.paypass.service.LoginHttpService;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Transactional
class LoginControllerTest {
    // 여기서는 통신을 테스트한다.
    // 기능적인 부분은 Service 에서 테스트한다.

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Mock
    private LoginHttpService loginHttpService;

    @InjectMocks
    private LoginController loginController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("로그인시_EXISTING_mainId")
    void checkLoginExistingData() {
        try {
            // given
            Map<String, String> request = new HashMap<>();
            request.put("googleId", "chungjongin@gmail.com");
            // when
            when(loginHttpService.checkNewUser(anyString())).thenReturn(false);
            // then
            mockMvc.perform(post("/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.status").value("EXISTING_USER"));
        } catch (Exception e) {
            fail("예외 발생: " + e.getMessage()); // 테스트 실패로 처리
        }
    }

    @Test
    @DisplayName("로그인시_NEW_mainId")
    void checkLoginNewData() {
        try {
            // given
            Map<String, String> request = new HashMap<>();
            request.put("googleId", "chungjongin@gmail.com");
            // when
            when(loginHttpService.checkNewUser(anyString())).thenReturn(true);
            // then
            mockMvc.perform(post("/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.status").value("NEW_USER"));
        } catch (Exception e) {
            fail("예외 발생: " + e.getMessage()); // 테스트 실패로 처리
        }
    }

    @Test
    @DisplayName("로그인시_null_mainId")
    void checkLoginNullData() {
        try {
            // given
            Map<String, String> request = new HashMap<>();
            request.put("googleId",null);
            // when & then
            mockMvc.perform(post("/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest());
        } catch (Exception e) {
            fail("예외 발생: " + e.getMessage()); // 테스트 실패로 처리
        }
    }

    @Test
    @DisplayName("로그인시_empty_mainId")
    void checkLoginEmptyData() {
        try {
            // given
            Map<String, String> request = new HashMap<>();
            // when & then
            mockMvc.perform(post("/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest());
        } catch (Exception e) {
            fail("예외 발생: " + e.getMessage()); // 테스트 실패로 처리
        }
    }


    @Test
    @DisplayName("신규유저_저장")
    void registerNewUser() {
        try {
            // given
            UserInfoDto userInfoDto = new UserInfoDto("test@email.com",
                    "hi", "2000-00-00", "01089099721");
            // when & then
            mockMvc.perform(post("/new-user")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(userInfoDto)))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            fail("예외 발생: " + e.getMessage()); // 테스트 실패로 처리
        }
    }
}