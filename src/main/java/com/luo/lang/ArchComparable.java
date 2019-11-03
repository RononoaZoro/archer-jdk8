package com.luo.lang;

/**
 * @param <T> the type of objects that this object may be compared to
 * @author Josh Bloch
 * @author luoxuzheng
 * @create 2019-10-22 22:15
 * @see java.util.Comparator
 * @since 1.2
 **/
public interface ArchComparable<T> {

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * @param o
     * @return
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    public int compareTo(T o);
}
