package app.app.user.Service;

import app.app.user.DTO.UserDTO;
import app.app.user.Repository.UserRepository;
import app.app.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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
        user.setPassword(passwordEncoder.encode(userDTO.getPassword())); // 비밀번호 암호화
        user.setPhoneNumber(userDTO.getPhoneNumber());

        // 사용자 저장
        userRepository.save(user);
        return "회원가입 성공";
    }

    public String updateUser(Long userId, UserDTO userDTO) {
        Optional<User> existingUser = userRepository.findById(userId);
        if (!existingUser.isPresent()) {
            return "사용자를 찾을 수 없습니다.";
        }

        User user = existingUser.get();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());

        // 필요한 경우 비밀번호 업데이트
        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }

        userRepository.save(user);
        return "사용자 정보 업데이트 성공";
    }

    public String deleteUser(Long userId) {
        Optional<User> existingUser = userRepository.findById(userId);
        if (!existingUser.isPresent()) {
            return "사용자를 찾을 수 없습니다.";
        }

        userRepository.delete(existingUser.get());
        return "사용자 삭제 성공";
    }

    public Optional<UserDTO> getUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            UserDTO userDTO = new UserDTO();
            userDTO.setName(user.get().getName());
            userDTO.setEmail(user.get().getEmail());
            userDTO.setPhoneNumber(user.get().getPhoneNumber());
            return Optional.of(userDTO);
        }
        return Optional.empty();
    }
}
