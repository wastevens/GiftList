package com.dstevens.users;

import static com.dstevens.collections.Lists.*;

import java.util.List;

import com.dstevens.gifts.Gift;
import com.dstevens.utilities.ObjectExtensions;

public class Wishlist {

    private final List<Gift> gifts = list();
    
    public void addGift(Gift gift) {
        this.gifts.add(gift);
    }

    public void removeGift(Gift gift) {
        this.gifts.remove(gift);
    }

    public List<Gift> getGifts() {
        return gifts;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Wishlist) {
            Wishlist that = (Wishlist) obj;
            return sort(this.gifts).equals(sort(that.gifts));
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return sort(gifts).hashCode();
    }
    
    @Override
    public String toString() {
        return ObjectExtensions.toStringFor(this);
    }
}
