package com.walkap.physicsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;

import org.jbox2d.common.Vec2;



public class SettingsActivity extends AppCompatActivity {
    RadioButton rad;
    CheckBox cb;
    EditText et;
    WorldView world;
    Vec2 gravity;
    float x = 0.0f , y;


    @Override
    protected void onCreate(Bundle savedIstanceState) {
        super.onCreate(savedIstanceState);
        setContentView(R.layout.activity_settings);
        et=(EditText) findViewById(R.id.editText);
        et.setVisibility(View.VISIBLE);
        cb = (CheckBox) findViewById(R.id.cbGravity);

    }

    private void setGravityManually(){
        if(cb.isChecked()){
            et.setVisibility(View.INVISIBLE);
            world.setGravityDefault(true);
        }else{
            et.setVisibility(View.VISIBLE);
            world.setGravityDefault(false);
            y = Float.valueOf(String.valueOf(et.getText()));
            gravity = new Vec2(x,y);
            world.setGravity(gravity);
        }
    }


}