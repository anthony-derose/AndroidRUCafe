package com.softmeth.rucafe;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.softmeth.rucafe.cafeProcessing.MenuItem;
import com.softmeth.rucafe.cafeProcessing.Order;

import java.util.Vector;

/**
 * Class for controlling order review Screen
 *
 * @author Daniel Boehm. Anthony De Rose
 */
public class OrderActivity extends AppCompatActivity implements ActivitySuite {

    private ListView itemView;
    private EditText subtotalBox;
    private EditText taxBox;
    private EditText totalBox;
    private int selection;
    private Order order;
    private final static int INT_NULL = -1;


    /**
     * Initialize page on creation, assumes no intent information
     * @param savedInstanceState unused
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        setTitle(R.string.order_page);
        selection = INT_NULL;

        order = MainActivity.currentOrder;

        itemView = findViewById(R.id.itemView);
        itemView.setOnItemClickListener((parent, view, position, id) -> selection = position);

        subtotalBox = findViewById(R.id.subtotalBox);
        taxBox = findViewById(R.id.taxBox);
        totalBox = findViewById(R.id.totalBox);

        update();
    }

    /**
     * Update Screen status upon interface action
     */
    @Override
    public void update(){
        Vector<MenuItem> items = order.access();

        String[] render = new String[items.size()];

        for(int i = 0; i<items.size(); i++){
            MenuItem item = items.get(i);
            render[i] = item.toString()+": "+DollarFormatter.format(item.itemPrice());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.listview_item, render);
        itemView.setAdapter(adapter);


        double subtotal = order.getSubtotal();
        subtotalBox.setText(DollarFormatter.format(subtotal));
        double tax = subtotal * Order.TAX;
        taxBox.setText(DollarFormatter.format(tax));
        double total = subtotal + tax;
        totalBox.setText(DollarFormatter.format(total));
    }

    /**
     * submit current order upon confirmation button press
     * @param view ignored
     */
    public void confirmOrder(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        int outMessage = R.string.order_confirmation;
        MainActivity.confirmOrder();
        startActivity(intent);
        print(outMessage);
    }

    /**
     * cancel selected item upon cancellation button press
     * @param view ignored
     */
    public void cancelItem(View view) {
        if(selection==INT_NULL){
            print(R.string.select_item);
            return;
        }
        MenuItem item = MainActivity.currentOrder.access().get(selection);
        MainActivity.currentOrder.remove(item);
        update();
    }
}
