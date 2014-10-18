package com.dstevens.gifts;

import static org.junit.Assert.assertEquals;
import org.junit.*;
import org.mockito.*;

import com.dstevens.testing.EqualityTester;
import com.dstevens.users.UserIdentifier;

public class GiftCommentTest {

    private static final GiftState STATE_2 = GiftState.CLAIMED;
    private static final GiftState STATE_1 = GiftState.AVAILABLE;
    private static final String COMMENT_1 = "a comment";
    private static final String COMMENT_2 = "another comment";
    
    @Mock private UserIdentifier userId_1;
    @Mock private UserIdentifier userId_2;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testEquals() {
        EqualityTester.testing(new GiftComment(userId_1, COMMENT_1)).
                 assertEqualTo(new GiftComment(userId_1, COMMENT_1)).
              assertNotEqualTo(new GiftComment(userId_2, COMMENT_1)).
              assertNotEqualTo(new GiftComment(userId_1, COMMENT_2)).
              assertNotEqualTo(new GiftComment(userId_1, COMMENT_1, STATE_1)).
              assertNotEqualTo(new GiftComment(userId_1, COMMENT_1, STATE_2)).
              assertNotEqualTo("Not a GiftComment");
        
        EqualityTester.testing(new GiftComment(userId_1, COMMENT_1, STATE_1)).
                 assertEqualTo(new GiftComment(userId_1, COMMENT_1, STATE_1)).
              assertNotEqualTo(new GiftComment(userId_2, COMMENT_1, STATE_1)).
              assertNotEqualTo(new GiftComment(userId_1, COMMENT_2, STATE_1)).
              assertNotEqualTo(new GiftComment(userId_1, COMMENT_1, STATE_2)).
              assertNotEqualTo("Not a GiftComment");
        
        EqualityTester.testing(new GiftComment(userId_1, COMMENT_1, STATE_2)).
                 assertEqualTo(new GiftComment(userId_1, COMMENT_1, STATE_2)).
              assertNotEqualTo(new GiftComment(userId_2, COMMENT_1, STATE_2)).
              assertNotEqualTo(new GiftComment(userId_1, COMMENT_2, STATE_2)).
              assertNotEqualTo(new GiftComment(userId_1, COMMENT_1, STATE_1)).
              assertNotEqualTo(new GiftComment(userId_1, COMMENT_1, GiftState.PURCHASED)).
              assertNotEqualTo(new GiftComment(userId_1, COMMENT_1, GiftState.REMOVED)).
              assertNotEqualTo("Not a GiftComment");
    }
    
    @Test
    public void testState() {
        assertEquals(false, new GiftComment(userId_1, COMMENT_1).hasState());
        assertEquals(true,  new GiftComment(userId_1, COMMENT_1, STATE_1).hasState());
        assertEquals(true,  new GiftComment(userId_1, COMMENT_1, STATE_2).hasState());
        
        assertEquals(STATE_1, new GiftComment(userId_1, COMMENT_1, STATE_1).getState());
        assertEquals(GiftState.PURCHASED, new GiftComment(userId_1, COMMENT_1, GiftState.PURCHASED).getState());
    }
    
    @Test
    public void testCommentor() {
        assertEquals(userId_1, new GiftComment(userId_1, COMMENT_1).getCommentor());
        assertEquals(userId_2, new GiftComment(userId_2, COMMENT_1).getCommentor());
    }
    
    @Test
    public void testComment() {
        assertEquals(COMMENT_1, new GiftComment(userId_1, COMMENT_1).getComment());
        assertEquals(COMMENT_2, new GiftComment(userId_1, COMMENT_2).getComment());
    }
    
}
