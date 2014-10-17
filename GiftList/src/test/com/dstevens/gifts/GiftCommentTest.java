package com.dstevens.gifts;

import static org.mockito.Mockito.mock;
import org.junit.Test;

import com.dstevens.testing.EqualityTester;
import com.dstevens.users.User;

public class GiftCommentTest {

    @Test
    public void testEquals() {
        User user = mock(User.class);
        User anotherUser = mock(User.class);
        String comment = "a comment";
        String anotherComment = "another comment";
        
        EqualityTester.testing(new GiftComment(user, comment)).
        assertEqualTo(new GiftComment(user, comment)).
        assertNotEqualTo(new GiftComment(anotherUser, comment)).
        assertNotEqualTo(new GiftComment(user, anotherComment)).
        assertNotEqualTo("Not a GiftComment");
    }
    
}
