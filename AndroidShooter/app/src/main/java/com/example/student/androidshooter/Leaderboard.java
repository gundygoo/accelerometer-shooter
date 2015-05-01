package com.example.student.androidshooter;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Leaderboard extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        String score= "";
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
    }



}
