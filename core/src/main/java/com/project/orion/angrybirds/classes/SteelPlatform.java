//package com.project.orion.angrybirds.classes;
//
//public class Steel extends Material{
//    public Steel() {
//        this.durability = 30;
//        this.shape = "Rectangular platform";
//    }
//    public void crush() {};
//}

package com.project.orion.angrybirds.classes;

import com.badlogic.gdx.physics.box2d.*;

public class SteelPlatform extends Material {

    public SteelPlatform(World world, float x, float y, float sX, float sY) {
        super(world, "horizontalBamboo.png", x, y, sX, sY);
        this.durability = 30;
        this.shape = "Rectangular platform";
    }

    @Override
    protected void createBody(float x, float y, float sX, float sY) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x, y);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        body = world.createBody(bodyDef);

        PolygonShape platformShape = new PolygonShape();
        platformShape.setAsBox(sX/2-sX/60, sY/2-sY/10);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = platformShape;
        fixtureDef.density = 2f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.1f;

        body.createFixture(fixtureDef);
        platformShape.dispose();
    }

    public void crush() {
        // Logic to handle crushing of the steel platform
    }
}
