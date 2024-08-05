package app.app.user.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserDTO {

    private Long id;

    // 생성자, 게터 및 세터
    @NotBlank(message = "이름은 필수입니다.")
    private String username;


    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "유효한 이메일 형식이 아닙니다.")
    private String email;


    @NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min = 6, message = "비밀번호는 최소 6자 이상이어야 합니다.")
    private String password;

    @NotBlank(message = "전화번호는 필수입니다.")
    @Size(min = 8, message = "전화번호를 형식에 맞게 입력하세요.")
    private String phoneNumber; // 전화번호

}
