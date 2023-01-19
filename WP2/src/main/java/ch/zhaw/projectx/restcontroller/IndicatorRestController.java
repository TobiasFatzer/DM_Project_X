package ch.zhaw.projectx.restcontroller;

import ch.zhaw.projectx.entities.Indicator;
import ch.zhaw.projectx.repositories.IndicatorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
public class IndicatorRestController {
    private final IndicatorRepository repository;

    public IndicatorRestController(IndicatorRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "api/indicators", method = RequestMethod.GET)
    public ResponseEntity<List<Indicator>> getIndicators() {
        List<Indicator> result = this.repository.findAll();

        if (!result.isEmpty()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "api/indicators/{targetValueId}", method = RequestMethod.GET)
    public ResponseEntity<Optional<Indicator>> getAggregationById(@PathVariable("targetValueId") Long targetValueId) {
        Optional<Indicator> result = this.repository.findById(targetValueId);

        if (result.isPresent()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "api/indicators/{targetValue}", method = RequestMethod.GET)
    public ResponseEntity<List<Indicator>> getIndicatorByName(@PathVariable("targetValue") int targetValue) {
        List<Indicator> result = this.repository.findIndicatorByIndicatorTargetValue(targetValue);

        if (!result.isEmpty()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
