package com.example.neel.brainteaser;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    int correct_result;
    Button start; // The start button
    Button grid1, grid2, grid3, grid4; // Thr grid buttons
    TextView score, time_left, problem_text; // The text for score, time_left and problem_text
    CountDownTimer countDownTimer; // The 30 sec countdown timer.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Register all views..
        time_left = findViewById(R.id.time_left);
        problem_text = findViewById(R.id.problem_text);
        score = findViewById(R.id.score);
        start = (Button) findViewById(R.id.startButton);
        start.setOnClickListener(this);
        grid1 = (Button) findViewById(R.id.grid1);
        grid1.setOnClickListener(this);
        grid2 = (Button) findViewById(R.id.grid2);
        grid2.setOnClickListener(this);
        grid3 = (Button) findViewById(R.id.grid3);
        grid3.setOnClickListener(this);
        grid4 = (Button) findViewById(R.id.grid4);
        grid4.setOnClickListener(this);
        setGridVisibility(false);  // Set Grid visibility false when starting
          // Call a function to create the problem text and answers
    }

    @Override
    public void onClick(View view) {
        final int id = view.getId();
        Log.i("Clicked", String.valueOf(id));
        switch (id) {
            case R.id.startButton:
                Log.i("Clicked start", String.valueOf(id));
                startButton(id);
                break;
            case R.id.grid1:
                checkResult(grid1.getText().toString());
                break;
            case R.id.grid2:
                checkResult(grid2.getText().toString());
                break;
            case R.id.grid3:
                checkResult(grid3.getText().toString());
                break;
            case R.id.grid4:
                checkResult(grid4.getText().toString());
                break;
            // even more buttons here
        }
    }

    private void setGridVisibility(boolean visibility) {
        for (int i = 0; i < 4; i++) {
            {
                String buttonID = "grid" + (i + 1);
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                Button button = (Button) findViewById(resID);
                if (visibility) {
                    button.setVisibility(View.VISIBLE);
                } else {
                    button.setVisibility(View.INVISIBLE);
                }
            }
        }

    }

    public void startCountDownTimer() {
        Log.i("Start Timeer", "Here");
        countDownTimer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long l) {
                time_left.setText(String.valueOf(l / 1000));
            }

            @Override
            public void onFinish() {
                startButton(R.id.startButton);
                setGridVisibility(false);
                problem_text.setText("0");
            }
        };
        countDownTimer.start();
    }

    public void startButton(int id) {
        start = findViewById(id);
        Log.i("visibility", String.valueOf(start.getVisibility()));
        if (start.getVisibility() == View.VISIBLE) {
            start.setVisibility(View.INVISIBLE);
            score.setText("0");
            setGridVisibility(true);
            createProblemText();
            startCountDownTimer();
        } else {
            start.setVisibility(View.VISIBLE);
        }
    }

    // Random Shuffling using the fisher yates algo.
    private static int[] shuffleArray(int[] array)
    {
        int index;
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--)
        {
            index = random.nextInt(i + 1);
            if (index != i)
            {
                array[index] ^= array[i];
                array[i] ^= array[index];
                array[index] ^= array[i];
            }
        }
        return array;
    }


    public void createProblemText() {
        Random r = new Random();
        int number_1 = r.nextInt(10);
        int number_2 = r.nextInt(10);
        int options[] = new int[4];
        correct_result = number_1 + number_2;
        options[0] = correct_result;
        options[1] = number_1 + number_2 - r.nextInt(8);
        options[2] = number_1 + number_2 + r.nextInt(5);
        options[3] = number_1 + number_2 + 8 + r.nextInt(5);
        Log.i("Options", Arrays.toString(options));
        options = shuffleArray(options);
        Log.i("Options shuffled", Arrays.toString(options));
        problem_text.setText(
                String.format("%s + %s", String.valueOf(number_1), String.valueOf(number_2))
        );
        grid1.setText(String.valueOf(options[0]));
        grid2.setText(String.valueOf(options[1]));
        grid3.setText(String.valueOf(options[2]));
        grid4.setText(String.valueOf(options[3]));
    }

    public void checkResult(String value){
        if (Integer.parseInt(value) == correct_result) {
            int currentScore = Integer.parseInt(score.getText().toString());
            score.setText(String.valueOf(currentScore + 1));
        }
        createProblemText();
    }
}
