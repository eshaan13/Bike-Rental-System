package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class QuoteController {

    private ArrayList<BikeRentalShop> providers = new ArrayList();
    private Set<Quote> matchingQuotes = new HashSet<Quote>();
    private String depreciationMethod;

    public QuoteController(ArrayList<BikeRentalShop> providers2) {
        this.providers = providers2;
    }

    // Valuation policy implemented by the contractor
    public void setDepreciationMethod(String depreciationMethod) {
        this.depreciationMethod = depreciationMethod;
    }

    public Set<Quote> findMatchingQuotes(Request request) {
        // Iterating over the providers
        for (BikeRentalShop shop : providers) {
            // checking if the bike provider is near the customer
            if (shop.getAddress().isNearTo(request.getLocation()))// CHECKING IF SHOP IS NEAR THE CUSTOMER
            {
                Quote quote = null;
                ArrayList<Bike> stock = shop.getStock();
                ArrayList<Bike> matchingBikes = new ArrayList();
                boolean allBikesAvailable = false;
                
                // iterating over the bike types requested by the customer
                label: for (String bikeType : request.getBikes())// bikes requested
                {
                    for (Bike bike : stock)// bikes at the bike provider
                    {
                        if (bike.getType().getTypeName().equals(bikeType)) {

                            boolean thereAreNoOverlaps = true;
                            // checking for reservations of the bikes
                            if (bike.isStatus() == BikeStatus.RESERVED)
                                for (int i = 0; i < bike.getReservations().size(); ++i)// reservations for the matched
                                                                                       // bikeType
                                {
                                    Reservation res = bike.getReservations().get(i);
                                    // checking for the overlap of dateRanges
                                    if (request.getDateRange().overlaps(res.getDateRange()))// checking for overlaps
                                    {
                                        allBikesAvailable = false;
                                        break label;
                                    }
                                }
                            matchingBikes.add(bike);
                        }
                    }
                }
                // checking if all the bikes are available from a bike provider 
                if (matchingBikes.size() == request.getBikes().size()) {
                    BigDecimal totalDeposit = new BigDecimal(0.0);
                    BigDecimal totalPrice = new BigDecimal(0.0);
                    
                    // implementing the ValuationPolicy
                    for (Bike matchedBike : matchingBikes) {
                        if (depreciationMethod.equals("LD"))// Linear Depreciation
                        {
                            ValuationPolicyLD ld = new ValuationPolicyLD(matchedBike.getDepreciationRate());
                            BigDecimal newValue = ld.calculateValue(matchedBike, request.getDateRange().getStart());
                            totalDeposit = totalDeposit
                               .add(newValue.multiply(matchedBike.getDepositRate().divide(new BigDecimal(100))));

                        } else if (depreciationMethod.equals("DDBD"))// Double Declining Balance Depreciation
                        {
                            ValuationPolicyDDBD ddbd = new ValuationPolicyDDBD(matchedBike.getDepreciationRate());
                            LocalDate startDate = request.getDateRange().getStart();
                            BigDecimal newValue = ddbd.calculateValue(matchedBike, startDate);
                            totalDeposit = totalDeposit
                               .add(newValue.multiply(matchedBike.getDepositRate().divide(new BigDecimal(100))));
                        } else if (depreciationMethod.equals("NULL"))// No depreciation
                        {
                            BigDecimal newValue = matchedBike.getDepositRate().divide(new BigDecimal(100))
                                    .multiply(matchedBike.getType().getReplacementValue());
                            totalDeposit = totalPrice.add(newValue);
                        }

                        totalPrice = totalPrice.add(matchedBike.getDailyRentalPrice());// calculation of total price
                    }
                    // creating a quote from a bike provider
                    quote = new Quote(shop, request.getDateRange(), matchingBikes,
                                    totalPrice, totalDeposit);
                    matchingQuotes.add(quote);
                }
            }
        }
        // returning the matching quotes
        return matchingQuotes;
    }
}