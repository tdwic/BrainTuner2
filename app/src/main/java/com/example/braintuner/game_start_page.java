package com.example.braintuner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class game_start_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_start_page);

        buttonConfigur();

    }

    private void buttonConfigur(){
        Button statBtn = (Button) findViewById(R.id.button_first);
        statBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(game_start_page.this,game_menu.class));
            }
        });
    }
}
