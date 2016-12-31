package com.walkap.physicsapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import static android.R.attr.width;

public class WorldView extends View {

    MyWorld world = new MyWorld();
    Paint paint = new Paint();

    Canvas canvas;

    public WorldView(Context context){
        super(context);
    }

    public WorldView(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    public void update(){
        postInvalidate();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;

        paint.setARGB(100, 100, 50, 25);

        canvas.drawCircle(world.getPivot().x, world.getPivot().y, world.getPivotRadius(), paint);

        canvas.save();
        canvas.rotate(world.swingAng());

        canvas.drawRect(world.getSwing().x - world.swingWidth() / 2, world.getSwing().y - world.swingHeight() / 2,
                world.getSwing().x + world.swingWidth() / 2, world.getSwing().y + world.swingHeight() / 2, paint);

        canvas.restore();

        world.playWorld();
        update();
    }

}