package com.dstevens.gifts;

import org.junit.Test;

import com.dstevens.testing.EqualityTester;

public class GiftTest {
    
    @Test
    public void testEquals() {
        String giftDescription = "some gift";
        String anotherGiftDescription = "another gift";
        
        EqualityTester.testing(new Gift(giftDescription)).
        assertEqualTo(new Gift(giftDescription)).
        assertNotEqualTo(new Gift(anotherGiftDescription)).
        assertNotEqualTo("Not a UserTest");
    }
}
