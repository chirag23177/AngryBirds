package com.project.orion.angrybirds.classes;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class Structure1 extends Structure {
    public Structure1(World world) {
        super(world);
        materials.add(new WoodPillar(world, 1600, 220, 60, 162));
        materials.add(new WoodPillar(world, 1300, 220, 60, 162));
        materials.add(new SteelPlatform(world, 1450, 315, 424, 48));
        materials.add(new GlassBox(world, 1460, 402, 150, 150));
        pigs.add(new MediumPig(world, 1460, 510));
    }

    public boolean containsBody(Body body) {
        return materials.stream()
            .anyMatch(material -> material.getBody() == body);
    }
}
