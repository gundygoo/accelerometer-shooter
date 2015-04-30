package com.example.student.androidshooter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameOver extends Activity implements View.OnClickListener {
    TextView t;
    ImageButton mainMenu;
    ImageButton start;
    String playerScoreNameCombo;
    String score;
    EditText playerNameInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        mainMenu = (ImageButton) findViewById(R.id.mainmenu);
        mainMenu.setOnClickListener(this);
        start = (ImageButton) findViewById(R.id.start);
        start.setOnClickListener(this);
        playerNameInput = (EditText) findViewById(R.id.playerName);
        t= (TextView) findViewById(R.id.textView);
        String score="";
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                score= null;
            } else {
                score= extras.getString("Score");
            }
        } else {
            score= (String) savedInstanceState.getSerializable("Score");
        }
        t.setText("SCORE: "+score);


    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.mainmenu)
        {
            sendDataToLeaderboard();
            Intent intent = new Intent(getApplicationContext(), MainMenu.class);
            startActivityForResult(intent, 0);
            finish();
        }
        if(v.getId()==R.id.start)
        {
            sendDataToLeaderboard();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivityForResult(intent, 0);
            finish();
        }
    }
    public void sendDataToLeaderboard()
    {
        String playerName = playerNameInput.getText().toString();
        playerName.replace("\n", " ");
        String playerScoreNameCombo= score+","+playerName+"\n";
        File file = new File("leaderboard");
        try {
            FileOutputStream fos = openFileOutput("leaderboard", Context.MODE_APPEND);
            fos.write(playerScoreNameCombo.getBytes());
            fos.close();
        }
        catch (Exception e)
        {
        }
    }
}
