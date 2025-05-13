package diploma.management.service.demo.repository;

import diploma.management.service.demo.entity.InterviewResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterviewResultRepository extends JpaRepository<InterviewResult, Long> {
    List<InterviewResult> findByUsernameOrderByTimestampAsc(String username);
}

