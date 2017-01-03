package com.walkap.physicsapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
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
        canvas.translate(world.getPivot().x, height - world.getPivot().y);
        canvas.drawCircle(0, 0, world.getPivotRadius(), red);

        canvas.restore();
        canvas.save();

        //Swing
        canvas.translate(world.getSwing().x, height - world.getSwing().y);
        canvas.rotate((float) - (world.swingAng() * 57.2958));
        canvas.drawRect(- world.swingWidth() / 2, - world.swingHeight() / 2, world.swingWidth() / 2, world.swingHeight() / 2, red);

        canvas.restore();
        canvas.save();

        //Target
        canvas.translate(world.getTarget().x, height - world.getTarget().y);
        canvas.drawCircle(0, 0, world.getTargetRadius(), blue);

        canvas.restore();
        canvas.save();

        //Bullet
        canvas.translate(world.getBullet().x, height - world.getBullet().y);
        canvas.rotate((float) - (world.bulletAng() * 57.2958));
        canvas.drawRect(- world.bulletWidth() / 2, - world.bulletHeight() / 2, world.bulletWidth() / 2, world.bulletHeight() / 2, grey);

        canvas.restore();
        canvas.save();

        //Ground
        canvas.translate(world.getGround().x, height - world.getGround().y);
        canvas.drawRect(- world.groundWidth() / 2,  - world.groundHeight() / 2, world.groundWidth() / 2, world.groundHeight() / 2, green);

        canvas.restore();

        //If the new ball is created
        if(world.ballIsCreate()) {
            canvas.save();
            canvas.translate(world.getBall().x, height - world.getBall().y);
            canvas.drawCircle(0, 0, world.geBallRadius(), green);
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
                world.createBall((e.getX()) - left, ( height - e.getY() + 2 * top));
            }

        }
    }
}