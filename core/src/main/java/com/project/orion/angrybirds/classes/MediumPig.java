//package com.project.orion.angrybirds.classes;
//
//public class MediumPig extends Pig {
//    public MediumPig() {
//        this.size = 2;
//        this.health = 20;
//    }
//}

package com.project.orion.angrybirds.classes;

import com.badlogic.gdx.physics.box2d.World;

public class MediumPig extends Pig {
    public MediumPig(World world, float x, float y) {
        super(world, "minionPig.png", x, y, 75f, 20);
    }
}
