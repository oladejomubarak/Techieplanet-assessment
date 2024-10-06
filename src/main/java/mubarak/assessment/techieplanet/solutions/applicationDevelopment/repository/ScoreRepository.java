package mubarak.assessment.techieplanet.solutions.applicationDevelopment.repository;

import mubarak.assessment.techieplanet.solutions.applicationDevelopment.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ScoreRepository  extends JpaRepository<Score, Long> {
    List<Score> findByStudentId(Long studentId);
}
