package com.walkap.physicsapp;

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


public class world {

    private World world;

    private BodyDef pivot;
    private BodyDef swing;
    private BodyDef target;
    private BodyDef ball;

    private float maxX;
    private float maxY;

    public world() {

        //world definition
        Vec2 gravity = new Vec2(10.0f, 0.0f);
        world = new World(gravity);

        createPivot(10.0f, 10.0f);

        createSwing(20.0f, 20.0f);

        createTarget(2.0f, 2.0f);

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

    private void createPivot(float posX, float posY){
        //pivot body definition
        pivot = new BodyDef();
        Vec2 posPivot = new Vec2(posX, posY);
        pivot.position.set(posPivot);
        pivot.type = BodyType.DYNAMIC;

        //define pivot shape of the body.
        CircleShape pivotShape = new CircleShape();
        pivotShape.m_radius = 0.5f;

        //define pivot fixture of the body.
        FixtureDef pivotFixture = new FixtureDef();
        pivotFixture.shape = pivotShape;
        pivotFixture.density = 0.5f;
        pivotFixture.friction = 0.3f;
        pivotFixture.restitution = 0.5f;

        //create the pivot body and add fixture to it
        Body pivotBody = world.createBody(pivot);
        pivotBody.createFixture(pivotFixture);

    }

    public Vec2 getPivot() {
        Vec2 posPivot = pivot.position;
        return posPivot;
    }

    public void setPivot(Vec2 posPivot) {
        pivot.position.set(posPivot);
    }

    private void createSwing(Float posX, float posY){
        //swing body definition
        swing = new BodyDef();
        Vec2 posSwing = new Vec2(posX, posY);
        swing.position.set(posSwing);
        swing.type = BodyType.DYNAMIC;

        Vec2 vertices[] = new Vec2[4];
        vertices[0] = new Vec2(-10.0f, 0.5f);
        vertices[1] = new Vec2( 10.0f, 0.5f);
        vertices[2] = new Vec2( 10.0f,-0.5f);
        vertices[3] = new Vec2(-10.0f,-0.5f);

        PolygonShape swingShape = new PolygonShape();
        swingShape.set(vertices, 4);

        //define swing fixture of the body.
        FixtureDef swingFixture = new FixtureDef();
        swingFixture.shape = swingShape;
        swingFixture.density = 0.5f;
        swingFixture.friction = 0.3f;
        swingFixture.restitution = 0.5f;

        //create the swing body and add fixture to it
        Body swingBody = world.createBody(swing);
        swingBody.createFixture(swingFixture);

    }

    public Vec2 getSwing() {
        Vec2 posSwing = swing.position;
        return posSwing;
    }

    public void setSwing(Vec2 posPivot) {
        swing.position.set(posPivot);
    }

    private void createTarget(float posX, float posY){
        //target body definition
        target = new BodyDef();

        Random rand = new Random();
        float randX = rand.nextFloat();

        rand = new Random();
        float randY = rand.nextFloat();

        Vec2 posTarget = new Vec2(randX * maxX,randY * maxY );
        target.type = BodyType.STATIC;

        //define target shape of the body.
        CircleShape targetShape = new CircleShape();
        targetShape.m_radius = 0.5f;

        //define target fixture of the body.
        FixtureDef targetFixture = new FixtureDef();
        targetFixture.shape = targetShape;

        //create the target body and add fixture to it
        Body targetBody = world.createBody(target);
        targetBody.createFixture(targetFixture);
    }

    public void createBall(Vec2 posBall, float density, float friction, float restitution) {
        ball = new BodyDef();
        ball.position.set(posBall);
        ball.type = BodyType.DYNAMIC;

        //define ball shape of the body.
        CircleShape ballShape = new CircleShape();
        ballShape.m_radius = 0.5f;

        //define ball fixture of the body.
        FixtureDef ballFixture = new FixtureDef();
        ballFixture.shape = ballShape;
        ballFixture.density = density;
        ballFixture.friction = friction;
        ballFixture.restitution = restitution;

        //create the ball body and add fixture to it
        Body ballBody = world.createBody(ball);
        ballBody.createFixture(ballFixture);

    }

    public Vec2 getBall() {
        Vec2 posBall = ball.position;
        return posBall;
    }

    public void setBall(Vec2 posBall) {
        ball.position.set(posBall);
    }


}
