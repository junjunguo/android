package com.junjunguo.sunshine;

/**
 * Created by GuoJunjun on 22.12.14.
 * <p/>
 * a generic class hold two unknown data type
 */
public class ObjectHolder<F, S> {
    private F first;
    private S second;

    public ObjectHolder(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public F getFirst() {
        return first;
    }

    public S getSecond() {
        return second;
    }
}
