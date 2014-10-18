package com.dstevens.users;

import static com.dstevens.collections.Lists.list;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

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
        UserIdentifier alice = new UserIdentifier("Alice's email");
        UserIdentifier bob = new UserIdentifier("Bob's email");
        UserIdentifier charlie = new UserIdentifier("Charlie's email");
        
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
        UserIdentifier alice = new UserIdentifier("Alice's email");
        UserIdentifier bob = new UserIdentifier("Bob's email");
        
        me.addFriend(alice);
        me.addFriend(bob);
        
        assertEquals(list(alice, bob), me.getFriends());
        
        me.addFriend(alice);
        
        assertEquals(list(alice, bob), me.getFriends());
    }
    
    @Test
    public void testThatRemovingFriendsWhoAreNotPresentDoesNotError() {
        User me = new User("my email");
        UserIdentifier alice = new UserIdentifier("Alice's email");
        UserIdentifier bob = new UserIdentifier("Bob's email");
        
        me.addFriend(alice);
        me.addFriend(bob);
        
        assertEquals(list(alice, bob), me.getFriends());
        
        me.removeFriend(alice);
        assertEquals(list(bob), me.getFriends());
        
        me.removeFriend(alice);
        assertEquals(list(bob), me.getFriends());
    }
}
