package com.softmeth.rucafe.cafeProcessing;

import com.softmeth.rucafe.DollarFormatter;

import java.util.Vector;

/**
 * Class for representing a single order of items
 *
 * @author Daniel Boehm. Anthony De Rose
 */
public class Order implements Customizable{

    private static int next_id = 0;   // <-- static, class-wide counter

    private final int id;
    public final static double TAX = 6.625 /100;

    private final Vector<MenuItem> items = new Vector<>();

    /**
     * Basic constructor, creates new order in running list
     */
    public Order(){
        this(0);
    }

    /**
     * Constructor taking id
     * @param id manual id to give order, 0 if automatic incrementing id
     */
    public Order(int id){
        if(id==0){
            this.id = ++Order.next_id;
        }
        else{
            this.id = id;
        }
    }

    /**
     * Adds item to list
     * @param item the item to add to the list
     */
    private void addItem(MenuItem item) {
        items.addElement(item);
    }

    /**
     * Removes item from list
     * @param item item to remove
     * @return true if item exists and was removed, false otherwise
     */
    private boolean removeItem(MenuItem item) {
        return items.remove(item);
    }

    /**
     * Adds item to list
     * @return true if add was successful, false otherwise
     */
    public boolean add(Object obj){
        if (obj instanceof MenuItem) {
            MenuItem objItem = (MenuItem) obj;
            addItem(objItem);
        } else {
            return false;
        }
        return true;
    }

    /**
     * Removes item from list
     * @return true if remove was successful, false if unsuccessful
     */
    public boolean remove(Object obj){
        if (obj instanceof MenuItem) {
            MenuItem objItem = (MenuItem) obj;
            return removeItem(objItem);
        } else {
            return false;
        }
    }

    /**
     * Returns subtotal for all items in the order
     * @return a double representing the current subtotal
     */
    public double getSubtotal(){
        double subTotal = 0;
        for (MenuItem item : items){
            subTotal+= item.itemPrice();
        }
        return subTotal;
    }


    /**
     * Renders the order for a list view
     * @return a string with the order id and subtotal
     */
    public Vector<String> prices() {
        Vector<String> out = new Vector<>();
        for (MenuItem item : items){
            out.addElement(DollarFormatter.format(item.itemPrice()));
        }
        return out;
    }

    /**
     * Returns the list of the items in the order.
     * @return vector of items in the menu
     */
    public Vector<MenuItem> access(){
        return items;
    }


    /**
     * Prints out order for store order rendering
     * @return a string with the order id and subtotal
     */
    @Override
    public String toString(){
        return "Order "+id+": Sub-total of "+ DollarFormatter.format(getSubtotal());
    }

    /**
     * check if two orders are equal
     * @param obj the other object, must be an order for equality
     * @return true if objects are both orders that share the same id, false otherwise
     */
    @Override
    public boolean equals(Object obj){
        return obj instanceof Order && ((Order) obj).id==id;
    }
}
