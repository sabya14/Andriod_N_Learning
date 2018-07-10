package com.example.neel.timerapp;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    DateFormat f;
    long max_time;
    CountDownTimer countDownTimer;
    TextView time;
    MediaPlayer mp;
    SeekBar seekBar;
    Button start_stop;
    Boolean playing;

    public long time_in_ms(long max_time) {
        return max_time * 60 * 1000;
    }

    public void setTime(long time_passed_ms, TextView time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss", Locale.ENGLISH);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date date = new Date(time_passed_ms);
        time.setText(dateFormat.format(date));
        Log.i("Time left:", dateFormat.format(date));
    }

    public void setCountDownTimer() {
        countDownTimer = new CountDownTimer(seekBar.getProgress(), 100) {
            @Override
            public void onTick(long l) {
                setTime(l, time);
            }

            @Override
            public void onFinish() {
                mp.start();
            }
        };
    }

    public void start_stop(View view) {
        if (playing) {
            countDownTimer.cancel();
            playing = false;
            start_stop.setText("Start");
            seekBar.setVisibility(View.VISIBLE);
            seekBar.setProgress(30*1000);
            setTime(seekBar.getProgress(), time);
            setCountDownTimer();
        } else {
            playing = true;
            seekBar.setVisibility(View.INVISIBLE);
            start_stop.setText("Stop");
            setCountDownTimer();
            countDownTimer.start();
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        time = findViewById(R.id.time);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        start_stop = (Button) findViewById(R.id.button);
        seekBar.setMax(600*1000);
        seekBar.setProgress(30*1000);
        setTime(seekBar.getProgress(), time);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                setTime(progress, time);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                countDownTimer.cancel();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                setCountDownTimer();
            }
        });
//        setCountDownTimer();
        mp = MediaPlayer.create(this, R.raw.air_horn);
        playing = false;
    }
}
