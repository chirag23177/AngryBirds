package com.project.orion.angrybirds.classes;

import com.badlogic.gdx.physics.box2d.*;

public class Ground {
    private Body groundBody;
    private Body leftWallBody;
    private Body rightWallBody;
    private World world;

    public Ground(World world, float groundY) {
        this.world = world;
        createGround(groundY);
        createWalls();
    }

    private void createGround(float groundY) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(0, groundY);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        groundBody = world.createBody(bodyDef);

        PolygonShape groundShape = new PolygonShape();
        groundShape.setAsBox(1920, 10);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = groundShape;
        fixtureDef.friction = 0.5f;

        groundBody.createFixture(fixtureDef);
        groundShape.dispose();
    }

    private void createWalls() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;

        PolygonShape wallShape = new PolygonShape();
        wallShape.setAsBox(10, 1080);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = wallShape;
        fixtureDef.friction = .99f;

        // Left wall
        bodyDef.position.set(0, 0);
        leftWallBody = world.createBody(bodyDef);
        leftWallBody.createFixture(fixtureDef);

        // Right wall
        bodyDef.position.set(1920, 0);
        rightWallBody = world.createBody(bodyDef);
        rightWallBody.createFixture(fixtureDef);

        wallShape.dispose();
    }

    public void dispose() {
    }

    public float getHeight() {
        return 130;
    }

    public Body getGroundBody() {
        return groundBody;
    }
}
