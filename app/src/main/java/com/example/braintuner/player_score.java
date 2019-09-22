package com.example.braintuner;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class player_score extends AppCompatActivity {

    private  int levelScore[] = {0,0,0,0,0,0,0,0},totalAnswer;
    private TextView userScoreShow,userShowName;

    UserData userData = new UserData();

    FirebaseDatabase firebaseDatabase;
    DatabaseReference finalScore,UserSave;

    @Override
    protected void onStart() {
        super.onStart();
        MediaPlayer victory = MediaPlayer.create(player_score.this,R.raw.victory);
        victory.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_score);

userData.setUserName("lanka");

        firebaseDatabase = FirebaseDatabase.getInstance();
        finalScore = firebaseDatabase.getReference().child("CurrentScore").child(userData.getUserName());
        UserSave = firebaseDatabase.getReference().child("FinalScore");
        tableConnection();
        userData.setUserName("df");

        //



    }


    private void  tableConnection(){
        finalScore.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                numbers_Load(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("myTa", "Failed to read value.", error.toException());
            }
        });



    }


    private void numbers_Load(DataSnapshot dataSnapshot){
        userScoreShow = (TextView)findViewById(R.id.totalScore);
        userShowName = (TextView)findViewById(R.id.userName);

        levelScore[0] = Integer.parseInt(dataSnapshot.child("level1").getValue().toString());
        levelScore[1] = Integer.parseInt(dataSnapshot.child("level1_1").getValue().toString());
        levelScore[2] = Integer.parseInt(dataSnapshot.child("level2").getValue().toString());
        levelScore[3] = Integer.parseInt(dataSnapshot.child("level2_1").getValue().toString());
        levelScore[4] = Integer.parseInt(dataSnapshot.child("level3").getValue().toString());
        levelScore[5] = Integer.parseInt(dataSnapshot.child("level3_1").getValue().toString());
        levelScore[6] = Integer.parseInt(dataSnapshot.child("level4").getValue().toString());
        levelScore[7] = Integer.parseInt(dataSnapshot.child("level4_1").getValue().toString());

        for (int x = 0; x<=7; x++){
            totalAnswer = totalAnswer + levelScore[x];
        }

        UserSave.child(userData.getUserName()).child("Score").setValue(totalAnswer);
        userScoreShow.setText(String.valueOf(totalAnswer));
        userShowName.setText(userData.getUserName());

    }


}
