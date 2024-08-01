package app.app.user.Controller;

import app.app.user.DTO.UserDTO;
import app.app.user.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users") // API 엔드포인트 경로 설정
public class userController {

    @Autowired
    private UserService userService; // 사용자 서비스

    // 사용자 등록 API
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDto) {
        try {
            userService.registerUser(userDto);
            return new ResponseEntity<>("사용자 등록 성공", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("사용자 등록 실패: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user")
    public String getUser(@AuthenticationPrincipal OAuth2User principal) {
        if (principal != null) {
            String name = principal.getAttribute("name");
            String email = principal.getAttribute("email");
            return "사용자 이름: " + name + ", 이메일: " + email;
        }
        return "사용자 정보가 없습니다.";
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess(@AuthenticationPrincipal OAuth2User principal) {
        if (principal != null) {
            return "로그인 성공: " + principal.getAttribute("name");
        }
        return "로그인 실패";
    }

    @GetMapping("/loginFailure")
    public String loginFailure() {
        return "로그인 실패: 잘못된 자격 증명.";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // 사용자 로그인 서비스 호출
        String token = userService.login(loginRequest.getEmail(), loginRequest.getPassword());

        if (token != null) {
            return ResponseEntity.ok(new LoginResponse(token)); // 로그인 성공 시 토큰 반환
        } else {
            return ResponseEntity.status(401).body("잘못된 자격 증명입니다."); // 로그인 실패 시
        }
    }
}
