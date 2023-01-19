package ch.zhaw.projectx.repositories;

import ch.zhaw.projectx.entities.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PerformanceRepository extends JpaRepository<Performance, Long> {

    @Query("SELECT pe FROM Performance pe WHERE pe.name = ?1")
    public List<Performance> findPerformanceByName(String name);

}
