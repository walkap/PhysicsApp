package com.walkap.physicsapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.walkap.physicsapp.MyWorld;

import org.jbox2d.common.Vec2;

public class WorldView extends View {

    MyWorld world = new MyWorld();
    Paint paint = new Paint();

    Canvas canvas;

    public float x=160,y=150;
    public float x1 = world.getPivot().x, y1 = world.getPivot().y;
    public float x2=150,y2=60;

    public WorldView(Context context){
        super(context);
    }

    public WorldView(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    public void drawBox(float x, float y){
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        canvas.drawRect(x-160, y-10, x+160, y+10, paint);
    }

    public void drawGround(){
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        canvas.drawRect(0, 460, 320, 480, paint);
    }

    public void drawCircle(float x1,float y1){
        paint.setAntiAlias(true);
        paint.setColor(Color.GREEN);
        canvas.drawCircle(x1, y1, 50, paint);
    }

    public void update(){
        postInvalidate();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        drawGround();       //blue line
        drawBox(x, y);      // red line
        //drawCircle(x1, y1);
        //drawCircle(x2, y2);
        //Vec2 ballPos = world.getBall();
        update();
    }

}