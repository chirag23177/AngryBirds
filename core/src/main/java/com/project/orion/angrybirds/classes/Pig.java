package com.project.orion.angrybirds.classes;

public abstract class Pig {
    float size;
    int health;

    public void reduceHealth(int damage) {};
    public boolean isDestroyed() {
        return true;
    };
    public float getSize() {
        return 1;
    };
    public int getHealth() {
        return 0;
    };
}
