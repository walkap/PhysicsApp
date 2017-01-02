package com.walkap.physicsapp;

import android.util.Log;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

import java.util.Random;

import static org.jbox2d.common.Settings.PI;

/**
 * Created by Giuseppe on 27/12/2016.
 */


public class MyWorld {

    private World world;

    private BodyDef pivot;
    private BodyDef swing;
    private BodyDef target;
    private BodyDef ball;

    private CircleShape pivotShape;
    private PolygonShape swingShape;
    private PolygonShape groundShape;
    private CircleShape targetShape;

    private FixtureDef pivotFixture;
    private FixtureDef swingFixture;
    private FixtureDef targetFixture;
    private FixtureDef ballFixture;

    private Body pivotBody;
    private Body swingBody;
    private Body targetBody;
    private Body ballBody;
    private Body groundBody;

    private float maxX;
    private float maxY;

    public MyWorld() {

        //world definition
        Vec2 gravity = new Vec2(0.0f, -10.0f);
        world = new World(gravity);

        createGround();

        createPivot(250.0f, 40.0f, 30.0f);

        createSwing(265.0f, 80.0f, 250.0f, 10.0f);

    }

    public void playWorld(){
        float timeStep = 10.0f / 60.f;
        int velocityIterations = 6;
        int positionIterations = 2;

        world.step(timeStep, velocityIterations, positionIterations);
    }

    private void createGround(){
        //ground body definition
        BodyDef ground = new BodyDef();
        Vec2 posGround = new Vec2(0.0f, 0.0f);
        ground.position.set(posGround);
        ground.angle = 0.0f;
        ground.linearVelocity = new Vec2(0.0f,0.0f);
        ground.angularVelocity = 0.0f;
        ground.fixedRotation = false;
        ground.active = true;
        ground.bullet = false;
        ground.allowSleep = true;
        ground.gravityScale = 1.0f;
        ground.linearDamping = 0.0f;
        ground.angularDamping = 0.0f;
        //bodyDef.userData = (Object)ObjectType.Floor;
        ground.type = BodyType.KINEMATIC;

        //define ground shape of the body.
        groundShape = new PolygonShape();
        groundShape.setAsBox(1000.0f,5.0f);

        //define ground fixture of the body.
        FixtureDef groundFixture = new FixtureDef();
        groundFixture.shape = groundShape;
        groundFixture.userData = null;
        groundFixture.friction = 5.0f;
        groundFixture.restitution = 0.05f;
        groundFixture.density = 1.0f;
        groundFixture.isSensor = false;

        //create the ground body and add fixture to it
        groundBody = world.createBody(ground);
        groundBody.createFixture(groundFixture);

    }

    public Vec2 getGround() {
        Vec2 posGround = groundBody.getPosition();
        return posGround;
    }

    public float groundHeight(){
        Vec2 vec0 = groundShape.getVertex(0);
        Vec2 vec2 = groundShape.getVertex(2);
        return(vec2.y - vec0.y);
    }

    public float groundWidth(){
        Vec2 vec0 = groundShape.getVertex(0);
        Vec2 vec2 = groundShape.getVertex(2);
        return(vec2.x - vec0.x);
    }

    public void setMaxX(Float newX){
        maxX = newX;
    }

    public Float getMaxX(){
        return maxX;
    }

    public void setMaxY(Float newY){
        maxY = newY;
        createTarget();
    }

    public Float getMaxY(){
        return maxY;
    }

    private void createPivot(float posX, float posY, float pivotRadius){
        //pivot body definition
        pivot = new BodyDef();
        pivot.position = new Vec2(posX, posY);
        pivot.angle = 0.0f;
        pivot.linearVelocity = new Vec2(0.0f, 0.0f);
        pivot.angularVelocity = 0.0f;
        pivot.fixedRotation = false;
        pivot.active = true;
        pivot.bullet = false;
        pivot.allowSleep = true;
        pivot.gravityScale = 1.0f;
        pivot.linearDamping = 0.0f;
        pivot.angularDamping = 0.0f;
        //pivot.userData = (Object)ObjectType.Ball;
        pivot.type = BodyType.DYNAMIC;


        //define pivot shape of the body.
        pivotShape = new CircleShape();
        pivotShape.m_radius = pivotRadius;

        //define pivot fixture of the body.
        pivotFixture = new FixtureDef();
        pivotFixture.shape = pivotShape;
        pivotFixture.userData = null;
        pivotFixture.friction = 0.45f;
        pivotFixture.restitution = 0.75f;
        pivotFixture.density = 25.0f;
        pivotFixture.isSensor = false;

        //create the pivot body and add fixture to it
        pivotBody = world.createBody(pivot);
        pivotBody.createFixture(pivotFixture);

    }

