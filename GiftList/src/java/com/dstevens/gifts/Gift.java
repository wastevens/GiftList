package com.dstevens.gifts;

import static com.dstevens.collections.Lists.list;

import java.util.List;

import com.dstevens.users.User;
import com.dstevens.utilities.ObjectExtensions;

public class Gift implements Comparable<Gift> {

    private final Wish wish;
    private final List<GiftComment> comments = list();
    
    public Gift(String description) {
        this.wish = new Wish(description);
    }

    public void addComment(User user, String comment) {
        comments.add(new GiftComment(user, comment));
    }

    public List<GiftComment> getComments() {
        return comments;
    }
    
    public Wish asWish() {
        return wish;
    }

    @Override
    public int compareTo(Gift that) {
        return this.wish.compareTo(that.wish);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Gift) {
            Gift that = (Gift) obj;
            return this.wish.equals(that.wish);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return wish.hashCode();
    }
    
    @Override
    public String toString() {
        return ObjectExtensions.toStringFor(this);
    }
}
