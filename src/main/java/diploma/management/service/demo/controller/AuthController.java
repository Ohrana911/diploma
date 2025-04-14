package diploma.management.service.demo.controller;

import diploma.management.service.demo.dto.UserDTO;
import diploma.management.service.demo.entity.UserEntity;
import diploma.management.service.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO) {
        UserEntity user = userService.registerUser(userDTO.getUsername(), userDTO.getPassword());
        return ResponseEntity.ok("Пользователь " + user.getUsername() + " зарегистрирован!");
    }

}
