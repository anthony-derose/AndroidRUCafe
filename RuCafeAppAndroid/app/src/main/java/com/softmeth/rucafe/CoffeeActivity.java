package com.softmeth.rucafe;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.softmeth.rucafe.cafeProcessing.AddIns;
import com.softmeth.rucafe.cafeProcessing.Coffee;

/**
 * Class for controlling Coffee Ordering Screen
 *
 * @author Daniel Boehm. Anthony De Rose
 */
public class CoffeeActivity extends AppCompatActivity implements ActivitySuite {

    private Spinner numCoffeeSpinner;
    private Spinner sizeCoffeeSpinner;

    private EditText subtotalBox;
    private CheckBox caramelBox;
    private CheckBox syrupBox;
    private CheckBox whippedBox;
    private CheckBox milkBox;
    private CheckBox creamBox;

    private final Coffee coffee = new Coffee(0, 0);

    private CheckBox[] boxes;

    /**
     * Initialize page on creation, assumes no intent information
     * @param savedInstanceState unused
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);
        setTitle(R.string.coffee_page);

        caramelBox = findViewById(R.id.caramelBox);
        syrupBox = findViewById(R.id.syrupBox);
        whippedBox = findViewById(R.id.whippedBox);
        milkBox = findViewById(R.id.milkBox);
        creamBox = findViewById(R.id.creamBox);
        subtotalBox = findViewById(R.id.coffeeSubtotalBox);
        boxes = new CheckBox[]{caramelBox, syrupBox, whippedBox, milkBox, creamBox};

        numCoffeeSpinner = configSpinner(R.id.numCoffeeSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item, Coffee.SIZES);
        sizeCoffeeSpinner = configSpinner(R.id.coffeeSizeSpinner, adapter);
        subtotalBox.setText(DollarFormatter.format(0));

    }

    /**
     * Update Screen status upon interface action
     */
    @Override
    public void update() {
        for (CheckBox box : boxes) {
            AddIns addin = AddIns.fromString(box.getText().toString());
            if (box.isChecked()) coffee.add(addin);
            else coffee.remove(addin);
        }
        Integer amount = (Integer) numCoffeeSpinner.getSelectedItem();
        int size = sizeCoffeeSpinner.getSelectedItemPosition();
        coffee.setNumber(amount);
        coffee.setSize(size);
        subtotalBox.setText(DollarFormatter.format(coffee.itemPrice()));



    }

    /**
     * Confirm coffee order and submit to running order
     * @param view unused
     */
    public void confirmCoffee(View view) {
        Integer amount = (Integer) numCoffeeSpinner.getSelectedItem();
        if (amount == null) {
            print(R.string.select_amount);
            return;
        }
        Intent intent = new Intent(this, MainActivity.class);
        int outMessage = amount > 1 ? R.string.coffees_confirmation : R.string.coffee_confirmation;
        MainActivity.currentOrder.add(coffee);
        startActivity(intent);
        print(outMessage);
    }


    /**
     * onAction listener for checkboxes
     * @param view unused
     */
    public void onCheckbox(View view) {
        update();
    }
}
