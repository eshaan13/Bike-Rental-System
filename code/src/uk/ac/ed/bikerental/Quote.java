package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

public class Quote {

    private BikeRentalShop shop;
    private DateRange dateRange;
    private BigDecimal totalPrice;
    private BigDecimal deposit;
    private ArrayList<Bike> bikes = new ArrayList();

    public Quote(BikeRentalShop shop, DateRange dateRange, ArrayList<Bike> matchingBikes,
            BigDecimal totalPrice, BigDecimal deposit) {
        this.shop = shop;
        this.dateRange = dateRange;
        this.totalPrice = totalPrice;
        this.deposit = deposit;
        this.bikes = matchingBikes;
    }

    public ArrayList<Bike> getBikes() {
        return bikes;
    }

    public BikeRentalShop getBrs() {
        return shop;
    }

    public DateRange getDateRange() {
        return dateRange;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

}