package com.walkap.physicsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

public class WorldActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        try
        {
            WorldView.TouchEvent(event);
        }
        catch (Exception e)
        {
            Log.e("WorldActivity","error in TouchEvent");
        }

        return true;
    }
}
