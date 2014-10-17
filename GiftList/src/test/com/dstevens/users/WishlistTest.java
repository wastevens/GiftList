package com.dstevens.users;

import static com.dstevens.collections.Lists.list;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.junit.Test;

import com.dstevens.gifts.*;
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
    
    @Test
    public void testThatAsViewedByOwnerStripsOutComments() {
        Gift gift1 = new Gift("gift 1");
        Gift gift2 = new Gift("gift 2");
        User user = mock(User.class);
        
        Wishlist wishlist = new Wishlist();
        wishlist.addGift(gift1);
        wishlist.addGift(gift2);
        wishlist.commentOn(gift1, user, "Some comment");
        wishlist.commentOn(gift1, user, "Another comment");
        wishlist.commentOn(gift2, user, "Yet another comment");
        
        assertEquals(list(gift1, gift2), wishlist.asViewedByOwner().getGifts());
        assertEquals(list(), wishlist.asViewedByOwner().getGifts().get(0).getComments());
        assertEquals(list(), wishlist.asViewedByOwner().getGifts().get(1).getComments());
    }
    
    @Test
    public void testThatAsViewedByFriendLeavesCommentsIn() {
        Gift gift1 = new Gift("gift 1");
        Gift gift2 = new Gift("gift 2");
        User user1 = mock(User.class);
        User user2 = mock(User.class);
        
        Wishlist wishlist = new Wishlist();
        wishlist.addGift(gift1);
        wishlist.addGift(gift2);
        wishlist.commentOn(gift1, user1, "Some comment");
        wishlist.commentOn(gift1, user2, "Another comment");
        wishlist.commentOn(gift2, user1, "Yet another comment");
        
        assertEquals(list(gift1, gift2), wishlist.asViewedByFriend().getGifts());
        assertEquals(list(new GiftComment(user1, "Some comment"), new GiftComment(user2, "Another comment")), 
                     wishlist.asViewedByFriend().getGifts().get(0).getComments());
        assertEquals(list(new GiftComment(user1, "Yet another comment")), 
                     wishlist.asViewedByFriend().getGifts().get(1).getComments());
    }
    
}
