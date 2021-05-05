package com.softmeth.rucafe.cafeProcessing;

import java.util.Vector;

/**
 * Class for representing all orders made
 *
 * @author Daniel Boehm. Anthony De Rose
 */
public class StoreOrders implements Customizable {


    private final Vector<Order> orders = new Vector<>();


    /**
     * Adds order to list
     * @param order the order to add to the list
     */
    private void addItem(Order order) {
        orders.addElement(order);
    }

    /**
     * Removes order from list
     * @param order order to remove
     * @return true if order exists and was removed, false otherwise
     */
    private boolean removeItem(Order order) {
        return orders.remove(order);
    }

    /**
     * Adds order from list
     * @return true if add was successful, false otherwise
     */
    public boolean add(Object obj){
        if (obj instanceof Order) {
            Order objItem = (Order) obj;
            addItem(objItem);
        } else {
            return false;
        }
        return true;
    }

    /**
     * Removes order from list
     * @return true if remove was successful, false otherwise
     */
    public boolean remove(Object obj){
        if (obj instanceof Order) {
            Order objItem = (Order) obj;
            return removeItem(objItem);
        } else {
            return false;
        }
    }

    /**
     * Returns the list of the orders in the store.
     * @return vector of the orders in the store.
     */
    public Vector<Order> access(){
        return orders;
    }


    /**
     * Outputs string formatted for export
     * @return a string containing every order details and contents
     */
    public String toString(){
        StringBuilder out= new StringBuilder();
        for(Order order: orders){
            out.append(order.toString()).append("\n");
            for(MenuItem item: order.access()){
                out.append("\t").append(item.toString()).append("\n");
            }
        }
        return out.toString().trim();
    }


}
