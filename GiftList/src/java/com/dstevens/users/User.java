package com.dstevens.users;

import static com.dstevens.collections.Lists.list;

import java.util.*;

import com.dstevens.gifts.Gift;
import com.dstevens.utilities.ObjectExtensions;

public class User {

    private final String email;
    private final List<User> friends = list();
    private final Wishlist wishlist;
    
    public User(String email) {
        this.email = email;
        this.wishlist = new Wishlist();
    }

    public void addFriend(User friend) {
        if(!friends.contains(friend)) {
            friends.add(friend);
        }
    }

    public Collection<User> getFriends() {
        return friends;
    }

    public void removeFriend(User friend) {
        friends.remove(friend);
    }

    public void addGiftToWishlist(Gift gift) {
        this.wishlist.addGift(gift);
    }

    public void removeGiftFromWishlist(Gift gift) {
        this.wishlist.removeGift(gift);
    }
    
    public Wishlist getMyWishlist() {
        return wishlist.asViewedByOwner();
    }

    public Wishlist getWishlistForOthers() {
        return wishlist;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            User that = (User) obj;
            return this.email.equals(that.email);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return email.hashCode();
    }
    
    @Override
    public String toString() {
        return ObjectExtensions.toStringFor(this);
    }
}
