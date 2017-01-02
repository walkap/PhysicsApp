package com.walkap.physicsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


/**
 * Created by luca92 on 02/01/17.
 */

public class SettingsActivity extends AppCompatActivity {
    RadioButton rad;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedIstanceState) {
        super.onCreate(savedIstanceState);
        setContentView(R.layout.activity_settings);
        tv=(TextView) findViewById(R.id.textView3);
        tv.setVisibility(View.GONE);
    }


    public void onRadioButtonClicked(View view) {

        boolean checked = (rad.isChecked());

        if(checked)
            tv.setVisibility(View.GONE);
        else
            tv.setVisibility(View.VISIBLE);
    }
}



