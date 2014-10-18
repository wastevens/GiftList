package com.dstevens.gifts;

import static com.dstevens.collections.Lists.*;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.dstevens.testing.EqualityTester;

public class WishTest {

    @Test
    public void testGetDescription() {
        assertEquals("a wish", new Wish("a wish").getDescription());
        assertEquals("another wish", new Wish("another wish").getDescription());
    }
    
    @Test
    public void testEquals() throws Exception {
        EqualityTester.testing(new Wish("wish")).
                       assertEqualTo(new Wish("wish")).
                       assertNotEqualTo(new Wish("another wish")).
                       assertNotEqualTo("Not a wish");
    }
    
    @Test
    public void testNaturalOrdering() {
        assertEquals(list(new Wish("1"), new Wish("2"), new Wish("3")), 
                sort(list(new Wish("2"), new Wish("3"), new Wish("1"))));
    }
    
}
