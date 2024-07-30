package app.app.user;

import app.app.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "user")
@NoArgsConstructor(access = AccessLevel.PUBLIC) //기본생성자를 생성해줌
@Getter
@Setter
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "profile_image_url")
    private String profile_image_url;

    @Column(name = "phonenumber")
    private String Phone_Number;

    @Builder
    public User(String username, String password, String email, String Phone_Number, String profile_image_url) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.profile_image_url = profile_image_url;
        this.Phone_Number = Phone_Number;
    }


    // 편의 메서드
    public void update(String username, String password, String email, String Phone_Number) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.Phone_Number = Phone_Number;
    }

    public void setName(String name) {
        this.username = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.Phone_Number = phoneNumber;
    }
}
