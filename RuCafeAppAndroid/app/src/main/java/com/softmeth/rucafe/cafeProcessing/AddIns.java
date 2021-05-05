package com.softmeth.rucafe.cafeProcessing;

/**
 * Enum for all possible Coffee Add Ins
 *
 * @author Daniel Boehm. Anthony De Rose
 */
public enum AddIns{
    CREAM, SYRUP, MILK, CARAMEL, WHIPPED_CREAM;

    /**
     * converts Gui Render String back to ENUM_NAME
     * @param name the string showing the formatted enum name
     * @return the associated AddIns enum value
     */
    public static AddIns fromString(String name){
        return valueOf(name.replace(' ', '_').toUpperCase());
    }
}
