package uk.ac.ed.bikerental;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.text.html.HTMLDocument.Iterator;

public class SystemTests {
    // You can add attributes here

    ArrayList<BikeRentalShop> providers = new ArrayList();
    Customer cr1 = new Customer("ABCD", "XYZ");
    Customer cr2 = new Customer("1234", "890");

//    BIKE RENTAL SHOPS
    BikeRentalShop brs1;
    BikeRentalShop brs2;
    BikeRentalShop brs3;
    BikeRentalShop brs4;
    BikeRentalShop brs5;

//    BIKES
    Bike b1;
    Bike b2;
    Bike b3;
    Bike b4;
    Bike b5;
    Bike b6;
    Bike b7;
    Bike b8;

//    COLLECTION OF BIKES
    ArrayList<Bike> bikes1;
    ArrayList<Bike> bikes2;
    ArrayList<Bike> bikes3;
    ArrayList<Bike> bikes4;
    ArrayList<Bike> bikes5;

//    DATE RANGES DEFINITION
    DateRange dateRange1;
    DateRange dateRange2;
    DateRange dateRange3;

//    RESERVATIONS
    Reservation r1;
    Reservation r2;
    Reservation r3;

    int noOfReservations;

//    QUOTES RECEIVED
    Set<Quote> actualQuotes;

    MainSystem ms;
    Location l_customer;// location of the customer
    ArrayList<String> bikesReq;// bikes requested

    Booking booking;// Booking a quote

    Request request;

    @BeforeEach
    void setUp() throws Exception {
        // Setup mock delivery service before each tests

//        DATE RANGES
        dateRange1 = new DateRange(LocalDate.of(2019, 1, 7), LocalDate.of(2019, 1, 10));
        dateRange2 = new DateRange(LocalDate.of(2019, 1, 5), LocalDate.of(2019, 1, 23));
        dateRange3 = new DateRange(LocalDate.of(2015, 1, 7), LocalDate.of(2018, 1, 10));

        DeliveryServiceFactory.setupMockDeliveryService();

//        RESERVATIONS      
        r1 = new Reservation(cr1, dateRange1);
        r2 = new Reservation(cr2, dateRange2);
        r3 = new Reservation(cr2, dateRange3);

        noOfReservations = 3;

        ArrayList res1 = new ArrayList();
        res1.add(r1);
        res1.add(r2);

        ArrayList res2 = new ArrayList();
        res2.add(r3);

//        BIKES
       
        b1 = new Bike(new BigDecimal(900), new BigDecimal(20), BikeStatus.AVAILABLE, new BigDecimal(20),
                new BigDecimal(10), LocalDate.of(2012, 1, 1), "Mountain Bike", null);
        b2 = new Bike(new BigDecimal(900), new BigDecimal(15), BikeStatus.AVAILABLE, new BigDecimal(20),
                 new BigDecimal(10), LocalDate.of(2012, 1, 1), "Road Bike", null);

        b3 = new Bike(new BigDecimal(150), new BigDecimal(40), BikeStatus.AVAILABLE, new BigDecimal(10),
                new BigDecimal(10), LocalDate.of(2017, 1, 1), "Hybrid Bike", null);
        b4 = new Bike(new BigDecimal(120), new BigDecimal(15), BikeStatus.AVAILABLE, new BigDecimal(9),
                new BigDecimal(10), LocalDate.of(2015, 1, 1), "Road Bike", null);

        b5 = new Bike(new BigDecimal(150), new BigDecimal(20), BikeStatus.RESERVED, new BigDecimal(11),
                new BigDecimal(12), LocalDate.of(2017, 1, 1), "Mountain Bike", res1);
        b6 = new Bike(new BigDecimal(120), new BigDecimal(15), BikeStatus.RESERVED, new BigDecimal(9),
                new BigDecimal(10), LocalDate.of(2015, 1, 1), "Road Bike", res2);

        b7 = new Bike(new BigDecimal(900), new BigDecimal(20), BikeStatus.RESERVED, new BigDecimal(20),
                new BigDecimal(10), LocalDate.of(2012, 1, 1), "Mountain Bike", res1);
        b8 = new Bike(new BigDecimal(900), new BigDecimal(15), BikeStatus.RESERVED, new BigDecimal(20),
                new BigDecimal(10), LocalDate.of(2012, 1, 1), "Road Bike", res1);

        
//        BIKE RENTAL SHOPS

//        SHOP 1
        bikes1 = new ArrayList();
        bikes1.add(b1);
        bikes1.add(b2);
        Location l1 = new Location("EH2 2PF", "Shop 1, 14 George St, Edinburgh");
        brs1 = new BikeRentalShop("Shop1", l1, "9999999999", "10:00 to 22:00", bikes1, null);

//        SHOP 2
        bikes2 = new ArrayList();
        bikes2.add(b3);
        bikes2.add(b4);
        Location l2 = new Location("EH2 2PF", "Shop2 , 14 George St, Edinburgh");
        brs2 = new BikeRentalShop("Shop2", l2, "888888888", "11:00 to 22:00", bikes2, null);

//        SHOP3
        bikes3 = new ArrayList();
        bikes3.add(b5);
        bikes3.add(b6);
        Location l3 = new Location("EH2 2PF", "Shop3 , 14 George St, Edinburgh");
        brs3 = new BikeRentalShop("Shop3", l3, "777777777", "12:00 to 22:00", bikes3, null);

//        SHOP4
        bikes4 = new ArrayList();
        bikes4.add(b7);
        bikes4.add(b8);
        Location l4 = new Location("LW2 2PF", "Shop4 , 14 George St, Edinburgh");
        brs4 = new BikeRentalShop("Shop4", l4, "6666666666", "13:00 to 22:00", bikes4, null);

//        SHOP5
        bikes5 = new ArrayList();
        bikes5.add(b7);
        bikes5.add(b8);
        Location l5 = new Location("EH2 2PF", "Shop 5, 14 George St, Edinburgh");
        brs5 = new BikeRentalShop("Shop5", l5, "55555555555", "14:00 to 22:00", bikes5, null);

        providers.add(brs1);
        providers.add(brs2);
        providers.add(brs3);
        providers.add(brs4);
        providers.add(brs5);
        // Put your test setup here

        bikesReq = new ArrayList();

        bikesReq.add("Mountain Bike");
        bikesReq.add("Road Bike");
        // bikesReq.add("Hybrid Bike");
        
        // location of the customer
        l_customer = new Location("EH2 2PF", "QuaterMile, Edin");

        ms = new MainSystem(providers);
        ms.setDepreciationMethod("DDBD");
        request = ms.createCustomer("Dan", "Wilks", l_customer, "999999999", bikesReq, dateRange3, "Delivery");
        // quotes returned by the system
        actualQuotes = ms.getQuotes(request);

        DeliveryServiceFactory.setupMockDeliveryService();

        Quote selectedQuote = new Quote(brs1, dateRange3, bikes1, new BigDecimal(35),
                new BigDecimal("184.32"));
        booking = ms.makeBooking(selectedQuote);
    }

