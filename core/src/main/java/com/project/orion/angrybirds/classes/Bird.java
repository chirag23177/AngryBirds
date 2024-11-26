package com.project.orion.angrybirds.classes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;

public abstract class Bird {
    int speed;
    int impact;
    protected Texture texture;
    protected Body body;
    protected World world;
//    protected float x;
//    protected float y;

    public Bird(World world, String texturePath, float x, float y) {
        this.world = world;
        this.texture = new Texture(texturePath);
        createBody(x, y);
    }

    private void createBody(float x, float y) {
        // Create body
        BodyDef bodydef = new BodyDef();
        bodydef.position.set(x, y);
        bodydef.type = BodyDef.BodyType.StaticBody;
        body = world.createBody(bodydef);

        CircleShape shape = new CircleShape();
        shape.setRadius(0.5f);

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



    public void fly() {};

    public void hitTarget() {};

    public void render(SpriteBatch batch) {
        batch.draw(texture, body.getPosition().x - 0.5f, body.getPosition().y - 0.5f, 1, 1);
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
}
