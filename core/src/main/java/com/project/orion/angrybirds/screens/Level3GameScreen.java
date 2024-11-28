package com.project.orion.angrybirds.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

public class Level3GameScreen implements Screen {
    private GameLauncher game;
    private Texture background;
    private Texture catapult;
    private Music gameMusic;
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private ShapeRenderer shapeRenderer;
    private List<Bird> birds;
    private Bird currentBird;
    private Ground ground;
    private Structure3 structure;

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
    private boolean birdCollided = false;
    private float collisionTime = 0;

    public Level3GameScreen(GameLauncher game) {
        this.game = game;
        stage = new Stage(game.viewport, game.batch);
        shapeRenderer = new ShapeRenderer();
        projectileEquation = new ProjectileEquation();
        projectileEquation.setGravity(9.8f);
        world = new World(new Vector2(0, -9.8f), true);
        setupContactListner();
        createBirds();
        setCurrentBird();
    }

    private void createBirds() {
        birds = new ArrayList<>();
        birds.add(new RedBird(world, BIRD_POSITION.x, BIRD_POSITION.y));
        birds.add(new BlackBird(world, 100, 150));
        birds.add(new YellowBird(world, 200, 150));
    }

    private void setCurrentBird() {
        if (!birds.isEmpty()) {
            currentBird = birds.remove(0);
            currentBird.getBody().setTransform(BIRD_POSITION, 0);
            currentBird.setStatic();
        }
    }

