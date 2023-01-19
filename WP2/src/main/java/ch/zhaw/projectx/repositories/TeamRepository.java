package ch.zhaw.projectx.repositories;

import ch.zhaw.projectx.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query("SELECT te FROM Team te WHERE te.name = ?1")
    public List<Team> findTeamByName(String name);

    @Query("SELECT te FROM Team te WHERE te.size = ?1")
    public List<Team> findTeamsBySize(int size);

    @Query("SELECT te.id, te.name FROM Team te")
    public List<Object[]> findNodes();
}
