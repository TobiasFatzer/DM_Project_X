package ch.zhaw.projectx.entities;

import ch.zhaw.projectx.util.Metric;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("aggregation")
public class Aggregation extends Performance {

    private Metric metric;

    public Aggregation() {
        super();
    }

    public Metric getMetric() {
        return metric;
    }

    public void setMetric(Metric metric) {
        this.metric = metric;
    }
}
