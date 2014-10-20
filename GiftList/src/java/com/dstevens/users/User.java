package com.dstevens.users;

import java.util.List;

import com.dstevens.gifts.Wishlist;
import com.dstevens.utilities.ObjectExtensions;

import static com.dstevens.collections.Lists.list;

public class User implements Comparable<User> {

    private final UserIdentifier userId;
    private final String email;
    private final List<UserIdentifier> friends = list();
    private final List<Wishlist> wishlists = list();
    
    public User(UserIdentifier userIdentifier, String email) {
        this.userId = userIdentifier;
		this.email = email;
    }
    
    public String getEmail() {
    	return email;
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

    public void addWishlist(Wishlist wishlist) {
        if(!wishlists.contains(wishlist)) {
            wishlists.add(wishlist);
        }
    }

    public List<Wishlist> getWishlists() {
        return wishlists;
    }

    public void removeWishlist(Wishlist wishlist) {
        wishlists.remove(wishlist);
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
