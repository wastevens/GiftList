package com.dstevens.gifts;

import com.dstevens.utilities.ObjectExtensions;

public class Wish implements Comparable<Wish> {

    private final String description;
    
    public Wish(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
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
    public int compareTo(Wish o) {
        return this.description.compareTo(o.description);
    }
    
}
