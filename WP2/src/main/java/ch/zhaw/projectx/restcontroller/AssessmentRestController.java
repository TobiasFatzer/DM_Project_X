package ch.zhaw.projectx.restcontroller;

import ch.zhaw.projectx.entities.Assessment;
import ch.zhaw.projectx.repositories.AssessmentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;
import java.util.Optional;


@RestController
public class AssessmentRestController {
    private final AssessmentRepository repository;

    public AssessmentRestController(AssessmentRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "api/assessment", method = RequestMethod.GET)
    public ResponseEntity<List<Assessment>> getAssessments() {
        List<Assessment> result = this.repository.findAll();

        if (!result.isEmpty()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(value = "api/assessment/{reportingDate}", method = RequestMethod.GET)
    public ResponseEntity<List<Assessment>> getAssessmentByReportingDate(
            @PathVariable("reportingDate") Date reportingDate) {
        List<Assessment> result = this.repository.findAssessmentByReportingDate(reportingDate);

        if (!result.isEmpty()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "api/assessment/{reportingDateId}", method = RequestMethod.GET)
    public ResponseEntity<Optional<Assessment>> getAggregationById(@PathVariable("reportingDateId") Long reportingDateId) {
        Optional<Assessment> result = this.repository.findById(reportingDateId);
        if (result.isPresent()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "api/assessment/edges", method = RequestMethod.GET)
    public ResponseEntity<List<Object[]>> getEdges() {
        List<Object[]> result = this.repository.findEdgesList();

        if (!result.isEmpty()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
