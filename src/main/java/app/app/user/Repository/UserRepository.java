package app.app.user.Repository;

import app.app.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 이메일로 사용자 조회하는 메서드
    User findByEmail(String email);
}