    @Override
    public void show() {
        game.introMusic.pause();
        background = new Texture("game_background.png");
        catapult = new Texture("slingshot.png");
        debugRenderer = new Box2DDebugRenderer();

        // Create ground and structure
        ground = new Ground(world, 130);
        structure = new Structure3(world);

        // Touch input handling
        stage.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Vector2 touchPos = game.viewport.unproject(new Vector2(x, y));
                touchPos.x = touchPos.x * game.viewport.getWorldWidth() / Gdx.graphics.getWidth();
                touchPos.y = (Gdx.graphics.getHeight() - touchPos.y) * game.viewport.getWorldHeight() / Gdx.graphics.getHeight();

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
                    currentBird.setDynamic();
                    currentBird.getBody().setLinearVelocity(launchVelocity.scl(100));
                    isDragging = false;
                }
            }
        });

        Gdx.input.setInputProcessor(stage);
    }

    private boolean isNearBird(Vector2 touchPos) {
        Vector2 birdPos = currentBird.getBody().getPosition();
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
        if (currentBird != null) {
            currentBird.render(game.batch);
        }
        structure.render(game.batch);
        game.batch.end();

        if (isDragging) {
            renderTrajectory();
        }

        debugRenderer.render(world, game.viewport.getCamera().combined);

        stage.act(delta);
        stage.draw();

        if (birdCollided) {
            collisionTime += delta;
            if (collisionTime >= 4) {
                removeCurrentBird();
                setCurrentBird();
                birdCollided = false;
                collisionTime = 0;
            }
        }

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
        Vector2 birdPosition = currentBird.getBody().getPosition();
        projectileEquation.setStartPoint(birdPosition);
        projectileEquation.setGravity(-9.8f);

        shapeRenderer.setProjectionMatrix(game.viewport.getCamera().combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(1, 1, 0, 0.7f);

        float timeStep = 0.05f;
        float t = 0f;
        int numSteps = 150;

        Vector2 previousPoint = new Vector2(birdPosition);

        for (int i = 0; i < numSteps; i++) {
            float x = birdPosition.x + projectileEquation.getX(t);
            float y = birdPosition.y + projectileEquation.getY(t);

            if (i > 0) {
                shapeRenderer.line(previousPoint.x, previousPoint.y, x, y);
            }

            previousPoint.set(x, y);
            t += timeStep;

            if (y < ground.getHeight()) {
                break;
            }
        }

        shapeRenderer.end();
    }

    private void setupContactListner() {
        contactListener = new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Fixture fixtureA = contact.getFixtureA();
                Fixture fixtureB = contact.getFixtureB();

                if (isBirdCollision(fixtureA, fixtureB)) {
                    handleBirdCollision(fixtureA, fixtureB);
                }

                if (isMaterialGroundCollision(fixtureA, fixtureB)) {
                    handleMaterialGroundCollision(fixtureA, fixtureB);
                }

                if (isPigGroundCollision(fixtureA, fixtureB)) {
                    handlePigGroundCollision(fixtureA, fixtureB);
                }

                if (isStructurePigCollision(fixtureA, fixtureB)) {
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

    private void removeCurrentBird() {
        if (currentBird != null && currentBird.getBody() != null) {
            bodiesToDestroy.add(currentBird.getBody());
            currentBird.dispose();
            currentBird = null;
        }
    }

    private boolean isPigGroundCollision(Fixture fixtureA, Fixture fixtureB) {
        return (isGround(fixtureA) && isPig(fixtureB)) || (isGround(fixtureB) && isPig(fixtureA));
    }

    private boolean isStructurePigCollision(Fixture fixtureA, Fixture fixtureB) {
        return (isMaterial(fixtureA) && isPig(fixtureB)) || (isMaterial(fixtureB) && isPig(fixtureA));
    }

    private boolean isMaterialGroundCollision(Fixture fixtureA, Fixture fixtureB) {
        return (isGround(fixtureA) && isMaterial(fixtureB)) || (isGround(fixtureB) && isMaterial(fixtureA));
    }

    private boolean isGround(Fixture fixture) {
        return fixture.getBody() == ground.getGroundBody();
    }

    private boolean isMaterial(Fixture fixture) {
        return structure.containsBody(fixture.getBody());
    }

    private void handleMaterialGroundCollision(Fixture fixtureA, Fixture fixtureB) {
        Body materialBody = isMaterial(fixtureA) ? fixtureA.getBody() : fixtureB.getBody();
        for (Material material : structure.getMaterials()) {
            if (material.getBody() == materialBody && !material.hasTakenDamage()) {
                if (material.getBody().getPosition().y > ground.getHeight()) {
                    material.reduceDurability(10);
                    material.setHasTakenDamage(true);
                    break;
                }
            }
        }
    }

    private boolean isBirdCollision(Fixture fixtureA, Fixture fixtureB) {
        if(currentBird != null) {
            return (fixtureA.getBody() == currentBird.getBody() || fixtureB.getBody() == currentBird.getBody());
        }
        return false;
    }

    private void handleBirdCollision(Fixture fixtureA, Fixture fixtureB){
        Body otherBody = fixtureA.getBody() == currentBird.getBody() ? fixtureB.getBody() : fixtureA.getBody();
        if (isStructureElement(otherBody)){
            onBirdHitStructure(otherBody);
        } else if (isPig(otherBody)) {
            onBirdHitPig(otherBody);
        }
    }

    private boolean isPig(Body body) {
        return structure.containsPig(body);
    }

    private boolean isPig(Fixture fixture) {
        return structure.containsPig(fixture.getBody());
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

    private boolean isStructureElement(Body body){
        Body primaryBody = structure.getPrimaryBody();
        return (primaryBody != null && body == primaryBody) ||
            structure.containsBody(body);
    }

    private void onBirdHitStructure(Body body){
        System.out.println("Bird hit structure");
        for (Material material : structure.getMaterials()){
            if (material.getBody() == body){
                material.reduceDurability(currentBird.getImpact());
                currentBird.hitTarget();
                birdCollided = true;
                break;
            }
        }
    }

    private void onBirdHitPig(Body body) {
        System.out.println("Bird hit pig");
        for (Pig pig : structure.getPigs()) {
            if (pig.getBody() == body) {
                pig.reduceHealth(currentBird.getImpact());
                currentBird.hitTarget();
                birdCollided = true;
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
        for (Bird bird : birds) {
            bird.dispose();
        }
        world.dispose();
        debugRenderer.dispose();
        shapeRenderer.dispose();
    }
}
