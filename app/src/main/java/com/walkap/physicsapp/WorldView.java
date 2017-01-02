package com.walkap.physicsapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import static java.lang.Math.PI;

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
        float height = this.getHeight();

        paint.setARGB(100, 255, 0, 0);

        canvas.save();
        canvas.translate(world.getPivot().x, world.getPivot().y);

        canvas.drawCircle(0, 0, world.getPivotRadius(), paint);

        canvas.restore();

        canvas.save();
        canvas.translate(world.getSwing().x, world.getSwing().y);
        canvas.rotate((float) (world.swingAng() * 57.2958));

        canvas.drawRect(- world.swingWidth() / 2, - world.swingHeight() / 2, world.swingWidth() / 2, world.swingHeight() / 2, paint);

        canvas.restore();

        paint.setARGB(100, 0, 255, 0);

        canvas.save();
        canvas.translate(world.getGround().x, world.getGround().y);

        canvas.drawRect(- world.groundWidth() / 2,  - world.groundHeight() / 2, world.groundWidth() / 2, world.groundHeight() / 2, paint);

        canvas.restore();

        //Log.e("WorldView", "height ball" +  (this.getHeight() - world.getPivot().y) + "\n");
        //Log.e("WorldView", "height swing" + (this.getHeight() - (world.getSwing().y - world.swingHeight() / 2)) + "\n");
        //Log.e("WorldView", "height swing" + (this.getHeight() - (world.getSwing().y + world.swingHeight() / 2)) + "\n");

        world.playWorld();
        update();
    }

}