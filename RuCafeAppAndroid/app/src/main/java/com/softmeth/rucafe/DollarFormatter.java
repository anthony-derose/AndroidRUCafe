package com.softmeth.rucafe;

import java.text.DecimalFormat;

/**
 * Publically Available streamlined formatter
 *
 *  @author Daniel Boehm. Anthony De Rose
 */
public class DollarFormatter {

    private static final DecimalFormat format = new DecimalFormat("0.00");

    /*
     * Initialization
     */
    static{
        format.setGroupingUsed(true);
        format.setGroupingSize(3);
    }

    /**
     * formats a double x to dollar format "$xy.cc"
     * @param number the double to format
     * @return a string representing the number as a USD amount
     */
    public static String format(double number) {
        return "$"+format.format(number);
    }




}
