package com.dstevens.gifts;

import com.dstevens.users.User;
import com.dstevens.utilities.ObjectExtensions;

public class GiftComment {

    private final User commentor;
    private final String comment;
    
    public GiftComment(User commentor, String comment) {
        this.commentor = commentor;
        this.comment = comment;
    }
    
    @Override
    public boolean equals(Object obj) {
       return ObjectExtensions.equals(this, obj);
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
