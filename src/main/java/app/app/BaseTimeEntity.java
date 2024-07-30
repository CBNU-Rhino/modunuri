package app.app;

import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import jakarta.persistence.Column;

@MappedSuperclass
public abstract class BaseTimeEntity {
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // 기본 생성자
    public BaseTimeEntity() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // 게터 및 세터 생략
}
