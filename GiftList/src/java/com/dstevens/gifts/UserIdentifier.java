package com.dstevens.gifts;

import com.dstevens.utilities.ObjectExtensions;

class GiftIdentifier implements Comparable<GiftIdentifier> {

    private final int id;
    
    public GiftIdentifier(int id) {
        this.id = id;
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
    public int compareTo(GiftIdentifier o) {
        return this.id - o.id;
    }
    
}
