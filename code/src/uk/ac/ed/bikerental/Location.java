package uk.ac.ed.bikerental;

/**
The Location class represents a spatial location described by a 
post code and a street address.
@author Daniel Wilks
@author Eshaan Manglik
*/

public class Location {
    
    /**
    postcode keeps track of the postal address
    */
    private String postcode;
    /**
    address keeps track of the postal address
    */
    private String address;
    
    /**
    
    Creates an instance of the Location class.
    @param postcode     the specific postal address of the location.
    @param address      the position that a BikeRentalShop is situated.
    */
    public Location(String postcode, String address) {
        assert postcode.length() >= 6;
        this.postcode = postcode;
        this.address = address;
    }
    
    /**
    Determines whether this location is near to another location. 
    It does this by comparing the first two characters of each 
    postcode.
    @param other    A location that is being compared with this 
                    location.
    @return         true if this location is near to 'other' 
                    location.
    */

    public boolean isNearTo(Location other) {
        
        boolean firstCharIsSame = other.postcode.charAt(0) == this.postcode.charAt(0);
        boolean secondCharIsSame = other.postcode.charAt(1) == this.postcode.charAt(1);
        
        return firstCharIsSame && secondCharIsSame;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getAddress() {
        return address;
    }
    
}