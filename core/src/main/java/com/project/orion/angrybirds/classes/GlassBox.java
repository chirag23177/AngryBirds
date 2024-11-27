//package com.project.orion.angrybirds.classes;
//
//public class Glass extends Material{
//    public Glass() {
//        this.durability = 10;
//        this.shape = "Box";
//    }
//    public void shatter() {};
//}

package com.project.orion.angrybirds.classes;

import com.badlogic.gdx.physics.box2d.*;

public class GlassBox extends Material {

    public GlassBox(World world, float x, float y, float sX, float sY) {
        super(world, "bambooBox.png", x, y, sX, sY);
        this.durability = 10;
        this.shape = "Box";
    }

    @Override
    protected void createBody(float x, float y, float sX, float sY) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x, y);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);

        PolygonShape boxShape = new PolygonShape();
        boxShape.setAsBox(sX/2-sX/20, sY/2-sY/20);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = boxShape;
        fixtureDef.density = 1f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.3f;

        body.createFixture(fixtureDef);
        boxShape.dispose();
    }

    public void shatter() {
        // Logic to handle shattering of the glass box
    }
}
