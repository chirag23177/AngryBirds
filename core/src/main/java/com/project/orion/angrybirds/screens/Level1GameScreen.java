package com.project.orion.angrybirds.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.project.orion.angrybirds.GameLauncher;
import com.project.orion.angrybirds.classes.*;

import java.util.ArrayList;
import java.util.List;

public class Level1GameScreen implements Screen {
    private GameLauncher game;
    private Texture background;
    private Texture catapult;
    private Music gameMusic;
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private ShapeRenderer shapeRenderer;

    private RedBird redBird;
    private Ground ground;
    private Structure1 structure;

    private final Stage stage;

    // Slingshot mechanics
    private boolean isDragging = false;
    private Vector2 initialTouchPosition = new Vector2();
    private Vector2 currentTouchPosition = new Vector2();
    private Vector2 launchVelocity = new Vector2();

    // Slingshot constraints
    private static final float MAX_DRAG_DISTANCE = 200f;
    private static final float LAUNCH_POWER_MULTIPLIER = 15f;
    private static final Vector2 BIRD_POSITION = new Vector2(200f, 300);
    private static final Vector2 GRAB_REGION_CENTRE = new Vector2(200f, 300f);

    //Projectile equation
    private ProjectileEquation projectileEquation;

    // Collision
    private ContactListener contactListener;
    private List<Body> bodiesToDestroy = new ArrayList<>();

    public Level1GameScreen(GameLauncher game) {
        this.game = game;
        stage = new Stage(game.viewport, game.batch);
        shapeRenderer = new ShapeRenderer();
        projectileEquation = new ProjectileEquation();
        projectileEquation.setGravity(9.8f);
        world = new World(new Vector2(0, -9.8f), true);
        setupContactListner();
    }

