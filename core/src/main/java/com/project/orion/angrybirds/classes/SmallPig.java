//package com.project.orion.angrybirds.classes;
//
//public class SmallPig extends Pig {
//    public SmallPig() {
//        this.size = 1;
//        this.health = 10;
//    }
//}

package com.project.orion.angrybirds.classes;

import com.badlogic.gdx.physics.box2d.World;

public class SmallPig extends Pig {
    public SmallPig(World world, float x, float y) {
        super(world, "minionPig.png", x, y, 60f, 10);
    }
}
