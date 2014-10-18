package com.dstevens.gifts;

import static com.dstevens.collections.Lists.list;

import java.util.List;
import java.util.stream.Collectors;

import com.dstevens.gifts.*;
import com.dstevens.utilities.ObjectExtensions;

public class Wishlist {

    private final List<Gift> gifts = list();
    
    public void addWish(String description) {
        gifts.add(new Gift(description));
    }

    public List<Gift> getGifts() {
        return gifts;
    }
    
    public List<Wish> getWishes() {
        return gifts.stream().filter(g -> !g.getState().equals(GiftState.REMOVED)).map(g -> g.asWish()).collect(Collectors.toList());
    }
    
    @Override
    public boolean equals(Object that) {
        return ObjectExtensions.equals(this, that);
    }
    
    @Override
    public int hashCode() {
        return ObjectExtensions.hashCodeFor(this);
    }
    
    @Override
    public String toString() {
        return ObjectExtensions.toStringFor(this);
    }
}
