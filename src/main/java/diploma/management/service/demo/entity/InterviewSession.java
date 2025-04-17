package diploma.management.service.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class InterviewSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId; // идентификатор пользователя
    @Column(nullable = false)
    private String status = "NEW"; // Дефолтное значение // статус интервью (например, "IN_PROGRESS", "COMPLETED")
    private LocalDateTime startTime; // время начала
    private LocalDateTime endTime; // время окончания
    @Column(nullable = false)
    private String sessionToken; // токен сессии для продолжения диалога (если нужно)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }
}

