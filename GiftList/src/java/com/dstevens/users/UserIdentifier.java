package com.dstevens.users;

import com.dstevens.utilities.ObjectExtensions;

public class UserIdentifier implements Comparable<UserIdentifier> {

    private final int id;

	public UserIdentifier(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
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
        return this.id - o.id;
    }
    
}
