package com.project.orion.angrybirds.classes;

//import com.badlogic.gdx.math.Vector2;
//
//public class ProjectileEquation {
//    public float gravity;
//    public Vector2 startVelocity = new Vector2();
//    public Vector2 startPoint = new Vector2();
//
//    public float getGravity() {
//        return gravity;
//    }
//
//    public void setGravity(float gravity) {
//        this.gravity = gravity;
//    }
//
//    public Vector2 getStartVelocity() {
//        return startVelocity;
//    }
//
//    public void setStartVelocity(Vector2 startVelocity) {
//        this.startVelocity = startVelocity;
//    }
//
//    public Vector2 getStartPoint() {
//        return startPoint;
//    }
//
//    public void setStartPoint(Vector2 startPoint) {
//        this.startPoint = startPoint;
//    }
//
//    public float getX(float t) {
//        return startPoint.x + startVelocity.x * t;
//    }
//
//    public float getY(float t) {
//        return startPoint.y + startVelocity.y * t + 0.5f * gravity * t * t;
//    }
//}


import com.badlogic.gdx.math.Vector2;

public class ProjectileEquation {
    public float gravity;
    public Vector2 startVelocity = new Vector2();
    public Vector2 startPoint = new Vector2();

    public float getX(float t) {
        return startVelocity.x * t;
    }

    public float getY(float t) {
        return startVelocity.y * t + 0.5f * gravity * t * t;
    }

    public void setStartVelocity(Vector2 startVelocity) {
        this.startVelocity = startVelocity;
    }

    public void setStartPoint(Vector2 startPoint) {
        this.startPoint = startPoint;
    }

    public void setGravity(float gravity) {
        this.gravity = gravity;
    }
}
