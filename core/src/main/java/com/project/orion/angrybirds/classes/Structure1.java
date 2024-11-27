package com.project.orion.angrybirds.classes;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class Structure1 extends Structure {
    public Structure1(World world) {
        super(world);
        // Initialize materials and pigs for this structure
        materials.add(new WoodPillar(world, 1600, 220, 60, 162));
        materials.add(new WoodPillar(world, 1300, 220, 60, 162));
        materials.add(new SteelPlatform(world, 1450, 315, 424, 48));
        materials.add(new GlassBox(world, 1460, 402, 150, 150));
        pigs.add(new MediumPig(world, 1460, 490));
    }
    public Body getPrimaryBody() {
        // Return the main body of the structure if applicable
        if (!materials.isEmpty()) {
            return materials.get(0).getBody();
        }
        return null; // Example
    }

    public boolean containsBody(Body body) {
        // Check if the given body is part of this structure
        return materials.stream()
            .anyMatch(material -> material.getBody() == body);
    }
}
