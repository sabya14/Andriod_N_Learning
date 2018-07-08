package com.example.neel.andriod_n_learning;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class Chapter5_01 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter5_01);

        ListView listView = (ListView) findViewById(R.id.list_view);
        final ArrayList<String> names = new ArrayList<String>(Arrays.asList("Neel", "Shubham", "Vivek"));

//        names.add("Neel");
//        names.add("Shubham");
//        names.add("Vivek");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                names);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("Person tapped", names.get(i));
                showToast(names.get(i));
            }
        });
    }
    /**
     * Function to display a toast message*/
    public void showToast(String toast_text) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast.makeText(context, toast_text, duration).show();

    }
}
