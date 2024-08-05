package app.app.user.Controller;

import app.app.user.DTO.UserDTO;
import app.app.user.Repository.UserRepository;
import app.app.user.entity.User;
import app.app.user.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class userController {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {
        try {
            // UserDTO를 사용하여 사용자 등록
            User newUser = userService.registerUser(
                    userDTO.getUsername(),
                    userDTO.getPassword(),
                    userDTO.getEmail(),
                    userDTO.getPhoneNumber()
            );
            return ResponseEntity.ok(newUser); // 성공적으로 등록된 사용자 반환
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // 오류 메시지 반환
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        User user = userService.login(userDTO.getUsername(), userDTO.getPassword());
        if (user != null) {
            return ResponseEntity.ok(user); // 로그인 성공 시 사용자 정보 반환
        } else {
            return ResponseEntity.status(401).body("이메일 또는 비밀번호가 잘못되었습니다."); // 로그인 실패 시 오류 메시지 반환
        }
    }


}