    public void destroyPivot(){
        world.destroyBody(pivotBody);
    }

    public void activePivot(Vec2 posPivot){
        pivot.position.set(posPivot);
        pivot.active = true;
    }

    public void disabledPivot(){
        pivot.active = false;
    }

    public Vec2 getPivot() {
        return pivotBody.getPosition();
    }

    public void setPivot(Vec2 posPivot) {
        pivot.position.set(posPivot);
    }

    public float getPivotRadius(){
        return(pivotShape.m_radius);
    }

    private void createSwing(Float posX, float posY,float swingWidth, float swingHeight){
        //swing body definition
        swing = new BodyDef();
        swing.position = new Vec2(posX, posY);
        swing.angle = 0.0f;
        swing.linearVelocity = new Vec2(0.0f,0.0f);
        swing.angularVelocity = 0.0f;
        swing.fixedRotation = false;
        swing.active = true;
        swing.bullet = false;
        swing.allowSleep = true;
        swing.gravityScale = 1.0f;
        swing.linearDamping = 0.0f;
        swing.angularDamping = 0.0f;
        //swing.userData = (Object)ObjectType.Box;
        swing.type = BodyType.DYNAMIC;

        //define swing shape of the body.
        swingShape = new PolygonShape();
        swingShape.setAsBox(swingWidth, swingHeight);

        //define swing fixture of the body.
        swingFixture = new FixtureDef();
        swingFixture.shape = swingShape;
        swingFixture.userData = null;
        swingFixture.friction = 0.35f;
        swingFixture.restitution = 0.05f;
        swingFixture.density = 0.75f;
        swingFixture.isSensor = false;

        //create the swing body and add fixture to it
        swingBody = world.createBody(swing);
        swingBody.createFixture(swingFixture);

    }

    public float swingHeight(){
        Vec2 vec0 = swingShape.getVertex(0);
        Vec2 vec2 = swingShape.getVertex(2);
        return(vec2.y - vec0.y);
    }

    public float swingWidth(){
        Vec2 vec0 = swingShape.getVertex(0);
        Vec2 vec2 = swingShape.getVertex(2);
        return(vec2.x - vec0.x);
    }

    public float swingAng(){
        return swingBody.getAngle();
    }

    public void destroySwing(){
        world.destroyBody(swingBody);
    }

    public void activeSwing(Vec2 posSwing){
        swing.position.set(posSwing);
        swing.active = true;
    }

    public void disabledSwing(){
        swing.active = false;
    }

    public Vec2 getSwing() {
        return swingBody.getPosition();
    }

    private void createTarget(){
        float radius = 20.0f;

        //target body definition
        target = new BodyDef();

        Random rand = new Random();
        float randX = rand.nextFloat();

        rand = new Random();
        float randY = rand.nextFloat();

        target.position = new Vec2(randX * maxX / 2 + maxX/2 - radius ,randY * maxY / 2 + maxY/2 - radius);

        target.type = BodyType.KINEMATIC;

        //define target shape of the body.
        targetShape = new CircleShape();
        targetShape.m_radius = radius;

        //define target fixture of the body.
        targetFixture = new FixtureDef();
        targetFixture.shape = targetShape;

        //create the target body and add fixture to it
        targetBody = world.createBody(target);
        targetBody.createFixture(targetFixture);
    }

    public Vec2 getTarget() {
        return targetBody.getPosition();
    }

    public float getTargetRadius(){
        return(targetShape.m_radius);
    }

    public void destroyTarget(){
        world.destroyBody(targetBody);
    }

    public void activeTarget(Vec2 posTarget){
        target.position.set(posTarget);
        target.active = true;
    }

    public void disabledTarget(){
        target.active = false;
    }

    public void createBall(Vec2 posBall, float density, float friction, float restitution) {
        ball = new BodyDef();
        ball.position.set(posBall);
        ball.type = BodyType.DYNAMIC;

        //define ball shape of the body.
        CircleShape ballShape = new CircleShape();
        ballShape.m_radius = 0.5f;

        //define ball fixture of the body.
        ballFixture = new FixtureDef();
        ballFixture.shape = ballShape;
        ballFixture.density = density;
        ballFixture.friction = friction;
        ballFixture.restitution = restitution;

        //create the ball body and add fixture to it
        ballBody = world.createBody(ball);
        ballBody.createFixture(ballFixture);

    }

    public void destroyBall(){
        world.destroyBody(ballBody);
    }

    public void activeBall(Vec2 posBall){
        ball.position.set(posBall);
        ball.active = true;
    }

    public void disabledBall(){
        ball.active = false;
    }

    public Vec2 getBall() {
        return ballBody.getPosition();
    }

    public void setBall(Vec2 posBall) {
        ball.position.set(posBall);
    }


}