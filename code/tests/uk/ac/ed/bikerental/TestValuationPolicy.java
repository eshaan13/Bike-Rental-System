package uk.ac.ed.bikerental;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.Before;

public class TestValuationPolicy {
    // You can add attributes here
	
	BigDecimal expectedLD;
	BigDecimal expectedDDBD;
	
	LocalDate purchaseDate;
	LocalDate date;
	
	Bike bike;
	BigDecimal rv;
	BigDecimal d_rental;
	BigDecimal dep_rate_temp;
	BigDecimal dep_rate;
	BigDecimal deposit_rate;
	

    @Before
    public void setUp() throws Exception {
        // Put setup here
    	rv = new BigDecimal("900");
    	d_rental = new BigDecimal("500");
    	dep_rate = new BigDecimal("10");
    	deposit_rate = new BigDecimal("20");
    	
    	date = LocalDate.now();
    	purchaseDate = LocalDate.of(2016, 1, 1);
    	
    	expectedLD = new BigDecimal("630.00");
    	expectedDDBD = new BigDecimal("460.80");
    	
    	bike = new Bike(rv, d_rental, BikeStatus.AVAILABLE, deposit_rate, dep_rate, purchaseDate, "Mountain Bike", null);
    }
    
    // TODO: Write tests for valuation policies
    
    @Test
    public void calculateValueTestLD()
    {
    	ValuationPolicyLD vp1 = new ValuationPolicyLD(dep_rate);
    	BigDecimal actualLD = vp1.calculateValue(bike, date);
    	assertEquals(actualLD, expectedLD); 	
    }
    
    @Test
    public void claculateValueTestDDBD()
    {
    	ValuationPolicyDDBD vp2 = new ValuationPolicyDDBD(dep_rate);
    	BigDecimal actualDDBD = vp2.calculateValue(bike, date);
    	assertEquals(actualDDBD.stripTrailingZeros(), expectedDDBD.stripTrailingZeros());
    }
    
    
}
