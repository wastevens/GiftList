package com.dstevens.gifts;

import static com.dstevens.collections.Lists.list;
import static org.junit.Assert.assertEquals;
import org.junit.*;
import org.mockito.*;

import com.dstevens.testing.EqualityTester;

public class WishlistTest {

    private static final String ANOTHER_NAME = "another name";
    private static final String NAME = "name";
    
    @Mock private Wish wish_1;
    @Mock private Wish wish_2;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testEquals() {
        EqualityTester.testing(new Wishlist(NAME)).
                 assertEqualTo(new Wishlist(NAME)).
              assertNotEqualTo(new Wishlist(NAME).addWish(wish_1)).
              assertNotEqualTo(new Wishlist(ANOTHER_NAME)).
              assertNotEqualTo("Not a Wishlist");
        
        EqualityTester.testing(new Wishlist(NAME).addWish(wish_1)).
                 assertEqualTo(new Wishlist(NAME).addWish(wish_1)).
              assertNotEqualTo(new Wishlist(NAME)).
              assertNotEqualTo(new Wishlist(NAME).addWish(wish_2)).
              assertNotEqualTo(new Wishlist(NAME).addWish(wish_1).addWish(wish_2)).
              assertNotEqualTo(new Wishlist(ANOTHER_NAME).addWish(wish_1)).
              assertNotEqualTo("Not a Wishlist");
    }
    
    @Test
    public void testGetName() {
        assertEquals(NAME,         new Wishlist(NAME).getName());
        assertEquals(ANOTHER_NAME, new Wishlist(ANOTHER_NAME).getName());
    }
    
    @Test
    public void testGetWishes() {
        assertEquals(list(), new Wishlist(NAME).getWishes());
        assertEquals(list(wish_1), new Wishlist(NAME).addWish(wish_1).getWishes());
        assertEquals(list(wish_1, wish_2), new Wishlist(NAME).addWish(wish_1).addWish(wish_2).getWishes());
    }
    
    @Test
    public void testGetGifts() {
        assertEquals(list(), new Wishlist(NAME).getGifts());
        assertEquals(list(new Gift(wish_1)), new Wishlist(NAME).addWish(wish_1).getGifts());
        assertEquals(list(new Gift(wish_1), new Gift(wish_2)), new Wishlist(NAME).addWish(wish_1).addWish(wish_2).getGifts());
    }
    
}
