package com.project.orion.angrybirds.classes;

public abstract class Material {
    int durability;
    String shape;

    public void reduceDurability(int damage) {};
    public boolean isDestroyed() {
        return true;
    };
    public String getShape() {
        return "angry birds";
    };
    public int getDurability() {
        return 3;
    };
}
