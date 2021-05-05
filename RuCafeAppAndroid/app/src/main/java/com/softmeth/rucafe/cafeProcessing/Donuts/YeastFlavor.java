package com.softmeth.rucafe.cafeProcessing.Donuts;

/**
 * Enum for representing yeast donut flavors
 *
 * @author Daniel Boehm. Anthony De Rose
 */
public enum YeastFlavor implements Flavor{
    JELLY_FILLED, CHOCOLATE, GLAZED, SPRINKLED, BEAR_CLAW;

    /**
     * converts Gui Render String back to ENUM_NAME
     * @param name the string showing the formatted enum name
     * @return the associated enum value
     */
    public static YeastFlavor fromString(String name){
        return valueOf(name.replace(' ', '_').toUpperCase());
    }

    public static String[] allStrings(){
        YeastFlavor[] flavors = values();
        String[] out = new String[flavors.length];
        for (int i = 0; i< flavors.length; i++){
            out[i] = flavors[i].printOut();
        }
        return out;
    }
}
