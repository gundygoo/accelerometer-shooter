package com.example.student.androidshooter;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by student on 5/1/2015.
 */
public class Pause_menu extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pause_menu);
        Log.d("Main Menu", "Activity Started");
    }
}
