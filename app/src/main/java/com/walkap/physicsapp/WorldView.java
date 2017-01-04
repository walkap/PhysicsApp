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

    boolean gravityDefault = true;
    Vec2 gravity;

    Canvas canvas;

    public WorldView(Context context){
        super(context);
    }

    public WorldView(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    public void update(){
        /*Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Handler h = new Handler (Lopper.getMainLopper());
                h.post(new Runnable() {
                    public void run() {
                        invalidate();
                    }
                });
                //postInvalidate();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();*/

        postInvalidate();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;

        red.setARGB(100, 255, 0, 0);
        green.setARGB(100, 0, 255, 0);
        grey.setARGB(100, 120, 120, 0);
        blue.setARGB(100, 0, 0, 255);

        if(hey) {
            if(gravityDefault) {
                world = new MyWorld();
            }
            else {
                world = new MyWorld(gravity);
            }
            height = this.getHeight();
            width = this.getWidth();
            world.setMaxX(width);
            world.setMaxY(height);
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

        //Target
        canvas.translate(world.getTarget().x * 10, height - world.getTarget().y * 10);
        canvas.drawCircle(0, 0, world.getTargetRadius() * 10, blue);

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
            canvas.drawCircle(0, 0, world.geBallRadius() * 10, green);
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

    public void setGravityDefault(boolean gravityDefaultSet){
        gravityDefault = gravityDefaultSet;
    }

    public void setGravity(Vec2 gravitySet){
        gravity = gravitySet;
    }

    public static void TouchEvent(MotionEvent e) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            //Log.e("worldView", "e.getX " + e.getX() + "\n");
            //Log.e("worldView", "e.getY " + (e.getY()) + "\n");
            if(!world.ballIsCreate()) {
                world.createBall(((e.getX()) - left) / 10, (height - e.getY() + 2 * top) / 10);
            }

        }
    }
}