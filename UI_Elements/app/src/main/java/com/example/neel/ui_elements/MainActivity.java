package com.example.neel.ui_elements;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // keeps track of current state of display, true means display is true
    boolean currentState = true;
    TextView textView;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
    }

    public void hide_show(View view) {
        if (currentState) {
            textView.setVisibility(View.INVISIBLE);
            button.setText("SHOW");
            currentState = false;
        } else {
            textView.setVisibility(View.VISIBLE);
            button.setText("HIDE");
            currentState = true;
        }
    }
}
