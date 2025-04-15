package diploma.management.service.demo.repository;

import diploma.management.service.demo.entity.InterviewMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterviewMessageRepository extends JpaRepository<InterviewMessage, Long> {
    List<InterviewMessage> findByInterviewSessionId(Long sessionId);
}

