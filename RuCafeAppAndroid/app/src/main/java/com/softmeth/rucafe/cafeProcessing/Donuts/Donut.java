package com.softmeth.rucafe.cafeProcessing.Donuts;


import com.softmeth.rucafe.cafeProcessing.MenuItem;

/**
 * Class for representing a single Donut order
 *
 * @author Daniel Boehm. Anthony De Rose
 */
public class Donut extends MenuItem {
    protected Flavor flavor;
    protected String donutType;

    /**
     * Super Constructor for Inheritance Validity
     * @param price the price of the donut
     * @param number the number of donuts
     */
    public Donut(double price, int number){
        super(price, number);
    }

    /**
     * Generic ToString for all donuts
     * @return a string showing the flavor of the donut and the donut type: i.e. "Chocolate Cake Donut"
     */
    @Override
    public String toString(){
        return getNumber()+" "+flavor.printOut()+" "+donutType+(getNumber()>1 ? "s": "");
    }

}
