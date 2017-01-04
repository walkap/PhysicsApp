package com.walkap.physicsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void startGame(View view) {
        Intent intent = new Intent(this, WorldActivity.class);
        String message = "main";
        intent.putExtra("EXTRA_MESSAGE", message);
        WorldView.gravityDefault = true;
        startActivity(intent);
    }

    public void settingsGame(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}