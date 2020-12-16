package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;

public class Bike {

    private BikeType bt;
    private BigDecimal dailyRentalPrice;
    private BikeStatus status;
    private BigDecimal depositRate;
    private BigDecimal deposit;
    private BigDecimal depreciationRate;
    private BigDecimal depreciation;
    private LocalDate purchaseDate;
    private ArrayList<Reservation> reservations = new ArrayList(); // reservations for the bike

    public Bike(BigDecimal replacementValue, BigDecimal dailyRentalPrice, BikeStatus status, BigDecimal depositRate,
            BigDecimal depreRate, LocalDate purchaseDate, String bike_type, ArrayList<Reservation> reservations) {
        bt = new BikeType(replacementValue, bike_type);
        this.dailyRentalPrice = dailyRentalPrice;
        this.status = status;
        this.depositRate = depositRate;
        this.depreciationRate = depreRate;
        this.purchaseDate = purchaseDate;
        if (reservations != null)
            this.reservations = reservations;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public BigDecimal getDailyRentalPrice() {
        return dailyRentalPrice;
    }

    public void setDailyRentalPrice(BigDecimal dailyRentalPrice) {
        this.dailyRentalPrice = dailyRentalPrice;
    }

    public BikeStatus isStatus() {
        return status;
    }

    public void setStatus(BikeStatus status) {
        this.status = status;
    }

    public BigDecimal getDepositRate() {
        return depositRate;
    }

    public BigDecimal getDepreciation() {
        return depreciation;
    }

    public void setDepreciation(BigDecimal depreciation) {
        this.depreciation = depreciation;
    }

    public void setBt(BikeType bt) {
        this.bt = bt;
    }

    public long calcAge(LocalDate startDate) {
        long age = ChronoUnit.YEARS.between(purchaseDate, startDate);
        return age;
    }

    public BigDecimal getDepreciationRate() {
        return depreciationRate;
    }

    public void setDepreciationRate(BigDecimal depreciationRate) {
        this.depreciationRate = depreciationRate;
    }

    public void setDepositRate(BigDecimal depositRate) {
        this.depositRate = depositRate;
    }

    public BikeType getType() {
        if (bt == null)
            assert false;
        return bt;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public ArrayList<Reservation> getReservations() {
        return reservations;
    }

    public void addReservation(Reservation reservation)
    {
        reservations.add(reservation);// add a reservation
    }
}
