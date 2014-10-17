package com.dstevens.users;

import static com.dstevens.collections.Lists.list;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import org.junit.Test;

import com.dstevens.gifts.*;
import com.dstevens.testing.EqualityTester;

public class UserTest {

    @Test
    public void testEquals() {
        String emailAddress= "some email address";
        String anotherEmailAddress= "another email address";
        
        EqualityTester.testing(new User(emailAddress)).
        assertEqualTo(new User(emailAddress)).
        assertNotEqualTo(new User(anotherEmailAddress)).
        assertNotEqualTo("Not a UserTest");
    }
    
    @Test
    public void testFriends() {
        User me = new User("my email");
        User alice = new User("Alice's email");
        User bob = new User("Bob's email");
        User charlie = new User("Charlie's email");
        
        me.addFriend(alice);
        me.addFriend(bob);
        
        assertEquals(list(alice, bob), me.getFriends());
        
        me.removeFriend(alice);
        me.addFriend(charlie);
        
        assertEquals(list(bob, charlie), me.getFriends());
    }
    
    @Test
    public void testThatFriendsAreNotAddedMultipleTimes() {
        User me = new User("my email");
        User alice = new User("Alice's email");
        User bob = new User("Bob's email");
        
        me.addFriend(alice);
        me.addFriend(bob);
        
        assertEquals(list(alice, bob), me.getFriends());
        
        me.addFriend(alice);
        
        assertEquals(list(alice, bob), me.getFriends());
    }
    
    @Test
    public void testThatRemovingFriendsWhoAreNotPresentDoesNotError() {
        User me = new User("my email");
        User alice = new User("Alice's email");
        User bob = new User("Bob's email");
        
        me.addFriend(alice);
        me.addFriend(bob);
        
        assertEquals(list(alice, bob), me.getFriends());
        
        me.removeFriend(alice);
        assertEquals(list(bob), me.getFriends());
        
        me.removeFriend(alice);
        assertEquals(list(bob), me.getFriends());
    }
    
    @Test
    public void testAddingAndRemovingGifts() {
        User me = new User("my email");
        Gift bike = new Gift("A new bike");
        Gift playstation = new Gift("PS4");
        Gift bbgun = new Gift("BB gun");
        
        me.addGiftToWishlist(bike);
        me.addGiftToWishlist(playstation);
        
        assertEquals(Wishlist.with(list(bike, playstation)), me.getMyWishlist());
        
        me.removeGiftFromWishlist(playstation);
        me.addGiftToWishlist(bbgun);
        
        assertEquals(Wishlist.with(list(bike, bbgun)), me.getMyWishlist());
    }
    
    @Test
    public void testThatUsersDoNotSeeCommentsOnTheirOwnWishlist() {
        User me = new User("me");
        Gift gift1 = new Gift("gift 1");
        Gift gift2 = new Gift("gift 2");
        User user = mock(User.class);
        gift1.addComment(user, "a comment");
        gift1.addComment(user, "another comment");
        gift2.addComment(user, "another comment");
        
        me.addGiftToWishlist(gift1);
        me.addGiftToWishlist(gift2);
        
        assertEquals(list(gift1, gift2), me.getMyWishlist().getGifts());
        assertEquals(list(), me.getMyWishlist().getGifts().get(0).getComments());
        assertEquals(list(), me.getMyWishlist().getGifts().get(1).getComments());
    }
    
    @Test
    public void testThatUsersDoSeeCommentsOnOtherUsersWishlists() {
        User myFriend = new User("my friend");
        Gift gift1 = new Gift("gift 1");
        Gift gift2 = new Gift("gift 2");
        User user1 = mock(User.class);
        User user2 = mock(User.class);
        gift1.addComment(user1, "a comment");
        gift1.addComment(user2, "another comment");
        gift2.addComment(user2, "yet another comment");
        
        myFriend.addGiftToWishlist(gift1);
        myFriend.addGiftToWishlist(gift2);
        
        assertEquals(list(gift1, gift2), myFriend.getWishlistForOthers().getGifts());
        assertEquals(list(new GiftComment(user1, "a comment"), new GiftComment(user2, "another comment")), 
                     myFriend.getWishlistForOthers().getGifts().get(0).getComments());
        assertEquals(list(new GiftComment(user2, "yet another comment")), 
                     myFriend.getWishlistForOthers().getGifts().get(1).getComments());
    }
}
