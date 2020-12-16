package uk.ac.ed.bikerental;

import java.util.Collection;

public class Reservation {

    private Customer customer;
    private DateRange dateRange;

    public Reservation(Customer customer, DateRange dateRange) {
        this.dateRange = dateRange;
        this.customer = customer;

    }

    public Customer getCustomer() {
        return customer;
    }

    public DateRange getDateRange() {
        return dateRange;
    }

}
