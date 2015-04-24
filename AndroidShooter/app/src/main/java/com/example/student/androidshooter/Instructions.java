package com.example.student.androidshooter;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.student.androidshooter.R;


public class Instructions extends Activity implements View.OnClickListener{
    ImageButton mainMenu1;
    ImageButton mainMenu2;
    ImageButton forward1;
    ImageButton forward2;
    ImageButton backward2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions_shooting);
        mainMenu1 = (ImageButton) findViewById(R.id.mainmenu1);
        mainMenu1.setOnClickListener(this);

        forward1 = (ImageButton) findViewById(R.id.moveForward1);
        forward1.setOnClickListener(this);


    }
    @Override
    public void onClick(View v)
    {
        if(v.getId()==R.id.mainmenu1)
        {
            Intent i = getIntent();
            setResult(Activity.RESULT_OK, i);
            finish();
        }
        if(v.getId()==R.id.moveForward1)
        {
            setContentView(R.layout.activity_instructions_moving);
            forward2 = (ImageButton) findViewById(R.id.moveForward2);
            forward2.setOnClickListener(this);
            backward2 = (ImageButton) findViewById(R.id.moveBackward2);
            backward2.setOnClickListener(this);
            mainMenu2 = (ImageButton) findViewById(R.id.mainmenu2);
            mainMenu2.setOnClickListener(this);
        }
        if(v.getId()==R.id.moveBackward2)
        {
            setContentView(R.layout.activity_instructions_shooting);
            mainMenu1 = (ImageButton) findViewById(R.id.mainmenu1);
            mainMenu1.setOnClickListener(this);

            forward1 = (ImageButton) findViewById(R.id.moveForward1);
            forward1.setOnClickListener(this);
        }
        if(v.getId()==R.id.mainmenu2)
        {
            Intent i = getIntent();
            setResult(Activity.RESULT_OK, i);
            finish();
        }
    }

}
