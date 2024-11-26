package com.project.orion.angrybirds.classes;

import java.util.ArrayList;

public class Level {
    public int levelNumber;
    public ArrayList<Pig> pigs;
    public ArrayList<Structure> structures;
    public int numBirds;

    public void loadLevel(int levelNumber) {};
    public void resetLevel() {};
    public void getRemainingPigs() {};
}