    @Test
    void findingAQuote() // FINDING QUOTES =============================================
    {
        int numQuotes = actualQuotes.size();

        assertEquals(numQuotes, 2);// CHECKING IF 2 QUOTES HAVE BEEN RETURNED

        java.util.Iterator<Quote> quote = actualQuotes.iterator();
        for (int i = 0; i < numQuotes; ++i) {
            Quote actualQ = quote.next();
            
            // CHECKING IF THE CORRECT QUOTE HAS BEEN RETURNED
            if (actualQ.getBrs().getName() == "Shop1") {
                assertEquals(actualQ.getBikes().get(0).getType().getTypeName(), "Mountain Bike");
                assertEquals(actualQ.getBikes().get(1).getType().getTypeName(), "Road Bike");

                assertEquals(actualQ.getTotalPrice(), new BigDecimal(35));
                assertEquals(actualQ.getDeposit().setScale(2, RoundingMode.CEILING), new BigDecimal("184.32"));
            } else if (actualQ.getBrs().getName() == "Shop5") {
                assertEquals(actualQ.getBikes().get(0).getType().getTypeName(), "Mountain Bike");
                assertEquals(actualQ.getBikes().get(1).getType().getTypeName(), "Road Bike");

                assertEquals(actualQ.getTotalPrice(), new BigDecimal(35));
                assertEquals(actualQ.getDeposit().setScale(2, RoundingMode.CEILING), new BigDecimal("184.32"));
            } else
                assert (false);// WRONG QUOTE
        }
    }

    @Test
    void bookingAQuote() {// BOOKING A QUOTE =============================================

        assertEquals(booking.getShop().getName(), "Shop1");

        assertEquals(booking.getDeposit().setScale(2, RoundingMode.CEILING), new BigDecimal("184.32"));
        assertEquals(booking.getTotalPrice(), new BigDecimal("35"));

        assertEquals(booking.getBikes().get(0).getType().getTypeName(), "Mountain Bike");
        assertEquals(booking.getBikes().get(1).getType().getTypeName(), "Road Bike");

        // Checking if the status of the bikes has been changed or not
        assertEquals(booking.getBikes().get(0).isStatus(), BikeStatus.RESERVED);
        assertEquals(booking.getBikes().get(1).isStatus(), BikeStatus.RESERVED);

        assertEquals((booking.getOrderNumber() + 3), (noOfReservations + 1));
        // +3 as we already have 3 reservations from before to test our getQuotes()
        // function

        MockDeliveryService deliveryService = (MockDeliveryService) DeliveryServiceFactory.getDeliveryService();
        Collection<Deliverable> deliverablesList = deliveryService.getPickupsOn(booking.getDateRange().getStart());
        System.out.println(deliverablesList);
        assertEquals(deliverablesList.size(), 1);
    }

    @Test
    public void returningBikes() {// RETURNING OF BIKES =============================================
        BikeRentalShop partnerBrs = brs1;

        ms.returnBikes(booking.getOrderNumber(), partnerBrs);

        // checking the status 0f the bikes after they've been returned to the Original provider
        if (booking.getShop().getName().equals(partnerBrs.getName())) {
            for (Bike bike : booking.getBikes())
                assertEquals(bike.isStatus(), BikeStatus.AVAILABLE);
        } 
        else // checking the status 0f the bikes after they've been returned to the partner provider
        {
            for (Bike bike : booking.getBikes())
                assertEquals(bike.isStatus(), BikeStatus.PARTNER_TO_ORIGPROVIDER);
        }
    }
}