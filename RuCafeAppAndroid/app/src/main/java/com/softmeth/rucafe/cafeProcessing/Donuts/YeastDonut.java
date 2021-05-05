package com.softmeth.rucafe.cafeProcessing.Donuts;

/**
 * Class for representing a single yeast donut order
 *
 * @author Daniel Boehm. Anthony De Rose
 */
public class YeastDonut extends Donut{
    private final static double YEAST_PRICE = 1.39;


    /**
     * Constructor for yeast donut
     * @param yeastFlavor the flavor of the donut
     * @param number the number of donuts
     */
    public YeastDonut(YeastFlavor yeastFlavor, int number){
        super(YEAST_PRICE,number);
        flavor = yeastFlavor;
        donutType = "Donut";//Changed from Yeast Donut to Donut for single type in Android App
    }

}
