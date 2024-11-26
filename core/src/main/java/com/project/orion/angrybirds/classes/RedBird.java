package com.project.orion.angrybirds.classes;

import com.badlogic.gdx.physics.box2d.World;

public class RedBird extends Bird {
    public RedBird(World world, float x, float y) {
        super(world,"redBird.png", x, y);
        this.speed = 2;
        this.impact = 20;
    }
}
