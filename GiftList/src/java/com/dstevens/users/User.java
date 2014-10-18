package com.dstevens.users;

import static com.dstevens.collections.Lists.list;

import java.util.List;

import com.dstevens.gifts.Gift;
import com.dstevens.utilities.ObjectExtensions;

public class User implements Comparable<User> {

    private final UserIdentifier userId;
    private final List<UserIdentifier> friends = list();
    private final Wishlist wishlist;
    
    public User(String email) {
        this.userId = new UserIdentifier(email);
        this.wishlist = new Wishlist();
    }

    public UserIdentifier getId() {
        return userId;
    }
    
    public void addFriend(UserIdentifier friendId) {
        if(!friends.contains(friendId)) {
            friends.add(friendId);
        }
    }

    public List<UserIdentifier> getFriends() {
        return friends;
    }

    public void removeFriend(UserIdentifier friend) {
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
            return this.userId.equals(that.userId);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return userId.hashCode();
    }
    
    @Override
    public String toString() {
        return ObjectExtensions.toStringFor(this);
    }

    @Override
    public int compareTo(User o) {
        return this.userId.compareTo(o.userId);
    }
}
