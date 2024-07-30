package app.app.touristSpot;

import jakarta.persistence.*;

@Entity
@Table(name = "tourist_spots")
public class TouristSpot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private boolean hasFacility;
    private boolean hasNursingRoom;
    private String location;

    public TouristSpot() {
    }

    public TouristSpot(Long id, String name, String description, boolean hasFacility, String location) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.hasFacility = hasFacility;
        this.location = location;
    }

    // Getter와 Setter 생략
}
