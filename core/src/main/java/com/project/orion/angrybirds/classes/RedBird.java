package com.project.orion.angrybirds.classes;

import com.badlogic.gdx.physics.box2d.World;

public class RedBird extends Bird {
    int health = 20;
    public RedBird(World world, float x, float y) {
        super(world,"redBird.png", x, y, 70);
        this.speed = 2;
        this.impact = 10;
    }
    public void applyDamage(){
        health -= 10;
        if (health <= 0){
            distroy();
        }
    }

    private void distroy(){
        world.destroyBody(body);
    }
}
