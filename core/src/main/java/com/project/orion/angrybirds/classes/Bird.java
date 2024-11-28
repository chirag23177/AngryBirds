package com.project.orion.angrybirds.classes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public abstract class Bird {
    int speed;
    int impact;
    protected Texture texture;
    protected Body body;
    protected World world;
    protected float size;

    public Bird(World world, String texturePath, float x, float y, float size) {
        this.world = world;
        this.texture = new Texture(texturePath);
        this.size = size;
        createBody(x, y, size);
    }

    private void createBody(float x, float y, float size) {
        BodyDef bodydef = new BodyDef();
        bodydef.position.set(x, y);
        bodydef.type = BodyDef.BodyType.KinematicBody;
        body = world.createBody(bodydef);

        CircleShape shape = new CircleShape();
        shape.setRadius(size/2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 10f;
        fixtureDef.friction = .99f;
        fixtureDef.restitution = 0.1f;

        body.createFixture(fixtureDef);
        shape.dispose();
    }

    public void setDynamic() {
        body.setType(BodyDef.BodyType.DynamicBody);
    }

    public void setStatic() {
        body.setType(BodyDef.BodyType.StaticBody);
    }

    public void fly() {}

    public void hitTarget() {}

    public void render(SpriteBatch batch) {
        if (body != null) {
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
        if (body != null) {
            world.destroyBody(body);
            body = null;
        }
    }

    public int getSpeed() {
        return speed;
    }

    public int getImpact() {
        return impact;
    }

    public Body getBody() {
        return body;
    }

    public Vector2 getPosition() {
        return body.getPosition();
    }

    public void setLinearVelocity(Vector2 velocity) {
        body.setLinearVelocity(velocity);
    }

    public Texture getTexture() {
        return texture;
    }
}
