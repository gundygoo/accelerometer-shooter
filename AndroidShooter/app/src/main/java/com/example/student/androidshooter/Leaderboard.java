package com.example.student.androidshooter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;


public class Leaderboard extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        String score= "";
        ArrayList<Score> scores = new ArrayList<>();
        int arrayLength =0;
        try
        {
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(openFileInput("leaderboard")));
            String inputString;
            StringBuffer stringBuffer = new StringBuffer();

            while ((inputString = inputReader.readLine()) != null)
            {
                scores.add(new Score(inputString));
                arrayLength++;
                //Log.d("Test", inputString);
            }
            if(arrayLength==0)
            {
               scores.add(new Score("100,KARL"));
            }
            //score = stringBuffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sortScores(scores, arrayLength);
        for(int j = 0; j<arrayLength&&j<10; j++)
        {
            int scoreNum = j+1;
            Log.d("Test String", (scoreNum) + "." + scores.get(j).getPlayerName() + scores.get(j).getStringScore());
           // Log.d("Test String", scores[j].getPlayerName());
        }
        try {
            FileOutputStream fos = openFileOutput("leaderboard", Context.MODE_PRIVATE);
            for(int i = 0; i<arrayLength&&i<10; i++) {
                String playerScoreNameCombo= scores.get(i).getNameScoreCombo()+"\n";
                fos.write(playerScoreNameCombo.getBytes());
            }
            fos.close();

        }
        catch (Exception e)
        {
        }
        finish();
    }

    public class Score{
        private String stringScore;
        private String playerName;
        private int numberScore;
        private String nameScoreCombo;
        public Score(String nameScoreCombo)
        {
            this.nameScoreCombo = nameScoreCombo;
            int scoreLength=0;
            char character=' ';
            for(int i = 0; i<nameScoreCombo.length()&&character!=','; i++)
            {
                scoreLength=i;
                character=nameScoreCombo.charAt(i);
            }
            stringScore=nameScoreCombo.substring(0,scoreLength);
            playerName=nameScoreCombo.substring(scoreLength+1);
            numberScore=Integer.parseInt(stringScore);

        }
        public String getNameScoreCombo()
        {
            return nameScoreCombo;
        }
        public String getStringScore()
        {
            return stringScore;
        }
        public int getNumberScore()
        {
            return numberScore;
        }
        public String getPlayerName()
        {
            return playerName;
        }
        public boolean isGreaterThan(Score checkScore)
        {
            if(numberScore>checkScore.getNumberScore())
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }
    public void sortScores(ArrayList<Score> scores, int arrayLength)
    {
         for(int i = 0; i<scores.size(); i++)
         {
             for(int j = i+1; j<scores.size(); j++)
             {
                 if(scores.get(j).isGreaterThan(scores.get(i)))
                 {
                     Collections.swap(scores, i, j);
                 }
             }
         }
    }

}
