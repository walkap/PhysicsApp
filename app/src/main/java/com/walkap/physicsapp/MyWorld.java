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


public class MyWorld {

    private World world;

    private BodyDef pivot;
    private BodyDef swing;
    private BodyDef ball;
    private BodyDef bullet;

    private CircleShape pivotShape;
    private PolygonShape swingShape;
    private PolygonShape groundShape;
    private CircleShape ballShape;
    private PolygonShape bulletShape;

    private FixtureDef pivotFixture;
    private FixtureDef swingFixture;
    private FixtureDef ballFixture;
    private FixtureDef bulletFixture;

    private Body pivotBody;
    private Body swingBody;
    private Body ballBody;
    private Body groundBody;
    private Body bulletBody;


    private boolean ballCreate = false;

    private void CreateBodies(float width){

        float frictionPivot = 0.5f, restitutionPivot = 0.1f, densityPivot=80.0f;
        float frictionSwing = 0.2f, restitutionSwing = 0.6f, densitySwing=0.001f;
        float frictionBullet = 0.5f, restitutionBullet = 0.1f, densityBullet=0.0001f;

        createPivot(width / 20, 6f, 5f, frictionPivot, restitutionPivot, densityPivot);

        createSwing(width / 20 + 0.2f, 8.0f, 45.0f, 1.0f, frictionSwing, restitutionSwing, densitySwing);

        createBullet(width / 20 + 40.0f, 10.5f,frictionBullet, restitutionBullet, densityBullet);
    }

    private void DestroyBodies(){
        destroyPivot();
        destroySwing();

        if(ballIsCreate()){
            destroyBall();
        }

        destroyBullet();
    }

    public MyWorld(float width){

        //world definition
        Vec2 gravity = new Vec2(0.0f, -1.0f);
        world = new World(gravity);

        createGround(width);

        CreateBodies(width);

    }

    public MyWorld(Vec2 gravity, float width) {

        //world definition
        world = new World(gravity);

        createGround(width);

        CreateBodies(width);

    }

    public void resetWorld(float width){

        DestroyBodies();

        CreateBodies(width);
    }

    public void playWorld(){
        float timeStep = 20.0f / 60.f;
        int velocityIterations = 6;
        int positionIterations = 2;

        world.step(timeStep, velocityIterations, positionIterations);
    }

    private void createGround(float width){
        //ground body definition
        BodyDef ground = new BodyDef();
        Vec2 posGround = new Vec2(width / 2, 0.0f);
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
        ground.type = BodyType.KINEMATIC;

        //define ground shape of the body.
        groundShape = new PolygonShape();
        groundShape.setAsBox(width / 2,0.5f);

        //define ground fixture of the body.
        FixtureDef groundFixture = new FixtureDef();
        groundFixture.shape = groundShape;
        groundFixture.userData = null;
        groundFixture.friction = 0.8f;
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

    private void createPivot(float posX, float posY, float pivotRadius, float friction, float restitution, float density){
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
        pivot.type = BodyType.KINEMATIC;

        //define pivot shape of the body.
        pivotShape = new CircleShape();
        pivotShape.m_radius = pivotRadius;

        //define pivot fixture of the body.
        pivotFixture = new FixtureDef();
        pivotFixture.shape = pivotShape;
        pivotFixture.userData = null;
        pivotFixture.friction = friction;
        pivotFixture.restitution = restitution;
        pivotFixture.density = density;
        pivotFixture.isSensor = false;

        //create the pivot body and add fixture to it
        pivotBody = world.createBody(pivot);
        pivotBody.createFixture(pivotFixture);

    }

    public void destroyPivot(){
        world.destroyBody(pivotBody);
    }

    public Vec2 getPivot() {
        return pivotBody.getPosition();
    }

    public float getPivotRadius(){
        return(pivotShape.m_radius);
    }

    private void createSwing(Float posX, float posY,float swingWidth, float swingHeight, float friction, float restitution, float density){
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
        swing.type = BodyType.DYNAMIC;

        //define swing shape of the body.
        swingShape = new PolygonShape();
        swingShape.setAsBox(swingWidth, swingHeight);

        //define swing fixture of the body.
        swingFixture = new FixtureDef();
        swingFixture.shape = swingShape;
        swingFixture.userData = null;
        swingFixture.friction = friction;
        swingFixture.restitution = restitution;
        swingFixture.density = density;
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

    public Vec2 getSwing() {
        return swingBody.getPosition();
    }

    public void createBall(float posX, float posY,float friction,float restitution,float density) {
        ballCreate = true;

        ball = new BodyDef();
        ball.position.set(new Vec2(posX, posY));
        ball.gravityScale = 20.0f;
        ball.type = BodyType.DYNAMIC;


        //define ball shape of the body.
        ballShape = new CircleShape();
        ballShape.m_radius = 10f;

        //define ball fixture of the body.
        ballFixture = new FixtureDef();
        ballFixture.shape = ballShape;
        swingFixture.friction = friction;
        swingFixture.restitution = restitution;
        swingFixture.density = density;

        //create the ball body and add fixture to it
        ballBody = world.createBody(ball);
        ballBody.createFixture(ballFixture);

    }

    public void destroyBall(){
        ballCreate = false;
        world.destroyBody(ballBody);
    }

    public Vec2 getBall() {
        return ballBody.getPosition();
    }

    public float geBallRadius(){
        return(ballShape.m_radius);
    }

    public boolean ballIsCreate(){
        return ballCreate;
    }

    private void createBullet(Float posX, float posY, float friction, float restitution, float density){
        //bullet body definition
        bullet = new BodyDef();
        bullet.position = new Vec2(posX, posY);
        bullet.angle = 0.0f;
        bullet.linearVelocity = new Vec2(0.0f,0.0f);
        bullet.angularVelocity = 0.0f;
        bullet.fixedRotation = false;
        bullet.active = true;
        bullet.bullet = true;
        bullet.allowSleep = true;
        bullet.gravityScale = 1.0f;
        bullet.linearDamping = 0.0f;
        bullet.angularDamping = 0.0f;
        bullet.type = BodyType.DYNAMIC;

        //define bullet shape of the body.
        bulletShape = new PolygonShape();
        bulletShape.setAsBox(1.5f, 1.5f);

        //define bullet fixture of the body.
        bulletFixture = new FixtureDef();
        bulletFixture.shape = bulletShape;
        bulletFixture.userData = null;
        bulletFixture.friction = friction;
        bulletFixture.restitution = restitution;
        bulletFixture.density = density;
        bulletFixture.isSensor = false;

        //create the bullet body and add fixture to it
        bulletBody = world.createBody(bullet);
        bulletBody.createFixture(bulletFixture);

    }

    public float bulletHeight(){
        Vec2 vec0 = bulletShape.getVertex(0);
        Vec2 vec2 = bulletShape.getVertex(2);
        return(vec2.y - vec0.y);
    }

    public float bulletWidth(){
        Vec2 vec0 = bulletShape.getVertex(0);
        Vec2 vec2 = bulletShape.getVertex(2);
        return(vec2.x - vec0.x);
    }

    public float bulletAng(){
        return bulletBody.getAngle();
    }

    public void destroyBullet(){
        world.destroyBody(bulletBody);
    }

    public Vec2 getBullet() {
        return bulletBody.getPosition();
    }

}