package com.walkap.physicsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.jbox2d.common.Vec2;

import static android.R.attr.type;
import static android.R.attr.value;


public class SettingsActivity extends AppCompatActivity {

    //CheckBox cb;
    //EditText et;
    WorldView world;
    Vec2 gravity;
    float x = 0.0f , ygravity;
    RadioButton rbEarth;
    RadioButton rbVenus;
    RadioButton rbJupiter;
    RadioButton rbSaturn;




    @Override
    protected void onCreate(Bundle savedIstanceState) {
        super.onCreate(savedIstanceState);
        setContentView(R.layout.activity_settings);
        RadioGroup radiogroup = (RadioGroup) findViewById(R.id.radiogroup);
        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {

                    // Set Earth Gravity
                    case R.id.radioButton4:
                        ygravity = -10f;
                        break;

                    // Set Venus Gravity
                    case R.id.radioButton3:
                        ygravity = -9f;
                        break;

                   // Set Jupiter Gravity
                    case R.id.radioButton:
                        ygravity = -25f;
                        break;

                    // Set Saturn Gravity
                    case R.id.radioButton2:
                        ygravity = -11f;
                        break;
                }

                gravity = new Vec2(x , ygravity);
                //world.setGravity(gravity);
            }
        });







        /*et=(EditText) findViewById(R.id.editText);
        et.setVisibility(View.VISIBLE);

        cb = (CheckBox) findViewById(R.id.cbGravity);
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(cb.isChecked()){
                    et.setVisibility(View.GONE);
                    world.setGravityDefault(true);
                }else{
                    et.setVisibility(View.VISIBLE);
                    world.setGravityDefault(false);
                    gravity = new Vec2(x,y);
                    world.setGravity(gravity);
                }
            }
        });*/


    }


    /*private void setGravityManually(){
        if(cb.isChecked()){
            et.setVisibility(View.GONE);
            world.setGravityDefault(true);
        }else{
            et.setVisibility(View.VISIBLE);
            world.setGravityDefault(false);
            gravity = new Vec2(x,y);
            world.setGravity(gravity);
        }
    }*/


}