package app.app.user.Service;


import app.app.user.entity.User;
import app.app.user.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User registerUser(String username, String password, String email, String phoneNumber) {
        // 이메일 중복 체크
        if (userRepository.findByEmail(email) != null) {
            throw new RuntimeException("이미 사용 중인 이메일입니다.");
        }

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(password);
        User newUser = new User(username, encodedPassword, email,phoneNumber);
        return userRepository.save(newUser); // 사용자 저장
    }

    public User login(String email, String password) { //로그인 구현
        User user = userRepository.findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user; // 인증 성공
        } else {
            return null; // 인증 실패
        }
    }
}
