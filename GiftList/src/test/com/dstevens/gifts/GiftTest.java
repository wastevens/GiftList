package com.dstevens.gifts;

import static com.dstevens.collections.Lists.list;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import org.junit.*;
import org.mockito.*;

import com.dstevens.testing.EqualityTester;
import com.dstevens.users.UserIdentifier;

public class GiftTest {

    @Mock private Wish wish_1;
    @Mock private Wish wish_2;
    private GiftComment commentWithoutState;
    private GiftComment commentWithClaimedState;
    private GiftComment commentWithPurchasedState;
    private GiftComment commentWithRemovedState;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
        UserIdentifier userIdentifier = mock(UserIdentifier.class);
        commentWithoutState = new GiftComment(userIdentifier, "comment");
        commentWithClaimedState = new GiftComment(userIdentifier, "comment", GiftState.CLAIMED);
        commentWithPurchasedState = new GiftComment(userIdentifier, "comment", GiftState.PURCHASED);
        commentWithRemovedState = new GiftComment(userIdentifier, "comment", GiftState.REMOVED);
    }
    
    @Test
    public void testEquals() {
        EqualityTester.testing(new Gift(wish_1)).
                 assertEqualTo(new Gift(wish_1)).
                 assertEqualTo(new Gift(wish_1).addComment(commentWithoutState).addComment(commentWithClaimedState)).
              assertNotEqualTo(new Gift(wish_2)).
              assertNotEqualTo("Not a GiftTest");
        
        EqualityTester.testing(new Gift(wish_1).addComment(commentWithoutState).addComment(commentWithClaimedState)).
                 assertEqualTo(new Gift(wish_1).addComment(commentWithoutState).addComment(commentWithClaimedState)).
                 assertEqualTo(new Gift(wish_1)).
                 assertNotEqualTo(new Gift(wish_2)).
                 assertNotEqualTo("Not a GiftTest");
    }
    
    @Test
    public void testComments() {
        assertEquals(list(), 
                     new Gift(wish_1).getComments());
        assertEquals(list(commentWithoutState), 
                     new Gift(wish_1).addComment(commentWithoutState).getComments());
        assertEquals(list(commentWithoutState, commentWithClaimedState), 
                     new Gift(wish_1).addComment(commentWithoutState).addComment(commentWithClaimedState).getComments());
    }
    
    @Test
    public void testAsWish() {
        assertEquals(wish_1, new Gift(wish_1).asWish()); 
        assertEquals(wish_2, new Gift(wish_2).asWish()); 
    }
    
    @Test
    public void testGetState() {
        assertEquals(GiftState.AVAILABLE, new Gift(wish_1).getState()); 
        
        assertEquals(GiftState.AVAILABLE, new Gift(wish_1).addComment(commentWithoutState).getState()); 
        assertEquals(GiftState.CLAIMED,   new Gift(wish_1).addComment(commentWithClaimedState).getState()); 
        assertEquals(GiftState.PURCHASED, new Gift(wish_1).addComment(commentWithPurchasedState).getState()); 
        assertEquals(GiftState.REMOVED,   new Gift(wish_1).addComment(commentWithRemovedState).getState());
        
        assertEquals(GiftState.CLAIMED, new Gift(wish_1).addComment(commentWithClaimedState).addComment(commentWithoutState).getState()); 
        assertEquals(GiftState.CLAIMED, new Gift(wish_1).addComment(commentWithoutState).addComment(commentWithClaimedState).getState());
        
        assertEquals(GiftState.PURCHASED, new Gift(wish_1).addComment(commentWithPurchasedState).addComment(commentWithoutState).getState()); 
        assertEquals(GiftState.PURCHASED, new Gift(wish_1).addComment(commentWithoutState).addComment(commentWithPurchasedState).getState());
        
        assertEquals(GiftState.CLAIMED,   new Gift(wish_1).addComment(commentWithPurchasedState).addComment(commentWithClaimedState).getState()); 
        assertEquals(GiftState.PURCHASED, new Gift(wish_1).addComment(commentWithClaimedState).addComment(commentWithPurchasedState).getState());
        
        assertEquals(GiftState.REMOVED,   new Gift(wish_1).addComment(commentWithRemovedState).addComment(commentWithClaimedState).getState()); 
        assertEquals(GiftState.REMOVED, new Gift(wish_1).addComment(commentWithRemovedState).addComment(commentWithPurchasedState).getState());
        assertEquals(GiftState.REMOVED, new Gift(wish_1).addComment(commentWithClaimedState).addComment(commentWithRemovedState).getState());
        assertEquals(GiftState.REMOVED, new Gift(wish_1).addComment(commentWithPurchasedState).addComment(commentWithRemovedState).getState());
    }
    
}
