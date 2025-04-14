package diploma.management.service.demo.controller;

import jakarta.annotation.PostConstruct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/interview")
public class InterviewController {

    @PostConstruct
    public void init() {
        System.out.println("Контроллер загружен!");
    }


    @GetMapping
    public String getChatPage(Model model) {
        return "chat";  // chat.html из templates
    }

    @PostMapping("/ask")
    @ResponseBody
    public ResponseEntity<String> ask(@RequestBody String userInput) {
        // Пока что фейковый ответ. Позже заменим на вызов модели.
        String reply = "Вы сказали: " + userInput + "\n(здесь будет ответ ИИ)";
        return ResponseEntity.ok(reply);
    }
}
