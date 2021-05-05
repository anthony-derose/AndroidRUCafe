package com.softmeth.rucafe.cafeProcessing;

import com.softmeth.rucafe.cafeProcessing.Donuts.Flavor;

import java.util.Vector;

/**
 * Class for representing a single coffee order
 *
 * @author Daniel Boehm. Anthony De Rose
 */
public class Coffee extends MenuItem implements Customizable{

    private final Vector<AddIns> additions;
    public final static String[] SIZES = {"Short", "Tall", "Grande", "Venti"};
    private final double BASE_COST = 1.99;
    private final double SIZE_COST = 0.5;
    private final double ADD_COST = 0.2;
    private final int MAX_SIZE = 3;
    private final int MIN_SIZE = 0;

    // 0 to short      1 to tall      2 to grande    3 to venti 0.50 cent increments
    private int size;

    /**
     * Constructor for a single coffee addition to order
     * @param size 0,1,2,3 for short, tall, grande, venti. Negative numbers are assumed to be 0,
     *             greater than 3 assumed to be 3
     * @param number the number of coffees ordered.
     */
    public Coffee(int size, int number) {
        super(0, number);
        additions = new Vector<>();
        setSize(size);
    }


    /**
     * changes size of coffee
     * @param size the new size of the coffee
     */
    public void setSize(int size){
        this.size = Math.max(Math.min(size, MAX_SIZE),MIN_SIZE);
    }

    /**
     * Adds addin to list
     * @return true if add was successful, false otherwise
     */
    public boolean add(Object obj){
        if (obj instanceof AddIns && !additions.contains(obj)) {
            AddIns objItem = (AddIns) obj;
            additions.addElement(objItem);
        } else {
            return false;
        }
        return true;
    }

    /**
     * Removes addin from list
     * @return true if remove was succesful, false if unsuccesful
     */
    public boolean remove(Object obj){
        if (obj instanceof AddIns) {
            AddIns objItem = (AddIns) obj;
            return additions.remove(objItem);
        } else {
            return false;
        }
    }

    /**
     * getter for the total cost of the coffee
     * @return the price of the item multiplied by the amount of items requested
     */
    @Override
    public double itemPrice(){
        double price = BASE_COST + (SIZE_COST*size) + (ADD_COST*additions.size());
        return price*getNumber();
    }

    /**
     * generates String representing coffee order
     * @return String describing coffee attributes
     */
    @Override
    public String toString() {
        String addinString = "";
        if (additions.isEmpty()) addinString = "No Add Ins";
        else{
            addinString = additions.toString();
            addinString = addinString.substring(1,addinString.length()-1);
            addinString = Flavor.capitalize(addinString);
        }
        return getNumber()+" "+SIZES[size]+" Coffee"+(getNumber()>1 ? "s": "")+" with "+addinString;
    }


}
