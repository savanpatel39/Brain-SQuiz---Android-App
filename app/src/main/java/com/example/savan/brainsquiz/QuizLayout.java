package com.example.savan.brainsquiz;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuizLayout extends AppCompatActivity {

    Questions que;
    Questions temp;
    private TextView questionLabel;
    private TextView scoreLabel;
    private RadioGroup rGroup;
    private RadioButton op1;
    private RadioButton op2;
    private RadioButton op3;
    private RadioButton op4;

    Intent i;

    int[] index;
    int count;
    int score;

    String playerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_layout);

        getPlayerDetails();

        initializeComponents();
        showQuestions();
    }

    public void getPlayerDetails()
    {
            i = this.getIntent();
            //playerName = i.getStringExtra("player_name");

            if( (i.hasExtra("player_name"))  )
            {
                playerName = i.getStringExtra("player_name");
                setPlayerName(playerName);
                Log.d("Setting Player Name", playerName);
            }
            else
            {
                playerName = getPlayerName();
                Log.d("Getting Player Name",playerName);
            }
    }

    public void setPlayerName(String playerName)
    {
        SharedPreferences sp = getSharedPreferences("playerDetails", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sp.edit();
        editor.putString("playerName", playerName);
        editor.apply();
    }

    public String getPlayerName()
    {
        SharedPreferences sp = getSharedPreferences("playerDetails", Context.MODE_PRIVATE);
        return sp.getString("playerName","");
    }

    public void initializeComponents()
    {
        count = 0;  score = 0;
        que = new Questions();
        index = que.randomizeQuestions();

        i = new Intent(QuizLayout.this,QuizLayout.class);

        questionLabel = (TextView) this.findViewById(R.id.questionLabel);
        scoreLabel = (TextView) this.findViewById(R.id.scoreLabel);
        rGroup = (RadioGroup) this.findViewById(R.id.rGroup);
        op1 = (RadioButton) this.findViewById(R.id.op1);
        op2 = (RadioButton) this.findViewById(R.id.op2);
        op3 = (RadioButton) this.findViewById(R.id.op3);
        op4 = (RadioButton) this.findViewById(R.id.op4);
    }

    public void showQuestions()
    {
        if(count < 5)
        {
            temp = que.getQuestionDetails(index[count++]);
            questionLabel.setText(temp.getQuestion);
            op1.setText(temp.getOptions[0]);
            op2.setText(temp.getOptions[1]);
            op3.setText(temp.getOptions[2]);
            op4.setText(temp.getOptions[3]);
        }
        else
        {
            showResult();
        }
    }

    public void showResult()
    {
        String msg = " ";
        boolean flag = false;
        String title = "Result";

        if( (score >= 0) && (score <= 2))
        {
            msg = playerName+", your score is "+score+"\nPlease try again! ";
            flag = false ;
        }
        else if ( score == 3 )
        {
            msg = playerName+", your score is "+score+"\nGood job! ";
            flag = true;
        }
        else if ( score == 4 )
        {
            msg = playerName+", your score is "+score+"\nExcellent work! ";
            flag = true;
        }
        else if ( score == 5 )
        {
            msg = playerName+", your score is "+score+"\nYou are a genius! ";
            flag = true;
        }

        showDialogBox(msg, title, flag);

    }

    public void showDialogBox(String msg,String title,boolean flag) {
        AlertDialog.Builder builder = new AlertDialog.Builder(QuizLayout.this);
        builder.setMessage(msg).setTitle(title);


        if (!flag)
        {
            builder.setPositiveButton("Retake", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    count = 0;
                    i = new Intent(QuizLayout.this,QuizLayout.class);
                    startActivity(i);
                }
            });

        }
        else
        {
            builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    i = new Intent(QuizLayout.this,MainActivity.class);
                    QuizLayout.this.startActivity(i);
                }
            });
        }
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void radioSelected(View v)
    {
        RadioButton r = (RadioButton)v;
        //boolean checked = ((RadioButton) v).isChecked();

        op1.setChecked(false);
        op2.setChecked(false);
        op3.setChecked(false);
        op4.setChecked(false);

        if( r.getText().equals( temp.getAnswer) )//count score
        {
            score++;
            scoreLabel.setText(score+" / 5");
            Toast.makeText(getApplicationContext(), "Correct answer", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Wrong answer", Toast.LENGTH_SHORT).show();
        }
        showQuestions();
    }
}
