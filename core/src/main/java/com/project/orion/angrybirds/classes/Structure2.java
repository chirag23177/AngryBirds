package com.project.orion.angrybirds.classes;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class Structure2 extends Structure {
    public Structure2(World world) {
        super(world);
        // Initialize materials and pigs for this structure
        materials.add(new WoodPillar(world, 1200, 185, 40, 110));
        materials.add(new WoodPillar(world, 1100, 185, 40, 110));
        materials.add(new SteelPlatform(world, 1150, 258, 180, 36));
        materials.add(new WoodPillar(world, 1200, 320, 40, 110));
        materials.add(new WoodPillar(world, 1100, 320, 40, 110));
        materials.add(new GlassBox(world, 1150, 450, 150, 150));
        pigs.add(new MediumPig(world, 1150, 550));

        pigs.add(new LargePig(world, 1300, 150));

        materials.add(new WoodPillar(world, 1500, 185, 40, 110));
        materials.add(new WoodPillar(world, 1400, 185, 40, 110));
        materials.add(new SteelPlatform(world, 1450, 258, 180, 36));
        materials.add(new WoodPillar(world, 1500, 320, 40, 110));
        materials.add(new WoodPillar(world, 1400, 320, 40, 110));
        materials.add(new GlassBox(world, 1450, 450, 150, 150));
        pigs.add(new MediumPig(world, 1450, 550));
    }
    public Body getPrimaryBody() {
        // Return the main body of the structure if applicable
        return materials.get(0).getBody(); // Example
    }

    public boolean containsBody(Body body) {
        // Check if the given body is part of this structure
        return materials.stream()
            .anyMatch(material -> material.getBody() == body);
    }
}
