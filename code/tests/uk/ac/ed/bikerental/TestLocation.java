package uk.ac.ed.bikerental;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class TestLocation {
	
	Location l1;
	Location l2;
	Location l3;
	
    @BeforeEach
    void setUp() throws Exception {
        // TODO: setup some resources before each test
    	l1 = new Location("EH2 2PF", "WhyNot Night Club, 14 George St, Edinburgh");
    	l2 = new Location("EH8 9YL", "Old College, South Bridge, Edinburgh");
    	l3 = new Location("W1F 7TF", "ToyRoom Night Club, London");
    }
    
 // TODO: put some tests here
    
    @Test
    public void Test_isNearTo1()
    {
    	assertTrue(l1.isNearTo(l2));
    }
    
    @Test
    public void Test_isNearTo2()
    {
    	assertFalse(l2.isNearTo(l3));
    }
    
    
    
}
