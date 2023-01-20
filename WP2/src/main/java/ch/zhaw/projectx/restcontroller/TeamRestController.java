package ch.zhaw.projectx.restcontroller;

import ch.zhaw.projectx.entities.Team;
import ch.zhaw.projectx.repositories.TeamRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
public class TeamRestController {
    private final TeamRepository repository;

    public TeamRestController(TeamRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "api/team", method = RequestMethod.GET)
    public ResponseEntity<List<Team>> getTeams() {
        List<Team> result = this.repository.findAll();

        if (!result.isEmpty()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "api/team/byName/{name}", method = RequestMethod.GET)
    public ResponseEntity<List<Team>> getTeamByName(@PathVariable("name") String name) {
        List<Team> result = this.repository.findTeamByName(name);

        if (!result.isEmpty()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "api/team/byId/{id}", method = RequestMethod.GET)
    public ResponseEntity<Optional<Team>> getAggregationById(@PathVariable("id") Long teamId) {
        Optional<Team> result = this.repository.findById(teamId);
        if (result.isPresent()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "api/team/nodes", method = RequestMethod.GET)
    public ResponseEntity<List<Object[]>> getNodes() {
        List<Object[]> result = this.repository.findNodes();

        if (!result.isEmpty()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
