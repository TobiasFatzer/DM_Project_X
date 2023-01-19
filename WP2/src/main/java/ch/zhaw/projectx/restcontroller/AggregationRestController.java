package ch.zhaw.projectx.restcontroller;

import ch.zhaw.projectx.entities.Aggregation;
import ch.zhaw.projectx.repositories.AggregationRepository;
import ch.zhaw.projectx.util.Metric;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class AggregationRestController {
    private final AggregationRepository repository;

    public AggregationRestController(AggregationRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "api/aggregations", method = RequestMethod.GET)
    public ResponseEntity<List<Aggregation>> getAggregations() {
        List<Aggregation> result = this.repository.findAll();

        if (!result.isEmpty()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "api/aggregations/{metric}", method = RequestMethod.GET)
    public ResponseEntity<List<Aggregation>> getAggregationByName(@PathVariable("metric") Metric metric) {
        List<Aggregation> result = this.repository.findAggregationByMetric(metric);

        if (!result.isEmpty()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "api/aggregations/{metricId}", method = RequestMethod.GET)
    public ResponseEntity<Optional<Aggregation>> getAggregationById(@PathVariable("metricId") Long metricId) {
        Optional<Aggregation> result = this.repository.findById(metricId);

        if (result.isPresent()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
