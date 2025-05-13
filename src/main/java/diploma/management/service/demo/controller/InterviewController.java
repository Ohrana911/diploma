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

import java.time.LocalDateTime;
import java.util.List;

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

    // Начало нового интервью или продолжение существующего
//    @PostMapping("/start")
//    public String startInterview(@RequestParam(value = "sessionId", required = false) Long sessionId,
//                                 @RequestBody String userMessage) {
//        // Вызов сервиса Flask для обработки сообщения
//        return flaskInterviewService.startInterview(sessionId, userMessage);
//    }

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

    // 🔥 Новый метод завершения интервью
    @PostMapping("/finish")
    public ResponseEntity<ChatResponse> finishInterview(Authentication authentication) {
        log.info("Метод finishInterview вызван");
        String username = authentication.getName();
        log.info("Завершение интервью для пользователя: {}", username);
        // Сюда подставь, если хочешь вытянуть оценку из Flask:
        // double score = flaskInterviewService.getScoreForUser(username);
        double score = Math.random() * 100; // пока просто рандомный score
        log.info("Оценка интервью: {}", score);

        InterviewResult result = new InterviewResult();
        result.setUsername(username);
        result.setScore(score);
        result.setTimestamp(LocalDateTime.now());  // если ты добавил поле timestamp

        interviewResultRepository.save(result);
        log.info("Результат интервью сохранен в базе данных");

        ChatResponse response = new ChatResponse();
        response.setResponse("Интервью завершено. Оценка: " + score);
        return ResponseEntity.ok(response);
    }



    @GetMapping("/results")
    public ResponseEntity<List<InterviewResult>> getInterviewResults(Authentication authentication) {
        String username = authentication.getName();
        List<InterviewResult> results = interviewResultRepository.findByUsernameOrderByTimestampAsc(username);
        return ResponseEntity.ok(results);
    }

}
