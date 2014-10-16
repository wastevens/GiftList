package com.dstevens.gifts;

import com.dstevens.users.User;

public class GiftComment {

    private final User commentor;
    private final String comment;
    
    public GiftComment(User commentor, String comment) {
        this.commentor = commentor;
        this.comment = comment;
    }
    
}
