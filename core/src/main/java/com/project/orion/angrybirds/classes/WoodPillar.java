//package com.project.orion.angrybirds.classes;
//
//public class Wood extends Material{
//    public Wood() {
//        this.durability = 20;
//        this.shape = "Pillar";
//    }
//    public void brea() {};
//}

package com.project.orion.angrybirds.classes;

import com.badlogic.gdx.physics.box2d.*;

public class WoodPillar extends Material {

    public WoodPillar(World world, float x, float y, float sX, float sY) {
        super(world, "verticalBamboo.png", x, y, sX, sY);
        this.durability = 20;
        this.shape = "Pillar";
    }

    @Override
    protected void createBody(float x, float y, float sX, float sY) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x, y);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        body = world.createBody(bodyDef);

        PolygonShape pillarShape = new PolygonShape();
        pillarShape.setAsBox(sX/2-sX/7, sY/2-sY/40);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = pillarShape;
        fixtureDef.density = 1.5f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.2f;

        body.createFixture(fixtureDef);
        pillarShape.dispose();
    }

    public void breakPillar() {
        // Logic to handle breaking of the wood pillar
    }
}
