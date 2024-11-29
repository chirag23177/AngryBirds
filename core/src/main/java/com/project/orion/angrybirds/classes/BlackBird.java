package com.project.orion.angrybirds.classes;

import com.badlogic.gdx.physics.box2d.World;

public class BlackBird extends Bird{
    public BlackBird(World world,float x, float y) {
        super(world, "blackBird.png", x, y, 70);
        this.speed = 1;
        this.impact = 15;
    }
}
