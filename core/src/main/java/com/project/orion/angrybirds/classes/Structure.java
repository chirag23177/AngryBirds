package com.project.orion.angrybirds.classes;

import java.util.ArrayList;

public abstract class Structure {
    ArrayList<Material> materials;
    float positionX;
    float positionY;

    public void addMaterial(Material material, float x, float y) {};
    public void collapse() {};
    public boolean checkStability() {
        return true;
    };
}
