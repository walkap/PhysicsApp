package com.walkap.physicsapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

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

        paint.setARGB(100, 255, 0, 0);

        canvas.drawCircle(world.getPivot().x, world.getPivot().y, world.getPivotRadius(), paint);

        canvas.save();
        canvas.rotate((float) (world.swingAng() * 57.2958));

        canvas.drawRect(world.getSwing().x - world.swingWidth() / 2,  (world.getSwing().y - world.swingHeight() / 2),
                world.getSwing().x + world.swingWidth() / 2, (world.getSwing().y + world.swingHeight() / 2), paint);

        canvas.restore();

        paint.setARGB(100, 0, 255, 0);

        canvas.drawRect(world.getGround().x - world.groundWidth() / 2,  (world.getGround().y - world.groundHeight() / 2),
                world.getGround().x + world.groundWidth() / 2, (world.getGround().y + world.groundHeight() / 2), paint);

        world.playWorld();
        update();
    }

}