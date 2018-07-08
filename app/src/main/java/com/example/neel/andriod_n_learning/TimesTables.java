package com.example.neel.andriod_n_learning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TimesTables extends AppCompatActivity {

    int currentMultiplier;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_times_tables);

        // Create a list view and populate items
        final ListView listView = (ListView) findViewById(R.id.times_table);
        final ArrayList<Integer> display_numbers = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5));
        final ArrayAdapter<Integer> numbers_adapter = new ArrayAdapter<Integer>(
                this,
                android.R.layout.simple_list_item_1,
                display_numbers);
        listView.setAdapter(numbers_adapter);

        //Set seekbar
        SeekBar multiplier = (SeekBar)findViewById(R.id.seekBar);
        currentMultiplier = 10;
        multiplier.setMax((int) 10);
        multiplier.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.i("Progress", String.valueOf(i));
                int index = 0;
                for (Integer number: display_numbers) {
                    display_numbers.set(index, (index  + 1) * i);
                    index++;
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                listView.setAdapter(numbers_adapter);
            }
        });
    }
}
