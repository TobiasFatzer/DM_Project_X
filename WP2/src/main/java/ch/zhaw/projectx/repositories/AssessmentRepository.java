package ch.zhaw.projectx.repositories;

import ch.zhaw.projectx.entities.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface AssessmentRepository extends JpaRepository<Assessment, Long> {

    @Query("SELECT ass FROM Assessment ass WHERE ass.reportingDate = ?1")
    public List<Assessment> findAssessmentByReportingDate(Date reportingDate);

    //TODO fix this
    @Query(value = "SELECT b1.team_id, b2.team_id FROM Assessment b1, Assessment b2 WHERE b1.performance_id = b2.performance_id AND b1.id < b2.id ORDER BY b1.team_id", nativeQuery = true)
    List<Object[]> findEdgesList();
}
