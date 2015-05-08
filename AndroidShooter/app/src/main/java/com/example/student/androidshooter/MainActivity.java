package com.example.student.androidshooter;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class MainActivity extends Activity implements SensorEventListener {
    //Keeps track of all the different sensors we have
    private SensorManager mSensorManager;
    //A sensor which we will later register as an accelerometer sensor
    private Sensor mSensor;
    //Speed at which ball moves given by accelerometer data
    double xPos, yPos;
    //Base Y Position
    double neutralYPos = 6.7f;
    //Custom view
    MyDrawView myDrawing = null;
    //Size of screen
    int width = 0;
    int height;
    //Starting position and keeps track of where ball is currently
    float x = 0;
    float y = 0;
    //Size of ball
    int size;
    Paint p;
    Player player;
    Sprite shield;
    private ArrayList<Enemy> enemies;
    private ArrayList<Projectile> projectiles;
    private ArrayList<Sprite> upgrades;
    private Sprite heart;
    private int shootingFrames = 0;
    private int enemySpawnFrames = 0;
    private int enemySpawnRate = 50;
    private Bitmap shieldImg;
    GestureDetector gestureDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set up Heart
        heart = new Sprite(this.getApplicationContext(), 0, 0);
        heart.setImage(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.heart), 41, 48, false));
        //Set up Shield Image
        shield = new Sprite(this.getApplicationContext(), 0, 0);
        shieldImg = BitmapFactory.decodeResource(getResources(), R.drawable.shield);
        shieldImg = Bitmap.createScaledBitmap(shieldImg, 90, 50, false);
        shield.setImage(shieldImg);
        //Setting up ArrayLists
        enemies = new ArrayList<Enemy>();
        projectiles = new ArrayList<Projectile>();
        upgrades = new ArrayList<Sprite>();
        //Setting up sensor to listen to accelerometer
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mSensor, mSensorManager.SENSOR_DELAY_NORMAL);
        //Make the content view the custom view created above.
        myDrawing = new MyDrawView(this);
        setContentView(myDrawing);
        player = new Player(this.getApplicationContext());
        p = new Paint();


        //set up file reader to get the neutralypos from calibrate activity
        try {
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(
                    openFileInput("angle")));
            String inputString;
            StringBuffer stringBuffer = new StringBuffer();
            while ((inputString = inputReader.readLine()) != null) {
                stringBuffer.append(inputString + "\n");
            }
            neutralYPos = Double.valueOf(String.valueOf(stringBuffer));
            Log.i("angle", String.valueOf(neutralYPos));
        } catch (IOException e) {
            e.printStackTrace();
        }

        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                Log.d("Main activity", "double tapped, pause menu here");
                Intent intent = new Intent(getApplicationContext(), Pause_menu.class);
                startActivityForResult(intent, 0);
                return true;
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (gestureDetector.onTouchEvent(event))
            return true;
        return super.onTouchEvent(event);
    }

    //Every time the sensor data changes updates the speed at which x and y are moved
    public void onSensorChanged(SensorEvent event) {

        xPos = event.values[0];
        yPos = event.values[1];
        if (yPos <= neutralYPos) {
            player.setShield(false);
        }
        if (yPos > neutralYPos) {
            player.setShield(true);
        }
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    //Stops listening for accelerometer data when app is paused
    public void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    //Resumes listening to accelerometer data when app is brought out of paused state
    public void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensor, mSensorManager.SENSOR_DELAY_NORMAL);
    }

    public class MyDrawView extends View {
        public MyDrawView(Context context) {
            super(context);
        }

        public void onDraw(Canvas canvas) {
            //Draw Black Background
            canvas.drawColor(Color.BLACK);
            shootingFrames++;
            if (shootingFrames > 30 && !player.getShield()) {
                player.shoot(projectiles);
                shootingFrames = 0;
            }
            //Spawn enemies at a random but steady pace
            enemySpawnFrames++;
            if (enemySpawnFrames > enemySpawnRate) {
                Enemy e1;
                if (new Random().nextInt(5) == 1) //One out of every 5 enemies will be a torpedo
                {
                    e1 = new Enemy(getContext(), new Random().nextInt(width - 328), -20, "torpedo");
                } else {
                    e1 = new Enemy(getContext(), new Random().nextInt(width - 328), -20, "orb");
                }
                enemies.add(e1);
                for (int i = 0; i < enemies.size() - 1; i++) {
                    if (e1.intersects(enemies.get(i))) {
                        enemies.remove(e1);
                        break;
                    } else {
                        enemySpawnFrames = 0;
                        enemySpawnRate = new Random().nextInt(100) + 50;
                    }
                }
            }
            if (width == 0) {
                width = canvas.getWidth();
                height = canvas.getHeight();
                x = width / 2;
            }
            //Loop through all Enemies and do all checks and balances
            for (int i = 0; i < enemies.size(); i++) {
                canvas.drawBitmap(enemies.get(i).getImage(), enemies.get(i).getX(), enemies.get(i).getY(), p);
                enemies.get(i).move("y", 5);
                enemies.get(i).shoot(projectiles);
                if (enemies.get(i).getY() > height + enemies.get(i).getHeight()) {
                    enemies.remove(i);
                    break;
                }
                if (enemies.get(i).intersects(player)) {
                    if (player.getShield()) {
                        enemies.remove(i);
                        break;
                    } else {
                        enemies.remove(i);
                        player.setLives(player.getLives() - 1);
                        break;
                    }
                }
            }
            //Loop through all Upgrades
            for (int i = 0; i < upgrades.size(); i++) {
                if (upgrades.get(i) == null) {
                    break;
                }
                canvas.drawBitmap(upgrades.get(i).getImage(), upgrades.get(i).getX(), upgrades.get(i).getY(), p);
                if (upgrades.get(i).intersects(player)) {
                    player.setMissileUpgrade(true);
                    upgrades.remove(i);
                    break;
                }
                if (upgrades.get(i).getY() > height + upgrades.get(i).getHeight()) {
                    upgrades.remove(i);
                    break;
                }
                upgrades.get(i).move("y", 20);
            }
            //Loop through all Projectiles and do all checks and balances
            for (int i = 0; i < projectiles.size(); i++) {
                if (projectiles.get(i) == null) {
                    break;
                }
                canvas.drawBitmap(projectiles.get(i).getImage(), projectiles.get(i).getX(), projectiles.get(i).getY(), p);
                if (projectiles.get(i).getY() > height + projectiles.get(i).getHeight()) {
                    projectiles.remove(i);
                    break;
                }
                if (projectiles.get(i).hitEnemy()) {
                    projectiles.get(i).move("y", -20);
                    for (int j = 0; j < enemies.size(); j++) {
                        if (enemies.get(j) == null) {
                            break;
                        }
                        if (projectiles.get(i).intersects(enemies.get(j))) {
                            enemies.get(j).setHealth(enemies.get(j).getHealth() - projectiles.get(i).getDamage());
                            if (enemies.get(j).getHealth() <= 0) {
                                if (new Random().nextInt(5) == 1) {
                                    Sprite newUpgrade = new Sprite(getContext(), enemies.get(j).getX(), enemies.get(j).getY());
                                    newUpgrade.setImage(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.bulletupgrade), 45, 45, false));
                                    upgrades.add(newUpgrade);
                                }
                                enemies.remove(j);
                                player.setScore(player.getScore() + 100);
                            }
                            projectiles.remove(i);
                            break;
                        }
                    }
                } else {
                    projectiles.get(i).move("y", 20);
                    if (projectiles.get(i).intersects(player)) {
                        if (player.getShield()) {
                            projectiles.remove(i);
                            break;
                        } else {
                            player.setLives(player.getLives() - projectiles.get(i).getDamage());
                            if (player.getLives() < 1) {
                                gameOver();
                                break;
                            }
                            projectiles.remove(i);
                            break;
                        }
                    }
                }
            }
            canvas.drawBitmap(player.getImage(), (-x + width), height - player.getHeight() - 100, p);
            player.setLocation(-x + width, height - player.getHeight() - 100);
            if (player.getShield()) {
                canvas.drawBitmap(shield.getImage(), player.getX() + shield.getWidth() / 12, player.getY() - shield.getHeight() / 2, p);
            }
            //Draw Gray Bar at the bottom of the screen
            p.setColor(Color.GRAY);
            canvas.drawRect(0, height - 100, width, height, p);
            //Draw score text
            p.setColor(Color.RED);
            p.setTextSize(60);
            canvas.drawText("Score: " + player.getScore(), width - 400, height - 50, p);
            //Draw life count
            for (int i = 0; i < player.getLives(); i++) {
                canvas.drawBitmap(heart.getImage(), i * heart.getWidth() + heart.getWidth(), height - heart.getHeight(), p);
            }

            //draw a circle at the point designated based on accelerometer data and previous points with a specified size and a color P
            //canvas.drawCircle(x, y, size, p);

            //canvas.drawBitmap(player.getImage(), (float)yPos*100+width/2-25, height-100, p);
            //Move ball based on where ball is and accelerometer data
            x = (float) (xPos * 2 + x);
            //y= (float) (yPos*2 + y);

            //Log.i("xPos", String.valueOf(xPos));
            //Log.i("yPos", String.valueOf(yPos));
            //Check for collision and move ball back into bounds if collision is is found
            if (topCollision(y)) {
                y = player.getHeight();
            }

            if (leftCollision(x)) {
                x = player.getWidth();
            }

            if (rightCollision(x)) {
                x = width;
            }

            if (bottomCollision(y)) {
                y = height - player.getHeight();
            }

            invalidate();
        }
    }

    private void gameOver() {
        enemies.clear();
        projectiles.clear();
        String playersScore = "";
        if (player.getScore() == 0) {
            playersScore = "0";
        } else {
            playersScore = player.getScore() + "";
        }

        player.setScore(0);
        player.setLives(5);
        Intent intent = new Intent(getApplicationContext(), GameOver.class);
        Log.d("???", playersScore);
        intent.putExtra("Score", playersScore);
        startActivityForResult(intent, 0);
        finish();

    }

    //Checks top of screen collision or if ball goes past
    private boolean topCollision(float y) {
        if (y - player.getHeight() <= 0) {
            return true;
        } else {
            return false;
        }
    }

    //Checks left of screen collision or if ball goes past
    private boolean leftCollision(float x) {
        if (x - player.getWidth() <= 0) {
            return true;
        } else {
            return false;
        }
    }

    //Checks right of screen collision or if ball goes past
    private boolean rightCollision(float x) {
        if (x + size >= width) {
            return true;
        } else {
            return false;
        }
    }

    //Checks bottom of screen collision or if ball goes past
    private boolean bottomCollision(float y) {
        if (y + player.getHeight() >= height) {
            return true;
        } else {
            return false;
        }
    }
}
