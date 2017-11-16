package com.example.savan.brainsquiz;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText playerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        playerName = (EditText) this.findViewById(R.id.playerName);
    }

    public void switchQuizLayout(View v)
    {
        String name = playerName.getText().toString();
        if(!name.isEmpty())
        {
            Intent i = new Intent(MainActivity.this,QuizLayout.class);
            i.putExtra("player_name",name);
            startActivity(i);
        }
        else
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Please enter your name..")
                    .setTitle("Name Required");
            builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id)
                {
                    playerName.findFocus();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}