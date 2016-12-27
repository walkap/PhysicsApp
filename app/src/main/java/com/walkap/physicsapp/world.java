package com.walkap.physicsapp;

import org.jbox2d.collision.shapes.CircleShape;
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

    private BodyDef pivot;
    private BodyDef ball;
    private BodyDef target;
    private World world;
    private float maxX;
    private float maxY;

    public world() {

        //world definition
        Vec2 gravity = new Vec2(10.0f, 0.0f);
        world = new World(gravity);

        //pivot body definition
        pivot = new BodyDef();
        Vec2 posPivot = new Vec2(50.0f, 10.0f);
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
        pivotShape.m_radius = 0.5f;

        //define target fixture of the body.
        FixtureDef targetFixture = new FixtureDef();
        pivotFixture.shape = targetShape;

        //create the target body and add fixture to it
        Body targetBody = world.createBody(target);
        targetBody.createFixture(targetFixture);

    }

    public Vec2 getPivot() {
        Vec2 posPivot = pivot.position;
        return posPivot;
    }

    public void setPivot(Vec2 posPivot) {
        pivot.position.set(posPivot);
    }

    public void createBall(Vec2 posBall, float density, float friction, float restitution) {
        ball = new BodyDef();
        ball.position.set(posBall);
        ball.type = BodyType.DYNAMIC;

        //define ball shape of the body.
        CircleShape ballShape = new CircleShape();
        ballShape.m_radius = 0.5f;

        //define pivot fixture of the body.
        FixtureDef ballFixture = new FixtureDef();
        ballFixture.shape = ballShape;
        ballFixture.density = density;
        ballFixture.friction = friction;
        ballFixture.restitution = restitution;

        //create the pivot body and add fixture to it
        Body ballBody = world.createBody(ball);
        ballBody.createFixture(ballFixture);

    }

    public Vec2 getBall() {
        Vec2 posBall = pivot.position;
        return posBall;
    }

    public void setBall(Vec2 posBall) {
        ball.position.set(posBall);
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

}
