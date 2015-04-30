package com.example.student.androidshooter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameOver extends Activity implements View.OnClickListener {
    TextView t;
    ImageButton mainMenu;
    ImageButton start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        mainMenu = (ImageButton) findViewById(R.id.mainmenu);
        mainMenu.setOnClickListener(this);
        start = (ImageButton) findViewById(R.id.start);
        start.setOnClickListener(this);
        t= (TextView) findViewById(R.id.textView);
        String score="";
        try {
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(
                    openFileInput("leaderboard")));
            String inputString;
            StringBuffer stringBuffer = new StringBuffer();
            while ((inputString = inputReader.readLine()) != null) {
                stringBuffer.append(inputString);
            }
            score = stringBuffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        t.setText("SCORE: "+score);


    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.mainmenu)
        {
            Intent intent = new Intent(getApplicationContext(), MainMenu.class);
            startActivityForResult(intent, 0);
            finish();
        }
        if(v.getId()==R.id.start)
        {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivityForResult(intent, 0);
            finish();
        }
    }
}
