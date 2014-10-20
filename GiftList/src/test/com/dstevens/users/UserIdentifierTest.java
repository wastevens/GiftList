package com.dstevens.users;

import static com.dstevens.collections.Lists.*;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.dstevens.testing.EqualityTester;

public class UserIdentifierTest {

    @Test
    public void testEquals() throws Exception {
        EqualityTester.testing(new UserIdentifier(13)).
                       assertEqualTo(new UserIdentifier(13)).
                       assertNotEqualTo(new UserIdentifier(14)).
                       assertNotEqualTo("Not a user identifier");
    }
    
    @Test
    public void testNaturalOrdering() {
        assertEquals(list(new UserIdentifier(1), new UserIdentifier(2), new UserIdentifier(3)), 
                sort(list(new UserIdentifier(2), new UserIdentifier(3), new UserIdentifier(1))));
    }
    
}
