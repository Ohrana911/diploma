package diploma.management.service.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
@PreAuthorize("isAuthenticated()") // только для авторизованных пользователей
public class ViewController {

    @GetMapping("/userpage")
    public String showUserPage() {
        return "userpage"; // user.html из templates будет отрендерен
    }
}
