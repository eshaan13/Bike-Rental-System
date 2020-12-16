package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

public class Booking implements Deliverable {

    private int orderNumber;
    private String orderSummary;
    private String deliveryMode;
    private BigDecimal deposit;
    private BigDecimal totalPrice;
    private Location pickupLoc;
    private Location dropOffLoc;
    private ArrayList<Bike> bikes;
    private DateRange dateRange;
    private BikeRentalShop shop;
    private BookingStatus bookingStatus;

    @Override
    public void onPickup() {// updating the booking status on pickup of the bike
                            // by the delivery driver

        bookingStatus = BookingStatus.PICKED_UP;
    }

    @Override
    public void onDropoff() {// updating the booking status on dropping off the bike

        bookingStatus = BookingStatus.DROPPED_OFF;
    }

    public Location getPickupLoc() {
        return pickupLoc;
    }

    public void setPickupLoc(Location pickupLoc) {
        this.pickupLoc = pickupLoc;
    }

    public Location getDropOffLoc() {
        return dropOffLoc;
    }

    public void setDropOffLoc(Location dropOffLoc) {
        this.dropOffLoc = dropOffLoc;
    }

    public DateRange getDateRange() {
        return dateRange;
    }

    public void setDateRange(DateRange dateRange) {
        this.dateRange = dateRange;
    }

    public void setShop(BikeRentalShop shop) {
        this.shop = shop;
    }

    public BikeRentalShop getShop() {
        return shop;
    }

    public Booking() {
        this.orderNumber = 0;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderSummary() {
        return orderSummary;
    }

    public void setOrderSummary(String orderSummary) {
        this.orderSummary = orderSummary;
    }

    public String getDeliveryMode() {
        return deliveryMode;
    }

    public void setDeliveryMode(String deliveryMode) {
        this.deliveryMode = deliveryMode;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public ArrayList<Bike> getBikes() {
        return bikes;
    }

    public void setBikes(ArrayList<Bike> bikes) {
        this.bikes = bikes;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}