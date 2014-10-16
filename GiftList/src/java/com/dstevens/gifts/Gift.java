package com.dstevens.gifts;

import static com.dstevens.collections.Lists.list;

import java.util.List;

import com.dstevens.users.User;

public class Gift {

    private final User recipient;
    private final String description;
    private final List<GiftComment> comments = list();
    
    public Gift(User recipient, String description) {
        this.recipient = recipient;
        this.description = description;
    }
    
}
