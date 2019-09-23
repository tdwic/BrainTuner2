package com.example.braintuner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class game_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MediaPlayer myMedia = MediaPlayer.create(game_menu.this,R.raw.bmusic);
        myMedia.start();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_menu);
        buttonConfigure();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void buttonConfigure(){
        Button startBtn = (Button) findViewById(R.id.button_start);
        Button exitBtn = findViewById(R.id.button_exit);


        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 startActivity(new Intent(game_menu.this,MainActivity.class));
                finish();
            }
        });

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
