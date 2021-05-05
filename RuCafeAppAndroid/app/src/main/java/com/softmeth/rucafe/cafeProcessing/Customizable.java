package com.softmeth.rucafe.cafeProcessing;

/**
 * Interface for promising element addition and removal
 *
 * @author Daniel Boehm. Anthony De Rose
 */
public interface Customizable {
    /**
     * must add element to calling object
     * @param obj object to add
     * @return true if addition successful, false otherwise
     */
    boolean add(Object obj);
    /**
     * must remove element from calling object
     * @param obj object to remove
     * @return true if removal successful, false otherwise
     */
    boolean remove(Object obj);
}
