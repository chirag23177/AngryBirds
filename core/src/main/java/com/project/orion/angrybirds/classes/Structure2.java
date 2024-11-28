package com.project.orion.angrybirds.classes;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class Structure3 extends Structure {
    public Structure3(World world) {
        super(world);

        materials.add(new WoodPillar(world, 1350, 185, 30, 90));
        materials.add(new WoodPillar(world, 1250, 185, 30, 90));
        materials.add(new SteelPlatform(world, 1300, 240, 190, 24));
        materials.add(new WoodPillar(world, 1360, 300, 35, 110));
        materials.add(new WoodPillar(world, 1240, 300, 35, 110));
        materials.add(new SteelPlatform(world, 1300, 360, 230, 22));
        materials.add(new WoodPillar(world, 1350, 410, 30, 90));
        materials.add(new WoodPillar(world, 1250, 410, 30, 90));
        materials.add(new SteelPlatform(world, 1300, 465, 350, 28));
        materials.add(new GlassBox(world, 1360, 490, 40, 40));
        materials.add(new GlassBox(world, 1400, 490, 40, 40));
        materials.add(new GlassBox(world, 1440, 490, 40, 40));
        materials.add(new GlassBox(world, 1380, 530, 40, 40));
        materials.add(new GlassBox(world, 1420, 530, 40, 40));
        materials.add(new GlassBox(world, 1400, 570, 40, 40));
        materials.add(new GlassBox(world, 1400, 610, 40, 40));
        materials.add(new WoodPillar(world, 1155, 550, 45, 152));
        materials.add(new SteelPlatform(world, 1300, 635, 350, 30));
        pigs.add(new LargePig(world, 1270, 515));
        pigs.add(new MediumPig(world, 1300, 280));
        pigs.add(new SmallPig(world, 1400, 665));
//        materials.add(new GlassBox(world, 1430, 660, 5, 5));

//        materials.add(new WoodPillar(world, 1500, 320, 40, 110));
//        materials.add(new WoodPillar(world, 1400, 320, 40, 110));
//        materials.add(new GlassBox(world, 1450, 450, 150, 150));
//        pigs.add(new MediumPig(world, 1450, 550));
    }
    public Body getPrimaryBody() {
        if (!materials.isEmpty()) {
            return materials.get(0).getBody();
        }
        return null;
    }

    public boolean containsBody(Body body) {
        return materials.stream()
            .anyMatch(material -> material.getBody() == body);
    }
}
