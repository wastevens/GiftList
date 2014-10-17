package com.dstevens.gifts;

import static com.dstevens.collections.Lists.list;

import java.util.List;

import com.dstevens.utilities.ObjectExtensions;

public class Gift implements Comparable<Gift>{

    private final String description;
    private final List<GiftComment> comments = list();
    
    public Gift(String description) {
        this.description = description;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Gift) {
            Gift that = (Gift) obj;
            return this.description.equals(that.description);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return description.hashCode();
    }
    
    @Override
    public String toString() {
        return ObjectExtensions.toStringFor(this);
    }

    @Override
    public int compareTo(Gift that) {
        return this.description.compareTo(that.description);
    }
    
}
