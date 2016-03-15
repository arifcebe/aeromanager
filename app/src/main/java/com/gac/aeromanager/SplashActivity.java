package com.gac.aeromanager;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;

/**
 * Created by arifcebe on 8/30/14.
 */
public class SplashActivity extends Activity {
    private static long SLEEP = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash_activity);

        View decorView = getWindow().getDecorView();
        int uiOption = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOption);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor edit = preferences.edit();

        if(preferences.getBoolean("isFirstLaunch",true)){
            edit.putBoolean("isFirstLaunch",false);
            Log.d("splash","first launch");
            edit.commit();
            SplashThread thread = new SplashThread();
            thread.start();
        }else {
            Intent intent = new Intent(SplashActivity.this,MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }



    }

    private class SplashThread extends Thread{
        @Override
        public void run() {
            super.run();
            try {
                Thread.sleep(SLEEP * 1000);
                /* CREATE DATABASE AND TABLE */
                new DBHandler(getApplicationContext())
                        .getWritableDatabase()
                        .close();
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
