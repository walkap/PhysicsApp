package com.walkap.physicsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;

import android.widget.RadioGroup;

public class SettingsActivity extends AppCompatActivity {

    float ygravity = 1.0f;

    public void GoToGame(View view) {

        RadioGroup radiogroup = (RadioGroup) findViewById(R.id.radiogroup);
        int checkedId = radiogroup.getCheckedRadioButtonId();
        switch (checkedId) {
            // Set Earth Gravity
            case R.id.radioButton4:
                ygravity = -1f;
                break;

            // Set Venus Gravity
            case R.id.radioButton3:
                ygravity = -.9f;
                break;

            // Set Jupiter Gravity
            case R.id.radioButton:
                ygravity = -2.5f;
                break;

            // Set Saturn Gravity
            case R.id.radioButton2:
                ygravity = -1.1f;
                break;
            case R.id.rbInverse:
                ygravity = 1.0f;
                break;
        }

        Intent intent = new Intent(this, WorldActivity.class);
        String message = "" + ygravity;
        intent.putExtra("EXTRA_MESSAGE", message);
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedIstanceState) {
        super.onCreate(savedIstanceState);
        setContentView(R.layout.activity_settings);
    }
}