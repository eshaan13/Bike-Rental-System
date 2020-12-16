package uk.ac.ed.bikerental;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Customer {

    protected String firstName;
    protected String lastName;
    protected Location address;
    protected String phNumber;
    public Set<Quote> matchingQuotes;
    public Quote selectedQuote;
    protected Request request;
    public ArrayList<String> bikesTypes;
    public DateRange dateRange;
    protected String modeOfCollection;
    // private Location location;

    public Customer(String firstName, String lastName, Location address, String phNumber, ArrayList<String> bikes,
            DateRange dateRange, String modeOfCollection) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phNumber = phNumber;
        this.bikesTypes = bikes;
        this.dateRange = dateRange;
        this.modeOfCollection = modeOfCollection;
        // this.location = location;
    }

    public Customer(String fName, String lName) {
        firstName = fName;
        lastName = lName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Set<Quote> getMatchingQuotes() {
        return matchingQuotes;
    }

    public Quote getSelectedQuote() {
        return selectedQuote;
    }

    public Request getRequest() {// creating a request on the bases of the rental needs of the customer
        request = new Request(bikesTypes, dateRange, address, modeOfCollection);
        return request;
    }
}
