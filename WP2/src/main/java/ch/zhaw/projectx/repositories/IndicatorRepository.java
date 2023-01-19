package ch.zhaw.projectx.repositories;

import ch.zhaw.projectx.entities.Indicator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IndicatorRepository extends JpaRepository<Indicator, Long> {

    @Query("SELECT pe FROM Indicator pe WHERE pe.targetValue = ?1")
    public List<Indicator> findIndicatorByIndicatorTargetValue(int targetValue);
}
