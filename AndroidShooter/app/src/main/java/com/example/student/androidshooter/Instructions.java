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
    ImageButton mainMenu3;
    ImageButton mainMenu4;
    ImageButton forward1;
    ImageButton forward2;
    ImageButton forward3;
    ImageButton backward2;
    ImageButton backward3;
    ImageButton backward4;
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
        if(v.getId()==R.id.moveForward2)
        {
            setContentView(R.layout.activity_instructions_howtoplay);
            mainMenu3 = (ImageButton) findViewById(R.id.mainmenu3);
            mainMenu3.setOnClickListener(this);

            forward3 = (ImageButton) findViewById(R.id.moveForward3);
            forward3.setOnClickListener(this);
            backward3 = (ImageButton) findViewById(R.id.moveBackward3);
            backward3.setOnClickListener(this);
        }
        if(v.getId()==R.id.moveBackward3)
        {
            setContentView(R.layout.activity_instructions_moving);
            forward2 = (ImageButton) findViewById(R.id.moveForward2);
            forward2.setOnClickListener(this);
            backward2 = (ImageButton) findViewById(R.id.moveBackward2);
            backward2.setOnClickListener(this);
            mainMenu2 = (ImageButton) findViewById(R.id.mainmenu2);
            mainMenu2.setOnClickListener(this);
        }
        if(v.getId()==R.id.mainmenu3)
        {
            Intent i = getIntent();
            setResult(Activity.RESULT_OK, i);
            finish();
        }
        if(v.getId()==R.id.moveForward3)
        {
            setContentView(R.layout.activity_instructions_powerups);
            backward4 =  (ImageButton) findViewById(R.id.moveBackward4);
            backward4.setOnClickListener(this);
            mainMenu4 = (ImageButton) findViewById(R.id.mainmenu4);
            mainMenu4.setOnClickListener(this);
        }
        if(v.getId()==R.id.moveBackward4)
        {
            setContentView(R.layout.activity_instructions_howtoplay);
            forward3 = (ImageButton) findViewById(R.id.moveForward3);
            forward3.setOnClickListener(this);
            backward3 = (ImageButton) findViewById(R.id.moveBackward3);
            backward3.setOnClickListener(this);
            mainMenu3 = (ImageButton) findViewById(R.id.mainmenu3);
            mainMenu3.setOnClickListener(this);
        }
        if(v.getId()==R.id.mainmenu4)
        {
            Intent i = getIntent();
            setResult(Activity.RESULT_OK, i);
            finish();
        }
    }


}
