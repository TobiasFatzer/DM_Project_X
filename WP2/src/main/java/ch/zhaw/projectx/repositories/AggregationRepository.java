package ch.zhaw.projectx.repositories;

import ch.zhaw.projectx.entities.Aggregation;
import ch.zhaw.projectx.util.Metric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AggregationRepository extends JpaRepository<Aggregation, Long> {

    @Query("SELECT ag FROM Aggregation ag WHERE ag.metric = ?1")
    public List<Aggregation> findAggregationByMetric(Metric metric);
}
