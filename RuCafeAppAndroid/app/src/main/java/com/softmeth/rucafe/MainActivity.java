package com.softmeth.rucafe;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.softmeth.rucafe.cafeProcessing.Order;
import com.softmeth.rucafe.cafeProcessing.StoreOrders;

/**
 * Class for controlling Main/Navigation Screen
 *
 * @author Daniel Boehm. Anthony De Rose
 */
public class MainActivity extends AppCompatActivity {



    public static final StoreOrders storeOrders = new StoreOrders();
    public static Order currentOrder = new Order();

    /**
     * Initialize page on creation, assumes intent information
     * @param savedInstanceState unused
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Opens donut order page
     * @param view ignored
     */
    public void openDonuts(View view) {
        Intent intent = new Intent(this, DonutActivity.class);
        startActivity(intent);
    }

    /**
     * Opens Store orders page
     * @param view ignored
     */
    public void openStoreOrder(View view) {
        Intent intent = new Intent(this, StoreOrderActivity.class);
        startActivity(intent);
    }

    /**
     * Opens current order page
     * @param view ignored
     */
    public void openOrder(View view) {
        Intent intent = new Intent(this, OrderActivity.class);
        startActivity(intent);
    }

    /**
     * log current order into store orders, and begin new order
     */
    public static void confirmOrder(){
        storeOrders.add(currentOrder);
        currentOrder = new Order();
    }

    /**
     * Opens order coffee page
     * @param view ignored
     */
    public void openCoffee(View view) {
        Intent intent = new Intent(this, CoffeeActivity.class);
        startActivity(intent);
    }
}
