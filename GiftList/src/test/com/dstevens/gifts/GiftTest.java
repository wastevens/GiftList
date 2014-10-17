package com.dstevens.gifts;

import static com.dstevens.collections.Lists.list;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.junit.Test;

import com.dstevens.testing.EqualityTester;
import com.dstevens.users.User;

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
    
    @Test
    public void testAddComment() {
        User user1 = mock(User.class);
        User user2 = mock(User.class);
        String comment1 = "I'm going to buy this";
        String comment2 = "I already bought it";
        Gift gift = new Gift("some gift");

        List<GiftComment> noComments = list();
        assertEquals(noComments, gift.getComments());
        
        gift.addComment(user1, comment1);
        gift.addComment(user2, comment2);
        
        assertEquals(list(new GiftComment(user1, comment1), new GiftComment(user2, comment2)), gift.getComments());
        
    }
}
