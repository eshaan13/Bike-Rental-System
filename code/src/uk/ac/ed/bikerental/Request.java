package uk.ac.ed.bikerental;

import java.util.ArrayList;
import java.util.Collection;

public class Request {

    private ArrayList<String> bikes;
    private DateRange dateRange;
    private Location location;
    private String modeOfCollection;

    public Request(ArrayList<String> bikes, DateRange dateRange, Location location,
                String modeOfCollection) {
        this.bikes = bikes;
        this.dateRange = dateRange;
        this.location = location;
        this.modeOfCollection = modeOfCollection;
    }

    public String getModeOfCollection() {
        return modeOfCollection;
    }

    public ArrayList<String> getBikes() {
        return bikes;
    }

    public DateRange getDateRange() {
        return dateRange;
    }

    public Location getLocation() {
        return location;
    }

}
