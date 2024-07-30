package app.app.user.Service;

import app.app.user.DTO.UserDTO;
import app.app.user.Repository.UserRepository;
import app.app.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String registerUser(UserDTO userDTO) {
        // 사용자 중복 체크
        Optional<User> existingUser = userRepository.findByEmail(userDTO.getEmail());
        if (existingUser.isPresent()) {
            return "이미 존재하는 이메일입니다.";
        }

        // User 엔티티로 변환
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword()); // 실제로는 비밀번호 암호화 필요
        user.setPhoneNumber(userDTO.getPhoneNumber());

        // 사용자 저장
        userRepository.save(user);
        return "회원가입 성공";
    }
}
