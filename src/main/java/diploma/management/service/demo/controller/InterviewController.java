package diploma.management.service.demo.controller;

import diploma.management.service.demo.service.FlaskInterviewService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/interview")
public class InterviewController {

    private final FlaskInterviewService flaskInterviewService;

    public InterviewController(FlaskInterviewService flaskInterviewService) {
        this.flaskInterviewService = flaskInterviewService;
    }

    @PostMapping("/start")
    public String startInterview(@RequestBody String userMessage) {
        return flaskInterviewService.startInterview(userMessage);
    }
}
