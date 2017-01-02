package com.walkap.physicsapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import org.jbox2d.common.Vec2;

public class WorldView extends View {

    static MyWorld world= new MyWorld();
    Paint paint = new Paint();
    boolean hey = true;

    static float height;
    static float width;

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

        if(hey) {
            height = this.getHeight();
            width = this.getWidth();
            world.setMaxX(width);
            world.setMaxY(height);
            hey = false;
        }
        paint.setARGB(100, 255, 0, 0);

        canvas.save();
        canvas.translate(world.getPivot().x, height - world.getPivot().y);

        canvas.drawCircle(0, 0, world.getPivotRadius(), paint);

        canvas.restore();

        canvas.save();
        canvas.translate(world.getSwing().x, height - world.getSwing().y);
        canvas.rotate((float) - (world.swingAng() * 57.2958));

        canvas.drawRect(- world.swingWidth() / 2, - world.swingHeight() / 2, world.swingWidth() / 2, world.swingHeight() / 2, paint);



        canvas.restore();

        paint.setARGB(100, 0, 0, 255);

        canvas.save();
        canvas.translate(world.getTarget().x, height - world.getTarget().y);

        canvas.drawCircle(0, 0, world.getTargetRadius(), paint);

        canvas.restore();

        paint.setARGB(100, 120, 120, 0);

        canvas.save();
        canvas.translate(world.getBullet().x, height - world.getBullet().y);
        canvas.rotate((float) - (world.bulletAng() * 57.2958));

        canvas.drawRect(- world.bulletWidth() / 2, - world.bulletHeight() / 2, world.bulletWidth() / 2, world.bulletHeight() / 2, paint);

        canvas.restore();

        paint.setARGB(100, 0, 255, 0);

        canvas.save();
        canvas.translate(world.getGround().x, height - world.getGround().y);

        canvas.drawRect(- world.groundWidth() / 2,  - world.groundHeight() / 2, world.groundWidth() / 2, world.groundHeight() / 2, paint);

        canvas.restore();
        if(world.ballIsCreate()) {
            canvas.save();
            canvas.translate(world.getBall().x, height - world.getBall().y);

            canvas.drawCircle(0, 0, world.geBallRadius(), paint);

            canvas.restore();
        }

        /*if(world.bulletHitTarget()){
            Log.e("worldView", " the bullet hit the target");
        }*/

        //Log.e("WorldView", "height ball" +  (this.getHeight() - world.getPivot().y) + "\n");
        //Log.e("WorldView", "height swing" + (this.getHeight() - (world.getSwing().y - world.swingHeight() / 2)) + "\n");
        //Log.e("WorldView", "height swing" + (this.getHeight() - (world.getSwing().y + world.swingHeight() / 2)) + "\n");

        world.playWorld();
        update();
    }

    public static void TouchEvent(MotionEvent e) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            //Log.e("worldView", "e.getX " + e.getX() + "\n");
            //Log.e("worldView", "e.getY " + (height - e.getY()) + "\n");
            world.createBall((e.getX() - 60.0f), (height - e.getY() + 85.0f));
        }
    }

}