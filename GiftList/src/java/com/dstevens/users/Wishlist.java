package com.dstevens.users;

import static com.dstevens.collections.Lists.*;

import java.util.*;
import java.util.stream.Collectors;

import com.dstevens.gifts.Gift;
import com.dstevens.utilities.ObjectExtensions;

class Wishlist {

    private final List<Gift> gifts = list();
    
    public Wishlist() {
    }

    public static Wishlist with(List<Gift> gifts) {
        return new Wishlist(gifts);
    }
    
    private Wishlist(List<Gift> gifts) {
        this.gifts.addAll(gifts);
    }
    
    public void addGift(Gift gift) {
        this.gifts.add(gift);
    }

    public void removeGift(Gift gift) {
        this.gifts.remove(gift);
    }

    public List<Gift> getGifts() {
        return gifts;
    }
    
    public void commentOn(final Gift gift, User user, String comment) {
        Optional<Gift> foundGift = gifts.stream().filter(g -> g.equals(gift)).findFirst();
        if(foundGift.isPresent()) {
            foundGift.get().addComment(user, comment);
        }
    }
    
    public Wishlist asViewedByOwner() {
        return Wishlist.with(gifts.stream().map((g -> g.withoutComments())).collect(Collectors.toList()));
    }

    public Wishlist asViewedByFriend() {
        return this;
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
