//package com.project.orion.angrybirds.classes;
//
//public abstract class Pig {
//    float size;
//    int health;
//
//    public void reduceHealth(int damage) {};
//    public boolean isDestroyed() {
//        return true;
//    };
//    public float getSize() {
//        return 1;
//    };
//    public int getHealth() {
//        return 0;
//    };
//}

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
        bodyDef.type = BodyDef.BodyType.DynamicBody; // Change to DynamicBody
        body = world.createBody(bodyDef);

        CircleShape shape = new CircleShape();
        shape.setRadius(size / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.6f;

        body.createFixture(fixtureDef);
        shape.dispose();
    }

    public void reduceHealth(int damage) {
        health -= damage;
        if (health <= 0) {
            dispose();
        }
    }

    public boolean isDestroyed() {
        return health <= 0;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, body.getPosition().x - size / 2, body.getPosition().y - size / 2, size, size);
    }

    public void dispose() {
        texture.dispose();
        world.destroyBody(body);
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
}
