package uk.ac.ed.bikerental;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MainSystem {

    private ArrayList<BikeRentalShop> providers;
    private Map<Integer, Booking> bookings = new HashMap();
    private Customer customer;
    private Request request;
    private Quote selectedQuote;
    private String depreciationMethod;
    private Set<Quote> matchingQuotes;

    public MainSystem(ArrayList<BikeRentalShop> providers2) {
        this.providers = providers2;
        request = new Request(null, null, null, "");
        selectedQuote = new Quote(null, null, null, null, null);
    }

    public String getDepreciationMethod() {
        return depreciationMethod;
    }

    public void setDepreciationMethod(String depreciationMethod) {
        this.depreciationMethod = depreciationMethod;
    }

    // creating a customer and a request based on his rental needs
    public Request createCustomer(String f_name, String l_name, Location addr, String phNo, ArrayList<String> bikeTypes,
            DateRange dateRange, String modeOfCollection) {
        customer = new Customer(f_name, l_name, addr, phNo, bikeTypes, dateRange, modeOfCollection);
        request = customer.getRequest();
        return request;
    }

    // quotes getting created in the QuoteController class
    public Set<Quote> getQuotes(Request request) {
        QuoteController controller = new QuoteController(providers);
        controller.setDepreciationMethod(depreciationMethod);// setting the depreciation method
        matchingQuotes = controller.findMatchingQuotes(request);
        return matchingQuotes;
    }

    public Booking selectQuote(Quote selectedQuote) {
        // CUSTOMER SELECTS A QUOTE AND PROCEEDS TO BOOKING

        Booking bookingMade = makeBooking(selectedQuote);
        if (bookingMade.getOrderNumber() != 0) {

            Reservation reservation = new Reservation(customer, selectedQuote.getDateRange());

            // changing the status of the bikes to reserved
            for (Bike b : selectedQuote.getBikes()) {
                b.setStatus(BikeStatus.RESERVED);// CHANGING STATUS TO RESERVE
                // mapping a booking to the provider
                selectedQuote.getBrs().addReservation(reservation, b);
            }

            return bookingMade;
        }
        return null;// NO BOOKING HAS BEEN MADE
    }

    public Booking makeBooking(Quote selectedQuote) {
        Booking booking = new Booking();
        booking.setDateRange(selectedQuote.getDateRange());
        booking.setShop(selectedQuote.getBrs());
        booking.setBikes(selectedQuote.getBikes());
        booking.setDeliveryMode(request.getModeOfCollection());
        booking.setDeposit(selectedQuote.getDeposit());
        booking.setOrderNumber(bookings.size() + 1);
        booking.setTotalPrice(selectedQuote.getTotalPrice());

        // Adding reservations to the bikes booked and changing there status to RESERVED
        Reservation reservation = new Reservation(customer, selectedQuote.getDateRange());
        for (Bike bike : selectedQuote.getBikes()) {
            bike.addReservation(reservation);
            bike.setStatus(BikeStatus.RESERVED);
        }

        // the customer has request for a delivery
        if (request.getModeOfCollection().equals("Delivery")) {
            booking.setDropOffLoc(customer.getAddress());
            booking.setPickupLoc(selectedQuote.getBrs().getAddress());

            // scheduling a delivery
            DeliveryService deliveryService = DeliveryServiceFactory.getDeliveryService();
            deliveryService.scheduleDelivery(booking, selectedQuote.getBrs().getAddress(), customer.getAddress(),
                    selectedQuote.getDateRange().getStart());
        }

        // adding booking to the collection of bookings
        bookings.put(booking.getOrderNumber(), booking);
        return booking;
    }

    public void returnBikes(int orderNo, BikeRentalShop shop) {

        // RETURN THE DEPOSIT
        if (!bookings.containsKey(orderNo))
            assert (false);// No booking in the database

        Booking bookingReturned = bookings.get(orderNo);

        if (bookingReturned.getShop().getName().equals(shop.getName())) // Bike returned to original bike provider
        {
            for (Bike bike : bookingReturned.getBikes())
                bike.setStatus(BikeStatus.AVAILABLE);
        } else // Bike is at partner and is being delivered to original provider
        {
            for (Bike bike : bookingReturned.getBikes())
                bike.setStatus(BikeStatus.PARTNER_TO_ORIGPROVIDER);

            // SCHEDULING A DELIVERY TO THE ORIGINAL PROVIDER
            DeliveryService deliveryService = DeliveryServiceFactory.getDeliveryService();
            deliveryService.scheduleDelivery(bookingReturned, shop.getAddress(), bookingReturned.getShop().getAddress(),
                    bookingReturned.getDateRange().getEnd());
        }
    }

}