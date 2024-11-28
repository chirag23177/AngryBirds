package com.project.orion.angrybirds.classes;

import com.badlogic.gdx.physics.box2d.World;

public class MediumPig extends Pig {
    public MediumPig(World world, float x, float y) {
        super(world, "minionPig.png", x, y, 75f, 30);
    }
}
