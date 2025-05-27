package diploma.management.service.demo.controller;

import diploma.management.service.demo.dto.ChatRequest;
import diploma.management.service.demo.dto.ChatResponse;
import diploma.management.service.demo.entity.InterviewMessage;
import diploma.management.service.demo.entity.InterviewResult;
import diploma.management.service.demo.repository.InterviewResultRepository;
import diploma.management.service.demo.service.FlaskInterviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j // Ломбок для логирования
@RestController
@RequestMapping("/api/interview")
public class InterviewController {

    private final FlaskInterviewService flaskInterviewService;
    private final InterviewResultRepository interviewResultRepository;

    public InterviewController(FlaskInterviewService flaskInterviewService,
                               InterviewResultRepository interviewResultRepository) {
        this.flaskInterviewService = flaskInterviewService;
        this.interviewResultRepository = interviewResultRepository;
    }


    @PostMapping
    public String startInterview(@RequestParam(value = "sessionId", required = false) Long sessionId,
                                 @RequestBody ChatRequest request) {
        return flaskInterviewService.startInterview(sessionId, request.getMessage());
    }

    // Получить все сообщения из сессии
    @GetMapping("/session/{sessionId}")
    public List<InterviewMessage> getSessionMessages(@PathVariable Long sessionId) {
        return flaskInterviewService.getSessionMessages(sessionId);
    }


//    @PostMapping("/finish")
//    public ResponseEntity<ChatResponse> finishInterview(Authentication authentication) {
//        log.info("Метод finishInterview вызван");
//        String username = authentication.getName();
//
//        try {
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//
//            Map<String, String> request = new HashMap<>();
//            request.put("sessionId", "default"); // Или другой ID, если есть
//
//            HttpEntity<Map<String, String>> entity = new HttpEntity<>(request, headers);
//
//            ResponseEntity<Map> flaskResponse = new RestTemplate()
//                    .postForEntity("https://c117-34-139-46-77.ngrok-free.app/interview/finish", entity, Map.class);
//
//            double score = Double.parseDouble(flaskResponse.getBody().get("score").toString());
//            String recommendations = flaskResponse.getBody().get("recommendations").toString();
//
//            System.out.println("DEBUG: Сохраняем результат — score = " + score);
//
//            InterviewResult result = new InterviewResult();
//            result.setUsername(username);
//            result.setScore(score);
//            result.setTimestamp(LocalDateTime.now());
//            result.setRecommendations(recommendations); // добавь это поле в сущность!
//            interviewResultRepository.save(result);
//
//
//            ChatResponse response = new ChatResponse();
//            response.setResponse("Интервью завершено. Оценка: " + score + "\n\nРекомендации:\n" + recommendations);
//            return ResponseEntity.ok(response);
//
//        } catch (Exception e) {
//            log.error("Ошибка при завершении интервью: ", e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }

    @PostMapping("/finish")
    public ResponseEntity<Map<String, Object>> finishInterview(Authentication authentication) {
        log.info("Метод finishInterview вызван");
        String username = authentication.getName();

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, String> request = new HashMap<>();
            request.put("sessionId", "default");

            HttpEntity<Map<String, String>> entity = new HttpEntity<>(request, headers);

            ResponseEntity<Map> flaskResponse = new RestTemplate()
                    .postForEntity("https://5b5d-34-87-126-212.ngrok-free.app/interview/finish", entity, Map.class);

            double score = Double.parseDouble(flaskResponse.getBody().get("score").toString());
            String recommendations = flaskResponse.getBody().get("recommendations").toString();

            InterviewResult result = new InterviewResult();
            result.setUsername(username);
            result.setScore(score);
            result.setTimestamp(LocalDateTime.now());
            result.setRecommendations(recommendations);
            interviewResultRepository.save(result);

            // ⬇️ возвращаем нужный формат:
            Map<String, Object> response = new HashMap<>();
            response.put("score", score);
            response.put("recommendations", recommendations);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            log.error("Ошибка при завершении интервью: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }




    @GetMapping("/results")
    public ResponseEntity<List<InterviewResult>> getInterviewResults(Authentication authentication) {
        String username = authentication.getName();
        List<InterviewResult> results = interviewResultRepository.findByUsernameOrderByTimestampAsc(username);
        return ResponseEntity.ok(results);
    }

}
