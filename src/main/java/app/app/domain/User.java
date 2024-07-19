package app.app.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name= "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") //@Colum == 내부변수 정의
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name ="Email")
    private String email;

    @Column(name ="profile_image_url") //프로필 이미지
    private String profile_image_url;

    @Builder //
    public User(String username, String password, String email, String profile_image_url) {
        this.username = username;
        this.password = password;
        this.email=email;
        this.profile_image_url = profile_image_url;
    }

    //편의 메서드
    public void update(String username, String password, String email) {
        this.username=username;
        this.password = password;
        this.email = email;
    }
}
