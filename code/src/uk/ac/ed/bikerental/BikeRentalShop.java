package uk.ac.ed.bikerental;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BikeRentalShop {

    public String name;
    public Location address;
    public String phNumber;
    public String openingHrs;
    protected ArrayList<Bike> stock;
    protected ArrayList<BikeRentalShop> partners;
    protected Map<Bike, Set<Reservation>> bikeToReservations = new HashMap<>();

    public BikeRentalShop(String name, Location address, String phNumber, String openingHrs, ArrayList<Bike> bikes1,
            ArrayList<BikeRentalShop> partners) {
        super();
        this.name = name;
        this.address = address;
        this.phNumber = phNumber;
        this.openingHrs = openingHrs;
        this.stock = bikes1;
        this.partners = partners;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getAddress() {
        return address;
    }

    public void setAddress(Location address) {
        this.address = address;
    }

    public String getPhNumber() {
        return phNumber;
    }

    public void setPhNumber(String phNumber) {
        this.phNumber = phNumber;
    }

    public String getOpeningHrs() {
        return openingHrs;
    }

    public void setOpeningHrs(String openingHrs) {
        this.openingHrs = openingHrs;
    }

    public ArrayList<Bike> getStock() {
        return stock;
    }

    public void setStock(ArrayList<Bike> stock) {
        this.stock = stock;
    }

    public Collection<BikeRentalShop> getPartners() {
        return partners;
    }

    public void setPartners(ArrayList<BikeRentalShop> partners) {
        this.partners = partners;
    }

    public void addReservation(Reservation resToAdd, Bike bike)// mapping a booking to the
                                                               // bike provider
    {
        Set<Reservation> bikeRes = bikeToReservations.get(bike);
        bikeToReservations.remove(bike);
        bikeRes.add(resToAdd);
        bikeToReservations.put(bike, bikeRes);
    }

    public void addBike(Bike newBike) {
        stock.add(newBike);
    }
}
