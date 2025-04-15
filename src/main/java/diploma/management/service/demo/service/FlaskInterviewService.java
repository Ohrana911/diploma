package diploma.management.service.demo.service;

import diploma.management.service.demo.dto.Message;
import diploma.management.service.demo.dto.ChatRequest;
import diploma.management.service.demo.dto.ChatResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class FlaskInterviewService {

    @Value("${flask.api.url}")
    private String flaskApiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public String startInterview(String userInput) {
        Message userMessage = new Message("user", userInput);
        ChatRequest request = new ChatRequest(
                Collections.singletonList(userMessage),
                0.7,
                300
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ChatRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<ChatResponse> response = restTemplate.exchange(
                flaskApiUrl,
                HttpMethod.POST,
                entity,
                ChatResponse.class
        );

        return response.getBody().getChoices().get(0).getMessage().getContent();
    }
}

