package app.app.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;
    private String phoneNumber;

    // 기본 생성자
    public User() {}

    // 생성자
    public User(String username, String password, String email,String phoneNumber) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber=phoneNumber;
    }

}
