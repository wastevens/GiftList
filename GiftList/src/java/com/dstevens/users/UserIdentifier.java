package com.dstevens.users;

import com.dstevens.utilities.ObjectExtensions;

public class UserIdentifier implements Comparable<UserIdentifier> {

    private final String email;
    
    public UserIdentifier(String email) {
        this.email = email;
    }
    
    @Override
    public boolean equals(Object that) {
        return ObjectExtensions.equals(this, that);
    }
    
    @Override
    public int hashCode() {
        return ObjectExtensions.hashCodeFor(this);
    }
    
    @Override
    public String toString() {
        return ObjectExtensions.toStringFor(this);
    }

    @Override
    public int compareTo(UserIdentifier o) {
        return this.email.compareTo(o.email);
    }
    
}
