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
    private float size;
//    protected float x;
//    protected float y;

    public Bird(World world, String texturePath, float x, float y, float size) {
        this.world = world;
        this.texture = new Texture(texturePath);
        createBody(x, y);
        this.size = size;
    }

    private void createBody(float x, float y) {
        // Create body
        BodyDef bodydef = new BodyDef();
        bodydef.position.set(x, y);
        bodydef.type = BodyDef.BodyType.StaticBody;
//        bodydef.type = BodyDef.BodyType.DynamicBody;
//        bodydef.linearDamping = 0.2f; // Optional: reduces excessive sliding/movement
//        bodydef.angularDamping = 0.2f; // Optional: reduces rotation damping
        body = world.createBody(bodydef);

        CircleShape shape = new CircleShape();
        shape.setRadius(10.0f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.6f;

        body.createFixture(fixtureDef);
        shape.dispose();
    }

    public void setDynamic() {
        body.setType(BodyDef.BodyType.DynamicBody);
    }

    public void setStatic() {
        body.setType(BodyDef.BodyType.StaticBody);
    }
//
//    public void applyVelocity(Vector2 velocity) {
//        body.setLinearVelocity(velocity);
//    }
//
//    public void setPosition(Vector2 position) {
//        body.setTransform(position, body.getAngle());
//    }





    public void fly() {};

    public void hitTarget() {};

    public void render(SpriteBatch batch) {
//        float birdSize = 100.0f; // Increase the size of the birds
        batch.draw(texture, body.getPosition().x - size / 2, body.getPosition().y - size / 2, size, size);
    }

    public void dispose() {
        texture.dispose();
    }

    public int getSpeed() {
        return speed;
    };

    public int getImpact() {
        return impact;
    };

    public Body getBody() {
        return body;
    }

    public Texture getTexture() {
        return texture;
    }
}
