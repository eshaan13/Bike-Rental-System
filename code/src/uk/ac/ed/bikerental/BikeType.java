package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Objects;

public class BikeType {

    private BigDecimal replacementValue;
    private String type;

    public BikeType(String type) {
        this.type = type;
    }

    public BikeType(BigDecimal replacementValue, String type) {
        this.replacementValue = replacementValue;
        this.type = type;
    }

    public String getTypeName() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BikeType(BigDecimal vp) {
        replacementValue = vp;
    }

    public BigDecimal getReplacementValue() {
        return replacementValue;
    }
}