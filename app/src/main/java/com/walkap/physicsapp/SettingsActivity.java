package com.walkap.physicsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


/**
 * Created by luca92 on 02/01/17.
 */

public class SettingsActivity extends AppCompatActivity {
    RadioButton rad;
    EditText et;


    @Override
    protected void onCreate(Bundle savedIstanceState) {
        super.onCreate(savedIstanceState);
        setContentView(R.layout.activity_settings);
        et=(EditText) findViewById(R.id.editText);
        et.setVisibility(View.VISIBLE);
    }


    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        if(checked)
            et.setVisibility(View.GONE);
        else
            et.setVisibility(View.VISIBLE);
    }
}



