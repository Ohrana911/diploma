package diploma.management.service.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    // Страница с графиком результатов интервью
    @GetMapping("/interview/results/page")
    public String showInterviewResultsPage() {
        return "interview_results"; // будет искать шаблон interview_results.html в templates/
    }

    // Можешь добавить сюда и другие страницы позже (например, домашнюю, профиль и т.д.)
}
