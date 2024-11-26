//package com.project.orion.angrybirds.classes;
//
//public class LargePig extends Pig {
//    public LargePig() {
//        this.size = 1;
//        this.health = 10;
//    }
//}

package com.project.orion.angrybirds.classes;

import com.badlogic.gdx.physics.box2d.World;

public class LargePig extends Pig {
    public LargePig(World world, float x, float y) {
        super(world, "minionPig.png", x, y, 90f, 30);
    }
}
