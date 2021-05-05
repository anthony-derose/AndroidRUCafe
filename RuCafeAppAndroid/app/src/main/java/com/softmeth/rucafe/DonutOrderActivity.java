package com.softmeth.rucafe;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.softmeth.rucafe.cafeProcessing.Donuts.YeastDonut;
import com.softmeth.rucafe.cafeProcessing.Donuts.YeastFlavor;

/**
 * Class for controlling Donut Order Confirmation Screen
 *
 * @author Daniel Boehm. Anthony De Rose
 */
public class DonutOrderActivity extends AppCompatActivity implements ActivitySuite {

    private Spinner numDonutsSpinner;
    private YeastDonut donut;
    private EditText subtotalBox;

    /**
     * Initialize page on creation, assumes intent information
     * @param savedInstanceState unused
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut_order);
        Intent intent = getIntent();
        String flavor = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (flavor == null) {
            print(R.string.no_flavor_error, Toast.LENGTH_LONG);
            Intent goBack = new Intent(this, DonutActivity.class);
            startActivity(goBack);
            return;
        }

        numDonutsSpinner = findViewById(R.id.numDonutSpinner);
        subtotalBox = findViewById(R.id.donutSubtotal);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item, AMOUNTS);
        numDonutsSpinner.setAdapter(adapter);
        numDonutsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                update();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        setTitle(flavor);
        donut = new YeastDonut(YeastFlavor.fromString(flavor), 0);
        subtotalBox.setText(DollarFormatter.format(0));
        numDonutsSpinner.setSelection(0);
    }

    /**
     * Change the screen status when spinner is used
     */
    @Override
    public void update() {
        Integer amount = (Integer) numDonutsSpinner.getSelectedItem();
        donut.setNumber(amount);
        subtotalBox.setText(DollarFormatter.format(donut.itemPrice()));
    }


    /**
     * Submit donut to running order upon confirmation button click
     * @param view unused
     */
    public void confirmDonuts(View view) {
        Integer amount = (Integer) numDonutsSpinner.getSelectedItem();
        if (amount == null) {
            print(R.string.select_amount);
            return;
        }
        Intent intent = new Intent(this, MainActivity.class);
        int outMessage = amount > 1 ? R.string.donuts_confirmation : R.string.donut_confirmation;
        MainActivity.currentOrder.add(donut);
        startActivity(intent);
        print(outMessage);
    }
}
