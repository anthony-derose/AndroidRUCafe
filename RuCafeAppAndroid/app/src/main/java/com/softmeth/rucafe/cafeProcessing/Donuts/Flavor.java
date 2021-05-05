package com.softmeth.rucafe.cafeProcessing.Donuts;

/**
 * Inheritable type for representing Donut flavors
 *
 * @author Daniel Boehm. Anthony De Rose
 */
public interface Flavor {

    /**
     * Publically accessible formatter for the behavior in printOut()
     * @param string the enum name string to capitalize
     * @return string formatted as human readable text
     */
    static String capitalize(String string){
        String[] start;
        start = string.replace('_',' ').split(" ");
        StringBuilder ret = new StringBuilder();
        for (String s : start) {
            ret.append(s.substring(0, 1).toUpperCase());
            ret.append(s.substring(1).toLowerCase());
            ret.append(" ");
        }
        return ret.toString().trim();
    }

    /**
     * Transforms ENUM_CONVENTION to Human Readable Text
     * @return A first letter capitalized and space separate representation of the enum value name.
     */
    default String printOut() {
        return capitalize(toString());
    }


}
