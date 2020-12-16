package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class ValuationPolicyLD implements ValuationPolicy {

    public ValuationPolicyLD(BigDecimal depreciationRate) {
        this.depreciationRate = depreciationRate;
    }

    private BigDecimal depreciation;
    private BigDecimal depreciationRate;

    public BigDecimal getDepreciationRate() {
        return depreciationRate;
    }

    public void setDepreciationRate(BigDecimal depreciationRate) {
        this.depreciationRate = depreciationRate;
    }

    @Override
    public BigDecimal calculateValue(Bike bike, LocalDate date) {
        // TODO Auto-generated method stub

        depreciationRate = depreciationRate.multiply(new BigDecimal(0.01));
        BigDecimal replaceValue = bike.getType().getReplacementValue();
        long age = bike.calcAge(date);
        depreciation = replaceValue.subtract
                (replaceValue.multiply(new BigDecimal(age)).multiply(depreciationRate));
        depreciation = depreciation.setScale(2, RoundingMode.CEILING);
        return depreciation;
    }

}
