package com.walkap.physicsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import org.jbox2d.common.Vec2;

import static com.walkap.physicsapp.WorldView.width;
import static com.walkap.physicsapp.WorldView.world;

public class WorldActivity extends AppCompatActivity implements View.OnClickListener{

    Button resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world);

        Intent intent = getIntent();
        String message = intent.getStringExtra("EXTRA_MESSAGE");
        if(!message.equals("main")) {
            WorldView.gravityDefault = false;
            WorldView.gravity = new Vec2(0.0f, Float.parseFloat(message));
        }

        resetButton = (Button) findViewById(R.id.resetButton);
        resetButton.setOnClickListener(this);
    }

    @Override
    protected void onPause(){

        super.onPause();
    }

    public void onClick(View resetButton){
        try{
            world.resetWorld(width);
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
