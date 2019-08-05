package com.example.braintuner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class level_2_a extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_2_a);
        buttonConfigure();
    }

    private void buttonConfigure(){
        Button statBtn = (Button) findViewById(R.id.button_level_2_a);
        statBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(level_2_a.this,level_3.class));
            }
        });
    }
}
