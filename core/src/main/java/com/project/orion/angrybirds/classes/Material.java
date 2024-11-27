package com.project.orion.angrybirds.classes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;

public abstract class Material {
    protected int durability;
    protected String shape;
    protected Body body;
    protected World world;
    protected Texture texture;
    protected float sizeX;
    protected float sizeY;
    private boolean markedForDestruction = false;
    private boolean hasTakenDamage = false;

    public Material(World world, String texturePath, float x, float y, float sX, float sY) {
        this.world = world;
        this.texture = new Texture(texturePath);
        this.sizeX = sX;
        this.sizeY = sY;
        createBody(x, y, sX, sY);
    }

    protected void createBody(float x, float y, float sX, float sY) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x, y);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(sX / 2, sY / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.1f;

        body.createFixture(fixtureDef);
        shape.dispose();
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture,
            body.getPosition().x - sizeX / 2, body.getPosition().y - sizeY / 2,
            sizeX / 2, sizeY / 2,
            sizeX, sizeY,
            1, 1,
            (float) Math.toDegrees(body.getAngle()),
            0, 0,
            texture.getWidth(), texture.getHeight(),
            false, false);
    }

    public void dispose() {
        texture.dispose();
        world.destroyBody(body);
    }

    public void reduceDurability(int damage) {
        durability -= damage;
        if (durability <= 0) {
            markedForDestruction = true;
        }
    }

    public int getDurability() {
        return durability;
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
