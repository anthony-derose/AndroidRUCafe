package com.softmeth.rucafe;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Interface for shared functionality between all activities
 *
 * @author Daniel Boehm. Anthony De Rose
 */
public interface ActivitySuite {

    Integer[] AMOUNTS = {1,2,3,4,5,6,7,8,9,10};

    /**
     * create a toast to display a message
     * @param messageId the R.string.MESSAGEID that references to message to print
     * @param length the Toast.LENGTH_CONSTANT that controls the toast display length
     */
    default void print(int messageId, int length){
        Context app = (Context) this;
        Toast.makeText(app, app.getString(messageId), length).show();
    }

    /**
     * Call configSpinner with the adapter defaulting the AMOUNTS
     * @param id the R.id.ID of the spinner to configure
     * @return the configured spinner
     */
    default Spinner configSpinner(int id){
        AppCompatActivity app = (AppCompatActivity) this;
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(app,
                R.layout.support_simple_spinner_dropdown_item, AMOUNTS);
        return configSpinner(id, adapter);
    }

    /**
     * Configures a spinner with a given adapter and to call this.update() on item selection
     * @param id the R.id.ID of the spinner to configure
     * @param adapter the adapter to set as the spinners content
     * @return the configured spinner
     */
    default Spinner configSpinner(int id, ArrayAdapter adapter){
        AppCompatActivity app = (AppCompatActivity) this;
        Spinner spinner = app.findViewById(id);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                update();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinner.setSelection(0);
        return spinner;
    }

    /**
     * call print with assumed length Toast.LENGTH_SHORT
     * @param messageId the R.String.MESSAGE to display with the toast
     */
    default void print(int messageId){
        print(messageId, Toast.LENGTH_SHORT);
    }

    /**
     * empty update() for spinner-less activities
     */
    default void update(){}

}
