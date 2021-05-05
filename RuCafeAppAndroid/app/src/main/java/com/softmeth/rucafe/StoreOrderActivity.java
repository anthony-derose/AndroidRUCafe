package com.softmeth.rucafe;

import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.softmeth.rucafe.cafeProcessing.MenuItem;
import com.softmeth.rucafe.cafeProcessing.Order;

import java.util.Vector;

/**
 * Class for controlling order history Screen
 *
 * @author Daniel Boehm. Anthony De Rose
 */
public class StoreOrderActivity extends AppCompatActivity implements ActivitySuite {

    private Spinner orderSpinner;
    private ListView orderView;
    private EditText totalBox;

    private Order observed;

    /**
     * Initialize page on creation, assumes no intent information
     * @param savedInstanceState unused
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_order);
        setTitle(R.string.history_page);
        totalBox = findViewById(R.id.orderTotalBox);
        orderView = findViewById(R.id.orderView);
        observed = null;
        setListview(null);
        spinnerUpdate();
    }

    /**
     * Update Screen status upon interface action
     */
    @Override
     public void update() {
        observed = (Order) orderSpinner.getSelectedItem();
        setListview(observed);
    }

    /**
     * sets listView to show items from given order
     * @param data order to display in listview. renders empty if data is null
     */
    private void setListview(Order data){
        if (data == null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.listview_item, new String[]{});
            orderView.setAdapter(adapter);

            return;
        }
        Vector<MenuItem> items = data.access();

        String[] render = new String[items.size()];

        for (int i = 0; i < items.size(); i++) {
            MenuItem item = items.get(i);
            render[i] = item.toString();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.listview_item, render);
        orderView.setAdapter(adapter);
        double subtotal = data.getSubtotal();
        double tax = subtotal * Order.TAX;
        double total = subtotal + tax;
        totalBox.setText(DollarFormatter.format(total));
    }

    /**
     * change the spinner to reflect deletion of order
     */
    private void spinnerUpdate() {
        Vector<Order> orders = MainActivity.storeOrders.access();

        ArrayAdapter<Order> adapter = new ArrayAdapter<Order>(this,
                R.layout.support_simple_spinner_dropdown_item, (Order[]) orders.toArray(new Order[orders.size()]));
        orderSpinner = configSpinner(R.id.orderSpinner, adapter);

    }

    /**
     * remove selected order from store orders upon cancellation button click
     * @param view unused
     */
    public void cancelOrder(View view) {
        if(observed==null){
            print(R.string.select_order);
            return;
        }
        MainActivity.storeOrders.remove(observed);
        print(R.string.cancel_confirmation);
        spinnerUpdate();
        setListview(null);
    }
}
