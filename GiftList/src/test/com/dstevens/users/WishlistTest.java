package com.dstevens.users;

import static com.dstevens.collections.Lists.list;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.dstevens.gifts.Gift;
import com.dstevens.testing.EqualityTester;

public class WishlistTest {

    @Test
    public void testEquals() {
        Gift gift1 = new Gift("gift 1");
        Gift gift2 = new Gift("gift 2");
        Gift anotherGift1 = new Gift("gift 1");
        Gift anotherGift2 = new Gift("gift 2");
        Gift gift3 = new Gift("gift 3");
        
        EqualityTester.testing(Wishlist.with(list(gift1, gift2))).
        assertEqualTo(Wishlist.with(list(gift1, gift2))).
        assertEqualTo(Wishlist.with(list(gift2, gift1))).
        assertEqualTo(Wishlist.with(list(anotherGift1, anotherGift2))).
        assertNotEqualTo(Wishlist.with(list(gift1, gift2, gift3))).
        assertNotEqualTo(Wishlist.with(list(gift1))).
        assertNotEqualTo(Wishlist.with(list(gift2, gift3))).
        assertNotEqualTo("Not a WishList");
    }
    
    @Test
    public void testAddingAndRemovingGifts() {
        Gift gift1 = new Gift("gift 1");
        Gift gift2 = new Gift("gift 2");
        Gift gift3 = new Gift("gift 3");
        
        Wishlist wishList = new Wishlist();
        
        List<Gift> noGifts = list();
        assertEquals(Wishlist.with(noGifts), wishList);
        
        wishList.addGift(gift1);
        wishList.addGift(gift2);
        assertEquals(Wishlist.with(list(gift1, gift2)), wishList);
        
        wishList.removeGift(gift2);
        wishList.addGift(gift3);
        
        assertEquals(Wishlist.with(list(gift1, gift3)), wishList);
    }
    
}
