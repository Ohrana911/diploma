package diploma.management.service.demo.entity;

import jakarta.persistence.*;

@Entity
public class InterviewMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "interview_session_id")
    private InterviewSession interviewSession;

    @Column(columnDefinition = "TEXT") // Указываем, что это будет тип TEXT
    private String role;
    @Column(columnDefinition = "TEXT") // Указываем, что это будет тип TEXT
    private String content;

    // Конструктор для создания объекта сообщения
    public InterviewMessage(InterviewSession interviewSession, String role, String content) {
        this.interviewSession = interviewSession;
        this.role = role;
        this.content = content;
    }

    // Геттеры и сеттеры

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InterviewSession getInterviewSession() {
        return interviewSession;
    }

    public void setInterviewSession(InterviewSession interviewSession) {
        this.interviewSession = interviewSession;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