    @Override
    public void show() {
        game.introMusic.pause();
        background = new Texture("game_background.png");
        catapult = new Texture("slingshot.png");
        debugRenderer = new Box2DDebugRenderer();

        ground = new Ground(world, 130);
        structure = new Structure1(world);

        // Creating bird at the specified position
        redBird = new RedBird(world, BIRD_POSITION.x, BIRD_POSITION.y);
        redBird.setStatic();

        stage.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Vector2 touchPos = game.viewport.unproject(new Vector2(x, y));
                touchPos.x = touchPos.x * game.viewport.getWorldWidth() / Gdx.graphics.getWidth();
                touchPos.y = (Gdx.graphics.getHeight() - touchPos.y) * game.viewport.getWorldHeight() / Gdx.graphics.getHeight();
                // Dont remove this
//                touchPos.x = touchPos.x - 32;
//                touchPos.y = touchPos.y - 362;
//                touchPos.x = touchPos.x;
//                touchPos.y = touchPos.y - 481;

                if (isNearBird(touchPos)) {
                    isDragging = true;
                    initialTouchPosition.set(touchPos);
                    currentTouchPosition.set(touchPos);
                    return true;
                }
                return false;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                if (isDragging) {
                    Vector2 touchPos = game.viewport.unproject(new Vector2(x, y));
                    touchPos.x = touchPos.x * game.viewport.getWorldWidth() / Gdx.graphics.getWidth();
                    touchPos.y = (Gdx.graphics.getHeight() - touchPos.y) * game.viewport.getWorldHeight() / Gdx.graphics.getHeight();
                    currentTouchPosition.set(touchPos);
                    // Dont remove this
//                    currentTouchPosition.x = currentTouchPosition.x - 32;
//                    currentTouchPosition.y = currentTouchPosition.y - 362;
//                    currentTouchPosition.x = currentTouchPosition.x;
//                    currentTouchPosition.y = currentTouchPosition.y - 481;

                    if (currentTouchPosition.dst(initialTouchPosition) > MAX_DRAG_DISTANCE) {
                        currentTouchPosition.sub(initialTouchPosition).nor().scl(MAX_DRAG_DISTANCE).add(initialTouchPosition);
                    }
                }
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (isDragging) {
                    launchVelocity.set(currentTouchPosition).sub(initialTouchPosition).scl(LAUNCH_POWER_MULTIPLIER);
                    launchVelocity.x = -launchVelocity.x;
                    launchVelocity.y = -launchVelocity.y;
                    if (launchVelocity.len() > MAX_DRAG_DISTANCE) {
                        launchVelocity.nor().scl(MAX_DRAG_DISTANCE);
                    }
                    redBird.setDynamic();
                    redBird.getBody().setLinearVelocity(launchVelocity.scl(100));
                    isDragging = false;
                }
            }
        });

        Gdx.input.setInputProcessor(stage);
    }

    private boolean isNearBird(Vector2 touchPos) {
        Vector2 birdPos = redBird.getBody().getPosition();
        System.out.println("birdPos" + birdPos + "touchPos" + touchPos);
        return touchPos.dst(birdPos) < 100f;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        world.step(1 / 60f, 6, 2);

        game.viewport.apply();
        game.batch.setProjectionMatrix(game.viewport.getCamera().combined);
        float worldWidth = game.viewport.getWorldWidth();
        float worldHeight = game.viewport.getWorldHeight();

        game.batch.begin();
        game.batch.draw(background, 0, 0, worldWidth, worldHeight);
        game.batch.draw(catapult, 150, 100, catapult.getWidth(), catapult.getHeight());
        redBird.render(game.batch);
        structure.render(game.batch);
        game.batch.end();

        if (isDragging) {
            renderTrajectory();
        }

        debugRenderer.render(world, game.viewport.getCamera().combined);

        stage.act(delta);
        stage.draw();

        structure.getMaterials().removeIf(material -> {
            if (material.isMarkedForDestruction()) {
                bodiesToDestroy.add(material.getBody());
                return true;
            }
            return false;
        });

        structure.getPigs().removeIf(pig -> {
            if (pig.isMarkedForDestruction()) {
                bodiesToDestroy.add(pig.getBody());
                return true;
            }
            return false;
        });

        for (Body body : bodiesToDestroy) {
            world.destroyBody(body);
        }
        bodiesToDestroy.clear();
    }

    private void renderTrajectory() {
        projectileEquation.setStartVelocity(launchVelocity);
        Vector2 birdPosition = new Vector2(200, 300);
        projectileEquation.setStartPoint(birdPosition);
        projectileEquation.setGravity(-9.8f);

        shapeRenderer.setProjectionMatrix(game.viewport.getCamera().combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(1, 0, 0, 1);

        float timeStep = 0.05f;
        float t = 0f;
        int numSteps = 150;

        Vector2 previousPoint = new Vector2(birdPosition);

        for (int i = 0; i < numSteps; i++) {
            float x = 200 + projectileEquation.getX(t);
            float y = 300 + projectileEquation.getY(t);

            if (i > 0) {
                shapeRenderer.line(previousPoint.x, previousPoint.y,1500, 400);
            }

            previousPoint.set(x, y);
            t += timeStep;

            if (y < ground.getHeight()) {
                break;
            }
        }

        shapeRenderer.end();
    }

    private void setupContactListner(){
        contactListener = new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Fixture fixtureA = contact.getFixtureA();
                Fixture fixtureB = contact.getFixtureB();

                // Check if the bird is involved in the collision
                if (isRedBirdCollision(fixtureA, fixtureB)) {
                    handleBirdCollision(fixtureA, fixtureB);
                }

                // If the material hits the ground, it takes damage
                if (isMaterialGroundCollision(fixtureA, fixtureB)) {
                    handleMaterialGroundCollision(fixtureA, fixtureB);
                }

                // If the pig hits the ground, it takes damage
                if (isPigGroundCollision(fixtureA, fixtureB)) {
                    handlePigGroundCollision(fixtureA, fixtureB);
                }

                //If structure hits the pig, it takes damage
                if (isStructurePigCollision(fixtureA, fixtureB)){
                    handleStructurePigCollision(fixtureA, fixtureB);
                }
            }

            @Override
            public void endContact(Contact contact) {}
            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {}

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {}
        };

        world.setContactListener(contactListener);
    }

    private boolean isPigGroundCollision(Fixture fixtureA, Fixture fixtureB) {
        return (isGround(fixtureA) && isPig(fixtureB)) ||
            (isGround(fixtureB) && isPig(fixtureA));
    }

    private boolean isStructurePigCollision(Fixture fixtureA, Fixture fixtureB) {
        return (isMaterial(fixtureA) && isPig(fixtureB)) ||
            (isMaterial(fixtureB) && isPig(fixtureA));
    }

    private boolean isMaterialGroundCollision(Fixture fixtureA, Fixture fixtureB) {
        return (isGround(fixtureA) && isMaterial(fixtureB)) ||
            (isGround(fixtureB) && isMaterial(fixtureA));
    }

    private boolean isGround(Fixture fixture) {
        return  fixture.getBody() == ground.getGroundBody();
    }

    private boolean isMaterial(Fixture fixture) {
        return structure.containsBody(fixture.getBody());
    }

    private void handleMaterialGroundCollision(Fixture fixtureA, Fixture fixtureB){
        Body materialBody = isMaterial(fixtureA) ? fixtureA.getBody() : fixtureB.getBody();
        for (Material material : structure.getMaterials()) {
            if (material.getBody() == materialBody && !material.hasTakenDamage()) {
                if (material.getBody().getPosition().y>ground.getHeight())
                    material.reduceDurability(10);
                material.setHasTakenDamage(true);
                break;
            }
        }
    }

    private boolean isRedBirdCollision(Fixture fixtureA, Fixture fixtureB) {
        return (fixtureA.getBody() == redBird.getBody() ||
            fixtureB.getBody() == redBird.getBody());
    }

    private void handleBirdCollision(Fixture fixtureA, Fixture fixtureB) {
        Body otherBody = fixtureA.getBody() == redBird.getBody() ?
            fixtureB.getBody() : fixtureA.getBody();
        if (isStructureElement(otherBody)) {
            onBirdHitStructure(otherBody);
        } else if (isPig(otherBody)) {
            onBirdHitPig(otherBody);

        }
    }

    private void handlePigGroundCollision(Fixture fixtureA, Fixture fixtureB) {
        Body pigBody = isPig(fixtureA) ? fixtureA.getBody() : fixtureB.getBody();
        for (Pig pig : structure.getPigs()) {
            if (pig.getBody() == pigBody && !pig.hasTakenDamage()) {
                if (pig.getBody().getPosition().y > ground.getHeight()) {
                    pig.reduceHealth(10);
                    pig.setHasTakenDamage(true);
                }
                break;
            }
        }
    }

    private void handleStructurePigCollision(Fixture fixtureA, Fixture fixtureB) {
        Body pigBody = isPig(fixtureA) ? fixtureA.getBody() : fixtureB.getBody();
        Body structureBody = isMaterial(fixtureA) ? fixtureA.getBody() : fixtureB.getBody();
        for (Pig pig : structure.getPigs()) {
            if (pig.getBody() == pigBody && !pig.hasTakenDamage()) {
                pig.reduceHealth(10);
                pig.setHasTakenDamage(true);
                break;
            }
        }
    }

    private boolean isStructureElement(Body body) {
        Body primaryBody = structure.getPrimaryBody();
        return (primaryBody != null && body == primaryBody) ||
            structure.containsBody(body);
    }

    private boolean isPig(Body body) {
        return structure.containsPig(body);
    }

    private boolean isPig(Fixture fixture) {
        return structure.containsPig(fixture.getBody());
    }

    private void onBirdHitStructure(Body body) {
        System.out.println("Bird hit the structure!");
        for (Material material : structure.getMaterials()) {
            if (material.getBody() == body) {
                material.reduceDurability(redBird.getImpact());
                break;
            }
        }
    }

    private void onBirdHitPig(Body body) {
        System.out.println("Bird hit the pig!");
        for (Pig pig : structure.getPigs()) {
            if (pig.getBody() == body) {
                pig.reduceHealth(redBird.getImpact());
                break;
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        game.viewport.update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        background.dispose();
        catapult.dispose();
        gameMusic.dispose();
        redBird.dispose();
        world.dispose();
        debugRenderer.dispose();
        shapeRenderer.dispose();
    }
}
