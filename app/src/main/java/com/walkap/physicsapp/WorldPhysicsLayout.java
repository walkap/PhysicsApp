package com.walkap.physicsapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class WorldPhysicsLayout extends View {

    public WorldPhysicsLayout(Context context){
        super(context);
    }

    public WorldPhysicsLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    Paint color = new Paint();
    //world earth = new world();

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        color.setARGB(100, 0,0,0);
        canvas.drawCircle(100, 100, 100, color);
        //canvas.drawCircle(earth.getPivot().x, earth.getPivot().y, 100, color);
    }
}