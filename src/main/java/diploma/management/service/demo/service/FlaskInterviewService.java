package diploma.management.service.demo.service;

import diploma.management.service.demo.dto.Message;
import diploma.management.service.demo.dto.ChatRequest;
import diploma.management.service.demo.dto.ChatResponse;
import diploma.management.service.demo.entity.InterviewMessage;
import diploma.management.service.demo.entity.InterviewSession;
import diploma.management.service.demo.repository.InterviewMessageRepository;
import diploma.management.service.demo.repository.InterviewSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class FlaskInterviewService {

    @Value("${flask.api.url}")
    private String flaskApiUrl;

    private final RestTemplate restTemplate = new RestTemplate();
    private final InterviewSessionRepository interviewSessionRepository;
    private final InterviewMessageRepository interviewMessageRepository;

    @Autowired
    public FlaskInterviewService(InterviewSessionRepository interviewSessionRepository, InterviewMessageRepository interviewMessageRepository) {
        this.interviewSessionRepository = interviewSessionRepository;
        this.interviewMessageRepository = interviewMessageRepository;
    }

//    public String startInterview(Long sessionId, String userInput) {
//        InterviewSession session = getOrCreateSession(sessionId);
//
//        Message userMessage = new Message("user", userInput);
//        InterviewMessage interviewMessage = new InterviewMessage(session, userMessage.getContent(), "user");
//        interviewMessageRepository.save(interviewMessage);
//
//        ChatRequest request = new ChatRequest(
//                Collections.singletonList(userMessage),
//                0.7,
//                300
//        );
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity<ChatRequest> entity = new HttpEntity<>(request, headers);
//
//        ResponseEntity<ChatResponse> response = restTemplate.exchange(
//                flaskApiUrl,
//                HttpMethod.POST,
//                entity,
//                ChatResponse.class
//        );
//
////        String botResponse = response.getBody().getChoices().get(0).getMessage().getContent();
//        String botResponse = response.getBody().getResponse();
//
//
//        InterviewMessage botMessage = new InterviewMessage(session, botResponse, "bot");
//        interviewMessageRepository.save(botMessage);
//
//        return botResponse;
//    }

    public String startInterview(Long sessionId, String userInput) {
        InterviewSession session = getOrCreateSession(sessionId);

        // Сохраняем сообщение пользователя
        InterviewMessage userMessage = new InterviewMessage(session, userInput, "user");
        interviewMessageRepository.save(userMessage);

        // Готовим JSON с полем "message"
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

//        String jsonPayload = String.format("{\"message\": \"%s\"}", userInput);
        String jsonPayload = String.format("{\"message\": \"%s\", \"sessionId\": \"%s\"}", userInput, session.getId());
        HttpEntity<String> entity = new HttpEntity<>(jsonPayload, headers);

        ResponseEntity<ChatResponse> response = restTemplate.exchange(
                flaskApiUrl,
                HttpMethod.POST,
                entity,
                ChatResponse.class
        );

        String botResponse = response.getBody().getResponse();


        InterviewMessage botMessage = new InterviewMessage(session, botResponse, "bot");
        interviewMessageRepository.save(botMessage);

        return botResponse;
    }

    private String extractResponse(String json) {
        // Простейший способ вытащить строку из {"response": "..."}
        int start = json.indexOf(":\"") + 2;
        int end = json.lastIndexOf("\"");
        return json.substring(start, end);
    }


    private InterviewSession getOrCreateSession(Long sessionId) {
        InterviewSession session;
        if (sessionId != null) {
            session = interviewSessionRepository.findById(sessionId)
                    .orElseThrow(() -> new RuntimeException("Session not found"));
        } else {
            session = new InterviewSession(); // Create a new session if none exists
            interviewSessionRepository.save(session);
        }
        return session;
    }

    public List<InterviewMessage> getSessionMessages(Long sessionId) {
        return interviewMessageRepository.findByInterviewSessionId(sessionId);
    }
}


