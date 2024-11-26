//package com.project.orion.angrybirds.classes;
//
//public abstract class Material {
//    int durability;
//    String shape;
//
//    public void reduceDurability(int damage) {};
//    public boolean isDestroyed() {
//        return true;
//    };
//    public String getShape() {
//        return "angry birds";
//    };
//    public int getDurability() {
//        return 3;
//    };
//}


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

    public Material(World world, String texturePath, float x, float y, float sX, float sY) {
        this.world = world;
        this.texture = new Texture(texturePath);
        this.sizeX = sX;
        this.sizeY = sY;
        createBody(x, y, sX, sY);
    }

    protected abstract void createBody(float x, float y, float sX, float sY);

    public void render(SpriteBatch batch) {
        batch.draw(texture, body.getPosition().x - sizeX/2, body.getPosition().y - sizeY/2, sizeX, sizeY);
    }

    public void dispose() {
        texture.dispose();
        world.destroyBody(body);
    }

    public void reduceDurability(int damage) {
        durability -= damage;
        if (durability <= 0) {
            dispose();
        }
    }
    public int getDurability() {
        return durability;
    }
    public Body getBody() {
        return body;
    }
}
