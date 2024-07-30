package app.app.restaurant;

import app.app.touristSpot.TouristSpot;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자
@AllArgsConstructor // 모든 필드를 포함하는 생성자
@Getter // Getter 메소드 생성
@Setter // Setter 메소드 생성
@Table(name = "restaurants")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //id
    private String name; //이름
    private String description; //설명
    private String location; //위치
    private String phoneNumber; // 식당 번호
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "tourist_spot_id")
    private TouristSpot touristSpot; // 외래 키
}
