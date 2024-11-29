package com.project.orion.angrybirds.classes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;

public abstract class Pig {
    protected float size;
    protected int health;
    protected Texture texture;
    protected Body body;
    protected World world;
    private boolean markedForDestruction = false;
    private boolean hasTakenDamage = false;

    public Pig(World world, String texturePath, float x, float y, float size, int health) {
        this.world = world;
        this.texture = new Texture(texturePath);
        this.size = size;
        this.health = health;
        createBody(x, y);
    }

    private void createBody(float x, float y) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x, y);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);

        CircleShape shape = new CircleShape();
        shape.setRadius(size / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1;

        fixtureDef.friction = 1f;
        fixtureDef.restitution = 0.2f;

        body.createFixture(fixtureDef);
        shape.dispose();
    }



    public void reduceHealth(int damage) {
        health -= damage;
        if (health <= 0) {
            markedForDestruction = true;
        }
    }

    public boolean isDestroyed() {
        return health <= 0;
    }

    public void render(SpriteBatch batch) {
        if (body != null && !markedForDestruction) {
            batch.draw(texture,
                body.getPosition().x - size / 2, body.getPosition().y - size / 2,
                size / 2, size / 2,
                size, size,
                1, 1,
                (float) Math.toDegrees(body.getAngle()),
                0, 0,
                texture.getWidth(), texture.getHeight(),
                false, false);
        }
    }

    public void dispose() {
        texture.dispose();
        if (body != null){
            world.destroyBody(body);
            body = null;
        }
    }

    public float getSize() {
        return size;
    }

    public int getHealth() {
        return health;
    }
    public Body getBody() {
        return body;
    }

    public boolean isMarkedForDestruction() {
        return markedForDestruction;
    }

    public boolean hasTakenDamage() {
        return hasTakenDamage;
    }

    public void setHasTakenDamage(boolean hasTakenDamage) {
        this.hasTakenDamage = hasTakenDamage;
    }

}
