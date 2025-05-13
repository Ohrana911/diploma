package diploma.management.service.demo.controller;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class InterviewClient {

    private static final String INTERVIEW_API_URL = "https://e98a-34-124-132-2.ngrok-free.app/interview";


    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();

        // Начать интервью
        String startMessage = "{\"message\": \"Привет, проведи со мной техническое интервью по Java.\"}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(startMessage, headers);

//        ResponseEntity<String> response = restTemplate.exchange(INTERVIEW_API_URL + "/start", HttpMethod.POST, entity, String.class);
        ResponseEntity<String> response = restTemplate.exchange(INTERVIEW_API_URL, HttpMethod.POST, entity, String.class);

        System.out.println("Первый вопрос: " + response.getBody());

        // Ответ на первый вопрос
        String userResponse = "{\"message\": \"Мой ответ на первый вопрос...\"}";
        entity = new HttpEntity<>(userResponse, headers);
        response = restTemplate.exchange(INTERVIEW_API_URL + "/next", HttpMethod.POST, entity, String.class);
        System.out.println("Следующий вопрос: " + response.getBody());
    }
}
