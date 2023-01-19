package ch.zhaw.projectx.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("indicator")
public class Indicator extends Performance {

    private int targetValue;

    public Indicator() {
        super();
    }

    public int getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(int indicatorValue) {
        this.targetValue = indicatorValue;
    }
}