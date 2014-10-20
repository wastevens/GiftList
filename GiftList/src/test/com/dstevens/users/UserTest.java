package com.dstevens.users;

import org.junit.Test;

import com.dstevens.testing.EqualityTester;

import static com.dstevens.collections.Lists.list;

import static org.junit.Assert.assertEquals;

public class UserTest {

    @Test
    public void testEquals() {
        String emailAddress= "some email address";
        String anotherEmailAddress= "another email address";
        
        EqualityTester.testing(new User(new UserIdentifier(1), emailAddress)).
        assertEqualTo(new User(new UserIdentifier(1), emailAddress)).
        assertNotEqualTo(new User(new UserIdentifier(2), anotherEmailAddress)).
        assertNotEqualTo(new User(new UserIdentifier(1), anotherEmailAddress)).
        assertNotEqualTo("Not a UserTest");
    }
    
    @Test
    public void testFriends() {
        User me = new User(new UserIdentifier(1), "my email");
        UserIdentifier alice = new UserIdentifier(2);
        UserIdentifier bob = new UserIdentifier(3);
        UserIdentifier charlie = new UserIdentifier(4);
        
        me.addFriend(alice);
        me.addFriend(bob);
        
        assertEquals(list(alice, bob), me.getFriends());
        
        me.removeFriend(alice);
        me.addFriend(charlie);
        
        assertEquals(list(bob, charlie), me.getFriends());
    }
    
    @Test
    public void testThatFriendsAreNotAddedMultipleTimes() {
        User me = new User(new UserIdentifier(1), "my email");
        UserIdentifier alice = new UserIdentifier(2);
        UserIdentifier bob = new UserIdentifier(3);
        
        me.addFriend(alice);
        me.addFriend(bob);
        
        assertEquals(list(alice, bob), me.getFriends());
        
        me.addFriend(alice);
        
        assertEquals(list(alice, bob), me.getFriends());
    }
    
    @Test
    public void testThatRemovingFriendsWhoAreNotPresentDoesNotError() {
        User me = new User(new UserIdentifier(1), "my email");
        UserIdentifier alice = new UserIdentifier(2);
        UserIdentifier bob = new UserIdentifier(3);
        
        me.addFriend(alice);
        me.addFriend(bob);
        
        assertEquals(list(alice, bob), me.getFriends());
        
        me.removeFriend(alice);
        assertEquals(list(bob), me.getFriends());
        
        me.removeFriend(alice);
        assertEquals(list(bob), me.getFriends());
    }
}
