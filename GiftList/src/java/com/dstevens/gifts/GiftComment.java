package com.dstevens.gifts;

import java.util.Optional;

import com.dstevens.users.UserIdentifier;
import com.dstevens.utilities.ObjectExtensions;

public class GiftComment {

    private final UserIdentifier commentor;
    private final String comment;
    private final Optional<GiftState> giftState;
    
    public GiftComment(UserIdentifier commentor, String comment) {
        this.commentor = commentor;
        this.comment = comment;
        this.giftState = Optional.empty();
    }
    
    public GiftComment(UserIdentifier commentor, String comment, GiftState giftState) {
        this.commentor = commentor;
        this.comment = comment;
        this.giftState = Optional.of(giftState);
    }

    public boolean hasState() {
        return giftState.isPresent();
    }
    
    public GiftState getState() {
        return giftState.get();
    }
    
    public UserIdentifier getCommentor() {
        return commentor;
    }
    
    public String getComment() {
        return comment;
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
