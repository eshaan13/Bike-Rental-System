package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ValuationPolicyDDBD implements ValuationPolicy {

    private BigDecimal depreciation;
    private BigDecimal depreciationRate;

    public ValuationPolicyDDBD(BigDecimal depreciationRate) {
        this.depreciationRate = depreciationRate;
    }

    public BigDecimal getDepreciationRate() {
        return depreciationRate;
    }

    public void setDepreciationRate(BigDecimal depreciationRate) {
        this.depreciationRate = depreciationRate;
    }

    @Override
    public BigDecimal calculateValue(Bike bike, LocalDate date) {

        BigDecimal replaceValue = bike.getType().getReplacementValue();
        depreciationRate = depreciationRate.multiply(new BigDecimal("0.01"));
        // calculating the age of the bike
        long age = bike.calcAge(date);
        BigDecimal x;
        BigDecimal reducedBy = depreciationRate.multiply(new BigDecimal("2"));
        x = (new BigDecimal("1").subtract(reducedBy)).pow((int) age);
        depreciation = replaceValue.multiply(x);
        return depreciation;
    }

}
