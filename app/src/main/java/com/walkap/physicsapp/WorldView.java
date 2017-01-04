package com.walkap.physicsapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import org.jbox2d.common.Vec2;

import java.util.logging.Handler;


public class WorldView extends View {

    static MyWorld world;
    Paint red = new Paint();
    Paint green = new Paint();
    Paint grey = new Paint();
    Paint blue = new Paint();


    boolean hey = true;

    static float height;
    static float width;

    static float top;
    static float left;

    static boolean gravityDefault = true;
    static Vec2 gravity = new Vec2(0.0f, 1.0f);

    Canvas canvas;

    public WorldView(Context context){
        super(context);
    }

    public WorldView(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    public void update(){
        new Thread(new Runnable() {
            public void run(){
                postInvalidate();
            }
        }).start();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;

        red.setARGB(100, 255, 0, 0);
        green.setARGB(100, 0, 255, 0);
        grey.setARGB(100, 120, 120, 0);
        blue.setARGB(100, 0, 0, 255);

        if(hey) {
            height = this.getHeight();
            width = this.getWidth();
            if(gravityDefault) {
                world = new MyWorld(width);
            }
            else {
                world = new MyWorld(gravity, width);
                Log.e("WorldView", "" + gravity.y);
            }
            top = getTop();
            left = getLeft();
            hey = false;
        }

        canvas.save();

        //Pivot
        canvas.translate(world.getPivot().x * 10, height - world.getPivot().y * 10);
        canvas.drawCircle(0, 0, world.getPivotRadius() * 10, red);

        canvas.restore();
        canvas.save();

        //Swing
        canvas.translate(world.getSwing().x * 10, height - world.getSwing().y * 10);
        canvas.rotate((float) - (world.swingAng() * 57.2958));
        canvas.drawRect(- world.swingWidth() / 2 * 10, - world.swingHeight() / 2 * 10, world.swingWidth() / 2 * 10, world.swingHeight() / 2 * 10, red);

        canvas.restore();
        canvas.save();

        //Bullet
        canvas.translate(world.getBullet().x * 10, height - world.getBullet().y * 10);
        canvas.rotate((float) - (world.bulletAng() * 57.2958));
        canvas.drawRect(- world.bulletWidth() / 2 * 10, - world.bulletHeight() / 2 * 10, world.bulletWidth() / 2 * 10, world.bulletHeight() / 2 * 10, grey);

        canvas.restore();
        canvas.save();

        //Ground
        canvas.translate(world.getGround().x * 10, height - world.getGround().y * 10);
        canvas.drawRect(- world.groundWidth() / 2 * 10,  - world.groundHeight() / 2 * 10, world.groundWidth() / 2 * 10, world.groundHeight() / 2 * 10, green);

        canvas.restore();

        //If the new ball is created
        if(world.ballIsCreate()) {
            canvas.save();
            canvas.translate(world.getBall().x * 10, height - world.getBall().y * 10);
            canvas.rotate((float) - (world.ballAng() * 57.2958));
            canvas.drawRect(- world.ballWidth() / 2 * 10,  - world.ballHeight() / 2 * 10, world.ballWidth() / 2 * 10, world.ballHeight() / 2 * 10, green);
            canvas.restore();
        }

        world.playWorld();
        update();
    }

    public static void TouchEvent(MotionEvent e) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            float x = e.getX();
            float y = e.getY();
            if(!world.ballIsCreate()) {
                world.createBall((x - left) / 10, (height - y + 2 * top) / 10, 0.2f, 0.8f, 100.0f);
            }

        }
    }
}