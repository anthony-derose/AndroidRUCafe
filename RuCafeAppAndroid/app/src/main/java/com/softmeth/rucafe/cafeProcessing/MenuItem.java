package com.softmeth.rucafe.cafeProcessing;

/**
 * Parent class for any item that can be ordered
 *
 * @author Daniel Boehm. Anthony De Rose
 */
public class MenuItem {
    /* notes abt items
        donuts:
                  3 types yeast $1.39, cake $1.59, and donut holes $0.33 regardless of flavor
        coffee:
                brewed coffee with choices of add-ins: cream, syrup, milk, caramel, and whipped cream
                 short, tall, grande, venti
                 short black is $1.99
                 each add in is $0.20 extra
                 each size increase is $0.50 increments
     */

    private double price;
    private int number;

    /**
     * constructor for creating item
     * @param price price of the menu item
     * @param number the number of items ordered
     */
    public MenuItem(double price, int number){
        this.price = price;
        this.number = number;
    }

    /**
     * changes price of created item
     * @param newPrice the new price of the object
     */
    protected void setPrice(double newPrice){
        price = newPrice;
    }

    /**
     * changes number of the item ordered
     * @param newNumber the new number of items
     */
    public void setNumber(int newNumber){
        number = newNumber;
    }

    /**
     * getter for number of items
     * @return the number of items ordered
     */
    protected int getNumber(){
        return number;
    }

    /**
     * Checks equality of two items. Defined for collection removal
     * @param obj the object to remove
     * @return true if the object is a MenuItem with the same String rendering as the calling object
     */
    @Override
    public boolean equals(Object obj){
        return (obj instanceof MenuItem) && toString().equals(obj.toString());
    }

    /**
     * Returns generating String
     * @return a string representing the specific menuitem, or an error message if called on an instantiated menuitem
     */
    @Override
    public String toString(){
        return "There is a grievous bug in our code";
    }

    /**
     * getter for the total cost of the item
     * @return the price of the item multiplied by the amount of items requested
     */
    public double itemPrice(){
        return price*number;
    }


}
