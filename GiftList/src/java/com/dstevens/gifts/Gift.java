package com.dstevens.gifts;

import static com.dstevens.collections.Lists.list;

import java.util.List;
import java.util.stream.Collectors;

import com.dstevens.utilities.ObjectExtensions;

public class Gift implements Comparable<Gift> {

    private final Wish wish;
    private final List<GiftComment> comments;
    
    public Gift(Wish wish) {
        this.wish = wish;
        this.comments = list();
    }
    
    public Wish asWish() {
        return wish;
    }
    
    public GiftState getState() {
        List<GiftComment> listOfComments = comments.stream().filter(c -> c.hasState()).collect(Collectors.toList());
        if(listOfComments.stream().anyMatch(c -> c.getState().equals(GiftState.REMOVED))) {
            return GiftState.REMOVED;
        }
        if(listOfComments.size() > 0) {
            return last(listOfComments).getState();
        }
        return GiftState.AVAILABLE;
    }
    
    private GiftComment last(List<GiftComment> listOfComments) {
        return listOfComments.get(listOfComments.size() -1);
    }

    public Gift addComment(GiftComment comment) {
        this.comments.add(comment);
        return this;
    }
    
    public List<GiftComment> getComments() {
        return comments;
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
