package uk.ac.ed.bikerental;

import java.util.ArrayList;
import java.util.Collection;

public class DeliverableBooking implements Deliverable {

    private String deliveryMode;
    private Collection<Bike> bikes;
    private Location pickupLoc;
    private Location dropOffLoc;
    
    public DeliverableBooking(String deliveryMode, Collection<Bike> bikes, Location pickupLoc, Location dropOffLoc) 
    {
		this.deliveryMode = deliveryMode;
		this.bikes = bikes;
		this.pickupLoc = pickupLoc;
		this.dropOffLoc = dropOffLoc;
	}
	
    
	public Location getDropOffLoc() {
        return dropOffLoc;
    }
    public void setDropOffLoc(Location dropOffLoc) {
        this.dropOffLoc = dropOffLoc;
    }
    public void setDeliveryMode(String deliveryMode) {
        this.deliveryMode = deliveryMode;
    }
    public void setBikes(Collection<Bike> bikes) {
        this.bikes = bikes;
    }
	public Location getPickupLoc() {
		return pickupLoc;
	}
	public void setPickupLoc(Location location) {
		this.pickupLoc = location;
	}
	public String getDeliveryMode() {
		return deliveryMode;
	}
	public Collection<Bike> getBikes() {
		return bikes;
	}
	public void setBikes(ArrayList<Bike> bikes) {
		this.bikes = bikes;
	}
	

}