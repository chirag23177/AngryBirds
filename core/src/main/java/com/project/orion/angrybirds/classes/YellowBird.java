package com.project.orion.angrybirds.classes;

import com.badlogic.gdx.physics.box2d.World;

public class YellowBird extends Bird{
    public YellowBird(World world,float x, float y) {
        super(world,"yellowBird.png", x, y, 70);
        this.speed = 3;
        this.impact = 5;
    }
}
