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
    private Structure3 structure1;

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

        // Create ground and structure
        ground = new Ground(world, 130);
        structure1 = new Structure3(world);

        // Create bird at the specified position
        redBird = new RedBird(world, BIRD_POSITION.x, BIRD_POSITION.y);
        redBird.setStatic(); // Keep bird stationary until launched

        // Touch input handling
        stage.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//                Vector2 touchPos = new Vector2(x, y);
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
//                    Vector2 touchPos = new Vector2(x, y);
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

//                    projectileEquation.setStartVelocity(launchVelocity);
//                    projectileEquation.setStartPoint(redBird.getBody().getPosition());
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
        structure1.render(game.batch);
        game.batch.end();

        if (isDragging) {
            renderTrajectory();
        }

        debugRenderer.render(world, game.viewport.getCamera().combined);

        stage.act(delta);
        stage.draw();

        structure1.getMaterials().removeIf(material -> {
            if (material.isMarkedForDestruction()) {
                bodiesToDestroy.add(material.getBody());
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
        // Ensure projectile equation is set up correctly
        projectileEquation.setStartVelocity(launchVelocity);
//        Vector2 birdPosition = redBird.getBody().getPosition();
        Vector2 birdPosition = new Vector2(200, 300);
        projectileEquation.setStartPoint(birdPosition);
        projectileEquation.setGravity(-9.8f);

        shapeRenderer.setProjectionMatrix(game.viewport.getCamera().combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(1, 1, 0, 0.7f); // Semi-transparent yellow

        float timeStep = 0.05f;  // Smaller time step for smoother curve
        float t = 0f;
        int numSteps = 150;  // More steps for a longer, smoother trajectory

        Vector2 previousPoint = new Vector2(birdPosition);

        for (int i = 0; i < numSteps; i++) {
            // Calculate trajectory point
            float x = 200 + projectileEquation.getX(t);
            float y = 300 + projectileEquation.getY(t);

            // Draw line segments instead of circles for smoother trajectory
            if (i > 0) {
                shapeRenderer.line(previousPoint.x, previousPoint.y,1500, 400);
            }

            previousPoint.set(x, y);
            t += timeStep;

            // Optional: Stop drawing if trajectory goes below ground level
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
            }

            @Override
            public void endContact(Contact contact) {
                // Not needed for this implementation
            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {
                // Not needed for this implementation
            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {
                // Can be used to check collision intensity if needed
            }
        };

        // Add the contact listener to the world
        world.setContactListener(contactListener);
    }

    private boolean isRedBirdCollision(Fixture fixtureA, Fixture fixtureB) {
        // Check if either fixture is the red bird
        return (fixtureA.getBody() == redBird.getBody() ||
            fixtureB.getBody() == redBird.getBody());
    }

    private void handleBirdCollision(Fixture fixtureA, Fixture fixtureB) {
        Body otherBody = fixtureA.getBody() == redBird.getBody() ?
            fixtureB.getBody() : fixtureA.getBody();

        // You might want to add a user data to your structure elements to identify them
        // For example, in your Structure or Material classes, you could do:
        // body.setUserData("structure");

        // Check if the collision is with a structure element
        if (isStructureElement(otherBody)) {
            // Trigger any specific bird collision behavior
            onBirdHitStructure(otherBody);
        }
    }

    private boolean isStructureElement(Body body) {
        // This method would check if the body is part of the structure
        // You might need to add user data or implement a more specific check
        return body == structure1.getPrimaryBody() ||
            structure1.containsBody(body);
    }

    private void onBirdHitStructure(Body body) {
        // Add any special effects or scoring logic here
        System.out.println("Bird hit the structure!");
        for (Material material : structure1.getMaterials()) {
            if (material.getBody() == body) {
                material.reduceDurability(redBird.getImpact());
                break;
            }
        }

        // Optional: Apply additional impulse or damage
//        redBird.applyDamage(); // Implement this method in RedBird class
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
