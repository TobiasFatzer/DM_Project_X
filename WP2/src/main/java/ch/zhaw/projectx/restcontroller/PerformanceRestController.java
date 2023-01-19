package ch.zhaw.projectx.restcontroller;

import ch.zhaw.projectx.entities.Performance;
import ch.zhaw.projectx.repositories.PerformanceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
public class PerformanceRestController {
    private final PerformanceRepository repository;

    public PerformanceRestController(PerformanceRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "api/performance", method = RequestMethod.GET)
    public ResponseEntity<List<Performance>> getPerformances() {
        List<Performance> result = this.repository.findAll();

        if (!result.isEmpty()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "api/performance/{valueId}", method = RequestMethod.GET)
    public ResponseEntity<Optional<Performance>> getAggregationById(@PathVariable("valueId") Long valueId) {
        Optional<Performance> result = this.repository.findById(valueId);
        if (result.isPresent()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "api/performance/{name}", method = RequestMethod.GET)
    public ResponseEntity<List<Performance>> getPerformanceByValue(@PathVariable("name") String name) {
        List<Performance> result = this.repository.findPerformanceByName(name);

        if (!result.isEmpty()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
