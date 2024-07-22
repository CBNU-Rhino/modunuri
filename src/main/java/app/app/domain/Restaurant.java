package app.app.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "restaurants")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String location;

    @ManyToOne
    @JoinColumn(name = "tourist_spot_id")
    private TouristSpot touristSpot; // 외래 키

    public Restaurant() {
    }

    public Restaurant(Long id, String name, String description, String location, TouristSpot touristSpot) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.touristSpot = touristSpot;
    }

    // Getter와 Setter 생략
}