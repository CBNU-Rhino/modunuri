package app.app.touristSpot.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TouristSpotDTO {
    private Long id;

    @NotBlank(message = "관광지 이름은 필수입니다.")
    private String name;

    private String description;

    @NotBlank(message = "위치는 필수입니다.")
    private String location;

    private String imageUrl;
}