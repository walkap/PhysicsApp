package com.walkap.physicsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import static com.walkap.physicsapp.WorldView.world;

public class WorldActivity extends AppCompatActivity implements View.OnClickListener{

    Button resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world);


        resetButton = (Button) findViewById(R.id.resetButton);
        resetButton.setOnClickListener(this);
    }

    @Override
    protected void onPause(){

        super.onPause();
    }

    public void onClick(View resetButton){
        try{
            world.resetWorld();
        } catch (Exception e) {
            Log.e("WorldActivity","error in Reset");
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        try {
            WorldView.TouchEvent(event);
        } catch (Exception e) {
            Log.e("WorldActivity","error in TouchEvent");
        }
        return true;
    }
}
