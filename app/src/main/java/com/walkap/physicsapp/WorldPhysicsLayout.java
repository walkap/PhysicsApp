package com.walkap.physicsapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by robertocapannelli on 28/12/16.
 */

public class WorldPhysicsLayout extends View {

    public WorldPhysicsLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    Paint color = new Paint();

    world earth = new world();

    @Override
    protected void onDraw(Canvas canvas){

        super.onDraw(canvas);
        color.setARGB(80, 0,0,0);
        canvas.drawCircle(earth.getPivot().x, earth.getPivot().y, 100, color);

    }


}