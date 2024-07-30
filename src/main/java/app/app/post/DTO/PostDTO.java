package app.app.post.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private Long id;

    @NotBlank(message = "제목은 필수입니다.")
    private String title;

    @NotBlank(message = "내용은 필수입니다.")
    private String content;

    @NotNull(message = "관광지 ID는 필수입니다.")
    private Long touristSpotId;

    private String author; // 작성자 이름

    private String createdAt; // 작성일

    private String updatedAt; // 수정일

    private String location;
}
