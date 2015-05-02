package com.example.student.androidshooter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.student.androidshooter.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;


public class MainMenu extends Activity implements View.OnClickListener {
    ImageButton start;
    ImageButton calibrate;
    ImageButton leaderboard;
    ImageButton instructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Log.d("Main Menu", "Activity Started");
        start= (ImageButton) findViewById(R.id.start);
        start.setOnClickListener(this);
        calibrate= (ImageButton) findViewById(R.id.calibrate);
        calibrate.setOnClickListener(this);
        leaderboard= (ImageButton) findViewById(R.id.leaderboard);
        leaderboard.setOnClickListener(this);
        instructions= (ImageButton) findViewById(R.id.instructions);
        instructions.setOnClickListener(this);
        File file = new File("angle");
        if(file.length()<=0)
        {
            try {
                String normalTilt = "6.7";
                FileOutputStream fos = openFileOutput("angle", Context.MODE_PRIVATE);
                fos.write(normalTilt.getBytes());
                fos.close();
            }
            catch (Exception e)
            {

            }
        }




    }
    @Override
    public void onClick(View v)
    {
        if(v.getId()==R.id.start)
        {
            try {
                BufferedReader inputReader = new BufferedReader(new InputStreamReader(
                        openFileInput("angle")));
                String inputString;
                StringBuffer stringBuffer = new StringBuffer();
                while ((inputString = inputReader.readLine()) != null) {
                    stringBuffer.append(inputString + "\n");
                }
                Log.d("Angle", stringBuffer.toString());
                //lblTextViewOne.setText(stringBuffer.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d("Main Menu", "Start was clicked");
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivityForResult(intent, 0);
        }
        if(v.getId()==R.id.leaderboard)
        {
            Log.d("Main Menu", "Leaderboard was clicked");
            Intent intent = new Intent(getApplicationContext(), Leaderboard.class);
            startActivityForResult(intent, 0);
        }
        if(v.getId()==R.id.calibrate)
        {
            Log.d("Main Menu", "Calibrate was clicked");
            Intent intent = new Intent(getApplicationContext(), Calibrate.class);
            startActivityForResult(intent, 0);
        }
        if(v.getId()==R.id.instructions)
        {
            Log.d("Main Menu", "Instructions was clicked");
            Intent intent = new Intent(getApplicationContext(), Instructions.class);
            startActivityForResult(intent, 0);
        }
    }
}

