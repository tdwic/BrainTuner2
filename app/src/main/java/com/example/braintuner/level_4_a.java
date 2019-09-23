package com.example.braintuner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class level_4_a extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference GameValue,CurrentScore,FinalScore;


    private Handler mHandler = new Handler();

    int leftNumber[]={0,0,0,0,0};
    int rightNumber[]={0,0,0,0,0};

    private  static  int i = 0,j=4,x=0,y= 0,rndomScore = 15,numberOfPass = 0;
    private int userAns;
    private  int scoreVal;

    private  TextView[] leftText = new TextView[6];
    private  TextView[] rightText = new TextView[6];
    private TextView TimmerShow,scoreShow;
    private EditText UserAnswer;
    private Button num0,num1,num2,num3,num4,num5,num6,num7,num8,num9,clr,numNegative;

    private CountDownTimer countDownTimer;
    private long timeLeftMiliSec = 10000;
    private long mili = 5000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_4_a);

        textViewFind();

        firebaseDatabase = FirebaseDatabase.getInstance();
        GameValue = FirebaseDatabase.getInstance().getReference().child("GameValues").child("level4_1");
        CurrentScore = FirebaseDatabase.getInstance().getReference().child("CurrentScore");

        tableConnection();

        startTimmer();
        UserAnswer.requestFocus();
        mHandler.postDelayed(mRun, mili);

    }


    private void numbers_Load(DataSnapshot dataSnapshot){
        leftNumber[0] = Integer.parseInt(dataSnapshot.child("leftNumber1").getValue().toString());
        leftNumber[1] = Integer.parseInt(dataSnapshot.child("leftNumber2").getValue().toString());
        leftNumber[2] = Integer.parseInt(dataSnapshot.child("leftNumber3").getValue().toString());
        leftNumber[3] = Integer.parseInt(dataSnapshot.child("leftNumber4").getValue().toString());
        leftNumber[4] = Integer.parseInt(dataSnapshot.child("leftNumber5").getValue().toString());

        rightNumber[0] = Integer.parseInt(dataSnapshot.child("rightNumber1").getValue().toString());
        rightNumber[1] = Integer.parseInt(dataSnapshot.child("rightNumber2").getValue().toString());
        rightNumber[2] = Integer.parseInt(dataSnapshot.child("rightNumber3").getValue().toString());
        rightNumber[3] = Integer.parseInt(dataSnapshot.child("rightNumber4").getValue().toString());
        rightNumber[4] = Integer.parseInt(dataSnapshot.child("rightNumber5").getValue().toString());
    }

    private void textViewFind(){
        leftText[0] =  findViewById(R.id.left0);
        leftText[1] =  findViewById(R.id.left1);
        leftText[2] =  findViewById(R.id.left2);
        leftText[3] =  findViewById(R.id.left3);
        leftText[4] =  findViewById(R.id.left4);
        leftText[5] =  findViewById(R.id.left5);


        rightText[0] =  findViewById(R.id.right0);
        rightText[1] =  findViewById(R.id.right1);
        rightText[2] =  findViewById(R.id.right2);
        rightText[3] =  findViewById(R.id.right3);
        rightText[4] =  findViewById(R.id.right4);
        rightText[5] =  findViewById(R.id.right5);

        TimmerShow = findViewById(R.id.timerShow);
        scoreShow = findViewById(R.id.score);
        UserAnswer = findViewById(R.id.userInput);
    }

    private void startTimmer(){
        countDownTimer = new CountDownTimer(timeLeftMiliSec, 1000) {
            @Override
            public void onTick(long l) {
                timeLeftMiliSec = l;
                updateTimer();
            }

            @Override
            public void onFinish() {
                TimmerShow.setText("Time Out");
                CurrentScore.child("level4_1").setValue(scoreVal);
                Intent nextLevel = new Intent(level_4_a.this,player_score.class);
                startActivity(nextLevel);
                finish();
            }
        }.start();
    }

    private void updateTimer(){
        String timeleft;
        int minits = (int) (timeLeftMiliSec / 60000);
        int seconds = (int) (timeLeftMiliSec % 60000 / 1000);

        timeleft = "0"+minits;
        timeleft += ":";

        if (seconds<10){
            timeleft += "0";
        }
        if (minits < 1){

            TimmerShow.setTextColor(Color.rgb(200,0,0));
        }
        timeleft += seconds;
        TimmerShow.setText(timeleft);
    }

    private void  tableConnection(){
        GameValue.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                numbers_Load(dataSnapshot);
                for (int s= 0; s<=4; s++){
                    leftText[s].setText(String.valueOf(leftNumber[s]));
                    rightText[s].setText(String.valueOf(rightNumber[s]));
                }



                /*for (int x=0; x<=4; x++){
                    Log.w("myTaR", "Lef" + leftNumber[x]);
                    Log.w("myTaL", "Rgt" + rightNumber[x]);
                }*/

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("myTa", "Failed to read value.", error.toException());
            }
        });

    }

    private Runnable mRun = new Runnable() {
        @Override
        public void run() {
            if (i < leftNumber.length) {
                leftText[5].setText(" ");
                rightText[5].setText(" ");

                leftText[5].setText(String.valueOf(leftNumber[j]));
                rightText[5].setText(String.valueOf(rightNumber[j]));

                x = Integer.parseInt(leftText[5].getText().toString());
                y = Integer.parseInt(rightText[5].getText().toString());

                leftText[j].setText(" ");
                rightText[j].setText(" ");
                /////////////////////////////////////////////

                UserAnswer.setText("");
                UserAnswer.addTextChangedListener(new TextWatcher() {

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        Log.d("myTag", "onTextChanged: ");
                        if (UserAnswer.length() > 0) {
                            userAns = Integer.parseInt(UserAnswer.getText().toString());
                        }
                        if (userAns == (x + y)) {
                            numberOfPass = numberOfPass + 1;

                            Log.d("myCal", "numberOfPass" + numberOfPass);
                            Log.d("scoreVal", "Before" + scoreVal);

                            scoreVal = scoreVal + rndomScore;
                            CurrentScore.child("level4_1").setValue(scoreVal);
                            Log.d("scoreVal", "After" + scoreVal);

                            userAns = 0;
                            scoreShow.setTextColor(Color.rgb(200,0,0));
                            scoreShow.setText(String.valueOf(scoreVal));

                            mHandler.post(mRun);

                        }
                    }
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
                    @Override
                    public void afterTextChanged(Editable editable) {
                        if (numberOfPass == 12){
                            Intent next = new Intent(level_4_a.this,player_score.class);
                            startActivity(next);
                            finish();
                        }


                    }
                });



                /////////////////////////////////////////////
                j--;
                i++;
                //mHandler.post(mRun);
            }else {

                mHandler.removeCallbacks(mRun);
            }


        }
    };

    class keyCode extends Thread{
        @Override
        public void run() {
            super.run();

            num0 = (Button)findViewById(R.id.bt0);
            num1 = (Button)findViewById(R.id.bt1);
            num2 = (Button)findViewById(R.id.bt2);
            num3 = (Button)findViewById(R.id.bt3);
            num4 = (Button)findViewById(R.id.bt4);
            num5 = (Button)findViewById(R.id.bt5);
            num6 = (Button)findViewById(R.id.bt6);
            num7 = (Button)findViewById(R.id.bt7);
            num8 = (Button)findViewById(R.id.bt8);
            num9 = (Button)findViewById(R.id.bt9);
            numNegative = (Button)findViewById(R.id.btNegative);
            clr = (Button)findViewById(R.id.btDel);


            num0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UserAnswer.setText(UserAnswer.getText().insert(UserAnswer.getText().length(),"0"));
                }
            });

            num1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UserAnswer.setText(UserAnswer.getText().insert(UserAnswer.getText().length(),"1"));
                }
            });

            num2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UserAnswer.setText(UserAnswer.getText().insert(UserAnswer.getText().length(),"2"));
                }
            });

            num3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UserAnswer.setText(UserAnswer.getText().insert(UserAnswer.getText().length(),"3"));
                }
            });

            num4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UserAnswer.setText(UserAnswer.getText().insert(UserAnswer.getText().length(),"4"));
                }
            });

            num5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UserAnswer.setText(UserAnswer.getText().insert(UserAnswer.getText().length(),"5"));
                }
            });

            num6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UserAnswer.setText(UserAnswer.getText().insert(UserAnswer.getText().length(),"6"));
                }
            });

            num7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UserAnswer.setText(UserAnswer.getText().insert(UserAnswer.getText().length(),"7"));
                }
            });

            num8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UserAnswer.setText(UserAnswer.getText().insert(UserAnswer.getText().length(),"8"));
                }
            });

            num9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UserAnswer.setText(UserAnswer.getText().insert(UserAnswer.getText().length(),"9"));
                }
            });

            numNegative.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UserAnswer.setText(UserAnswer.getText().insert(UserAnswer.getText().length(),"-"));
                }
            });

            clr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (UserAnswer.length() > 0){
                        UserAnswer.setText(UserAnswer.getText().delete(UserAnswer.getText().length()-1,UserAnswer.getText().length()));
                    }

                }
            });

        }
    }


}
