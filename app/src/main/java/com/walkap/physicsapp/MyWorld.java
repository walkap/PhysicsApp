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

        createPivot(25.0f, 20.0f, 15.0f);

        createSwing(50.0f, 80.0f, 65.0f, 5.0f);

        //createTarget(2.0f, 2.0f);

    }

    public void playWorld(){
        float timeStep = 1.0f / 60.f;
        int velocityIterations = 6;
        int positionIterations = 2;

        world.step(timeStep, velocityIterations, positionIterations);
    }

    private void createGround(){
        //ground body definition
        BodyDef ground = new BodyDef();
        Vec2 posGround = new Vec2(0.0f, 0.0f);
        ground.position.set(posGround);
        ground.type = BodyType.STATIC;

        //define ground shape of the body.
        groundShape = new PolygonShape();
        groundShape.setAsBox(1000.0f,10.0f);

        //define ground fixture of the body.
        FixtureDef groundFixture = new FixtureDef();
        groundFixture.shape = groundShape;
        groundFixture.density = 0.5f;
        groundFixture.friction = 0.3f;
        groundFixture.restitution = 0.5f;

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
    }

    public Float getMaxY(){
        return maxY;
    }

    private void createPivot(float posX, float posY, float pivotRadius){
        //pivot body definition
        pivot = new BodyDef();
        Vec2 posPivot = new Vec2(posX, posY);
        pivot.position.set(posPivot);
        pivot.type = BodyType.DYNAMIC;

        //define pivot shape of the body.
        pivotShape = new CircleShape();
        pivotShape.m_radius = pivotRadius;

        //define pivot fixture of the body.
        pivotFixture = new FixtureDef();
        pivotFixture.shape = pivotShape;
        pivotFixture.density = 1.0f;
        pivotFixture.friction = 0.3f;
        pivotFixture.restitution = 0.5f;

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
        Vec2 posPivot = pivotBody.getPosition();
        return posPivot;
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
        Vec2 posSwing = new Vec2(posX, posY);
        swing.position.set(posSwing);
        swing.type = BodyType.DYNAMIC;

        //define swing shape of the body.
        swingShape = new PolygonShape();
        swingShape.setAsBox(swingWidth, swingHeight);

        //define swing fixture of the body.
        swingFixture = new FixtureDef();
        swingFixture.shape = swingShape;
        swingFixture.density = 1.0f;
        swingFixture.friction = 0.3f;
        swingFixture.restitution = 0.5f;

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
        Vec2 posSwing = swingBody.getPosition();
        return posSwing;
    }

    private void createTarget(float posX, float posY){
        //target body definition
        target = new BodyDef();

        Random rand = new Random();
        float randX = rand.nextFloat();

        rand = new Random();
        float randY = rand.nextFloat();

        Vec2 posTarget = new Vec2(randX * maxX,randY * maxY );
        target.type = BodyType.KINEMATIC;

        //define target shape of the body.
        CircleShape targetShape = new CircleShape();
        targetShape.m_radius = 0.5f;

        //define target fixture of the body.
        targetFixture = new FixtureDef();
        targetFixture.shape = targetShape;

        //create the target body and add fixture to it
        targetBody = world.createBody(target);
        targetBody.createFixture(targetFixture);
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
        Vec2 posBall = ballBody.getPosition();
        return posBall;
    }

    public void setBall(Vec2 posBall) {
        ball.position.set(posBall);
    }


}