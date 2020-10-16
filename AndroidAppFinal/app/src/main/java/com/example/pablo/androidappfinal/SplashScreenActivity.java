package com.example.pablo.androidappfinal;


import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;
@SuppressWarnings("serial")
public class SplashScreenActivity extends Activity implements Serializable {

    private static final long SPLASH_SCREEN_DELAY = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.pantalla_carga);
        ImageView img=(ImageView)findViewById(R.id.loadingView);
        img.setBackgroundResource(R.drawable.loading);
        AnimationDrawable frameAnimation = (AnimationDrawable)img.getBackground();
        frameAnimation.start();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {


                AdminSQLiteOpenHelper ad=new AdminSQLiteOpenHelper(getApplicationContext());

                SQLiteDatabase db=ad.getReadableDatabase();


                Cursor c = db.rawQuery(" SELECT * FROM municipios", null);

                Intent mainIntent = new Intent().setClass(
                        SplashScreenActivity.this, MapsActivity.class);


                startActivity(mainIntent);


                finish();
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);
    }

}