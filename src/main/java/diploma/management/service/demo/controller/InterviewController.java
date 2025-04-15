package diploma.management.service.demo.controller;

import diploma.management.service.demo.dto.ChatRequest;
import diploma.management.service.demo.entity.InterviewMessage;
import diploma.management.service.demo.service.FlaskInterviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interview")
public class InterviewController {

    private final FlaskInterviewService flaskInterviewService;

    public InterviewController(FlaskInterviewService flaskInterviewService) {
        this.flaskInterviewService = flaskInterviewService;
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
}
