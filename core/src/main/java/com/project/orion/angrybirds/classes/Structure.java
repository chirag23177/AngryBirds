package com.project.orion.angrybirds.classes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;
import java.util.List;

public abstract class Structure {
    protected ArrayList<Material> materials;
    protected ArrayList<Pig> pigs;
    protected boolean allPigsDestroyed;

    public Structure(World world) {
        materials = new ArrayList<>();
        pigs = new ArrayList<>();
        allPigsDestroyed = false;
    }

    public void render(SpriteBatch batch) {
        for (Material material : materials) {
            material.render(batch);
        }
        for (Pig pig : pigs) {
            pig.render(batch);
        }
    }

    public boolean areAllPigsDestroyed() {
        return pigs.isEmpty();
    }

    public void dispose() {
        for (Material material : materials) {
            material.dispose();
        }
        for (Pig pig : pigs) {
            pig.dispose();
        }
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public List<Pig> getPigs() {
        return pigs;
    }

    public boolean containsBody(Body body) {
        return pigs.stream().anyMatch(pig -> pig.getBody() == body);
    }

    public boolean containsPig(Body body) {
        return pigs.stream().anyMatch(pig -> pig.getBody() == body);

    }

    public Body getPrimaryBody() {
        if (!materials.isEmpty()) {
            return materials.get(0).getBody();
        }
        return null;
    }

}
