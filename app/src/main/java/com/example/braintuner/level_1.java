package com.example.braintuner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import java.util.concurrent.Delayed;

public class level_1 extends AppCompatActivity {

    private TextView time_Show;
    private CountDownTimer countDownTimer;
    private long timeLeftMiliSec;

    public void delayTimeLast(){

        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    Intent nextLevel = new Intent(level_1.this,level_1_a.class);
                    startActivity(nextLevel);
                    finish();
                }

            }
        };
        thread.start();
    }


    public void startTimmer(){
        countDownTimer = new CountDownTimer(timeLeftMiliSec, 1000) {
            @Override
            public void onTick(long l) {
                timeLeftMiliSec = l;
                updateTimer();
            }

            @Override
            public void onFinish() {
                time_Show.setText("Time Out");
                MediaPlayer cer = MediaPlayer.create(level_1.this,R.raw.tick);
                cer.start();
                delayTimeLast();


            }
        }.start();
    }

    public void updateTimer(){
        String timeleft;
        int minits = (int) (timeLeftMiliSec / 60000);
        int seconds = (int) (timeLeftMiliSec % 60000 / 1000);

        timeleft = "0"+minits;
        timeleft += ":";

        if (seconds < 10){
            timeleft += "0";
            time_Show.setTextColor(Color.rgb(200,0,0));
        }
        timeleft += seconds;
        time_Show.setText(timeleft);
    }


    //Main Method Begins

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_1);

        timeLeftMiliSec = 5000;
        time_Show = findViewById(R.id.time);
        startTimmer();
    }

}
