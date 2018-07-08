package com.example.neel.andriod_n_learning;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class Timers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timers);
        // two ways to implement timers, using count down timer or runnable.
        CountDownTimer countDownTimer= new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l) {
                Log.i("MS until done", String.valueOf(l));
            }

            @Override
            public void onFinish() {
                Log.i("On Finish", "Countdown finished");
            }
        };

        countDownTimer.start();

        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Log.i("Running", "Runnable");
                handler.postDelayed(this,1000);
            }
        };
        handler.post(runnable);
    }
}
