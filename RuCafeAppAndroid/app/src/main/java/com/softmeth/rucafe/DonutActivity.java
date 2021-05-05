package com.softmeth.rucafe;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.softmeth.rucafe.cafeProcessing.Donuts.YeastFlavor;
/**
 * Class for controlling Donut Selection Screen
 *
 * @author Daniel Boehm. Anthony De Rose
 */
public class DonutActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView flavorView;

    /**
     * Initialize page on creation, assumes no intent information
     * @param savedInstanceState unused
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut);
        setTitle(R.string.donut_page);

        flavorView = findViewById(R.id.flavorView);

        flavorView.setOnItemClickListener(this);
        ArrayAdapter<String> flavors = new ArrayAdapter<>(this, R.layout.listview_item, YeastFlavor.allStrings());
        flavorView.setAdapter(flavors);

    }

    /**
     * Listener for item selected from flavorView listview. opens an order confirmation to get donuts of the selected
     * flavor
     *
     * @param parent   The AdapterView where the click happened.
     * @param view     The view within the AdapterView that was clicked (this
     *                 will be a view provided by the adapter)
     * @param position The position of the view in the adapter.
     * @param id       The row id of the item that was clicked.
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        YeastFlavor flavor = YeastFlavor.values()[position];
        Intent intent = new Intent(this, DonutOrderActivity.class);
        intent.putExtra(Intent.EXTRA_TEXT,flavor.printOut());
        startActivity(intent);
    }
}
