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
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.project.orion.angrybirds.GameLauncher;
import com.project.orion.angrybirds.classes.*;

import java.util.ArrayList;
import java.util.List;

public class Level2GameScreen implements Screen {
    private GameLauncher game;
    private Texture background;
    private Texture catapult;
    private Music gameMusic;
    private World world;
//    private Box2DDebugRenderer debugRenderer;
    private ShapeRenderer shapeRenderer;

    private List<Bird> birds;
    private Bird currentBird;
    private Ground ground;
    private Structure2 structure;

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

    // Collision
    private ContactListener contactListener;
    private List<Body> destroyBody = new ArrayList<>();

    private WinPopupScreen winPopupScreen;
    private LosePopupScreen losePopupScreen;
    private PausePopupScreen pausePopupScreen;
    private boolean gameEnded;
    private float timer;
    private Texture pauseTexture;
    private Button pauseButton;

    private boolean birdCollided = false;
    private float collisionTime = 0;

    private boolean birdLaunched = false;

    public Level2GameScreen(GameLauncher game) {
        this.game = game;
        stage = new Stage(game.viewport, game.batch);
        shapeRenderer = new ShapeRenderer();
        world = new World(new Vector2(0, -9.8f), true);
        setupContactListner();
        createBirds();
        setCurrentBird();
    }

    private void createBirds() {
        birds = new ArrayList<>();
        birds.add(new RedBird(world, BIRD_POSITION.x, BIRD_POSITION.y));
        birds.add(new BlackBird(world, 50, 175));
        birds.add(new YellowBird(world, 150, 175));
    }

    private void setCurrentBird() {
        if (!birds.isEmpty()) {
            currentBird = birds.remove(0);
            currentBird.getBody().setTransform(BIRD_POSITION, 0);
            currentBird.setStatic();
            birdLaunched = false;
        }
    }

    @Override
    public void show() {

        game.introMusic.pause();
        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("game_theme.mp3"));
        gameMusic.setLooping(true);
        gameMusic.play();
        background = new Texture("game_background.png");
        catapult = new Texture("slingshot.png");
//        debugRenderer = new Box2DDebugRenderer();

        // Create ground and structure
        ground = new Ground(world, 130);
        structure = new Structure2(world);

        winPopupScreen = new WinPopupScreen(game);
        losePopupScreen = new LosePopupScreen(game);
        pausePopupScreen = new PausePopupScreen(game, stage);

        // Touch input handling
        stage.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (birdLaunched){
                    return false;
                }
                Vector2 touchPos = (new Vector2(x, y));

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
                    Vector2 touchPos = (new Vector2(x, y));
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
                    birdLaunched = true;
                }
            }
        });

        Texture pauseTexture = new Texture("pause.png");
        Button pauseButton = new Button(new TextureRegionDrawable(pauseTexture));
        pauseButton.setPosition(10, game.viewport.getWorldHeight() - pauseButton.getHeight() - 10);

        pauseButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameEnded = true;
                Gdx.input.setInputProcessor(new PausePopupScreen(game, stage));
            }
        });

        stage.addActor(pauseButton);
        Gdx.input.setInputProcessor(stage);
    }

    private boolean isNearBird(Vector2 touchPos) {
        Vector2 birdPos = currentBird.getBody().getPosition();
        return touchPos.dst(birdPos) < 100f;
    }

    @Override
    public void render(float delta) {
        timer += delta;

        if (!gameEnded) {
            if (structure.areAllPigsDestroyed()) {
                gameEnded = true;
                Gdx.input.setInputProcessor(winPopupScreen);
            } else if (allBirdsUsed() && !structure.areAllPigsDestroyed()) {
                gameEnded = true;
                Gdx.input.setInputProcessor(losePopupScreen);
            }
        }
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.viewport.apply();
        game.batch.setProjectionMatrix(game.viewport.getCamera().combined);

        if (!gameEnded) {
            world.step(1 / 60f, 6, 2);
            float worldWidth = game.viewport.getWorldWidth();
            float worldHeight = game.viewport.getWorldHeight();

            game.batch.begin();
            game.batch.draw(background, 0, 0, worldWidth, worldHeight);
            game.batch.draw(catapult, 150, 100, catapult.getWidth(), catapult.getHeight());
            for (Bird bird : birds) {
                bird.render(game.batch);
            }
            if (currentBird != null && !birds.contains(currentBird)) {
                currentBird.render(game.batch);
            }
            structure.render(game.batch);
            game.batch.end();

            if (isDragging) {
                renderTrajectory();
            }

//            debugRenderer.render(world, game.viewport.getCamera().combined);

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
                    destroyBody.add(material.getBody());
                    return true;
                }
                return false;
            });

            structure.getPigs().removeIf(pig -> {
                if (pig.isMarkedForDestruction()) {
                    destroyBody.add(pig.getBody());
                    return true;
                }
                return false;
            });

            for (Body body : destroyBody) {
                world.destroyBody(body);
            }
            destroyBody.clear();
        } else {
            game.batch.begin();
            game.batch.draw(background, 0, 0, game.viewport.getWorldWidth(), game.viewport.getWorldHeight());
            game.batch.draw(catapult, 150, 100, catapult.getWidth(), catapult.getHeight());
            if (currentBird != null) {
                currentBird.render(game.batch);
            }
            structure.render(game.batch);
            game.batch.end();
        }

        if (gameEnded){
            if (structure.areAllPigsDestroyed()){
                winPopupScreen.draw();
            } else if (allBirdsUsed() && !structure.areAllPigsDestroyed()) {
                losePopupScreen.draw();
            } else {
                pausePopupScreen.draw();
            }
        }
    }

    private boolean allBirdsUsed() {
        return (currentBird == null && birds.isEmpty());
    }

    public void setPausePopupScreen() {
        pausePopupScreen = new PausePopupScreen(game, stage);
    }

    public void setGameEnded(boolean gameEnded) {
        this.gameEnded = gameEnded;
    }

    public Music getGameMusic() {
        return gameMusic;
    }

    private void renderTrajectory() {
        Texture trajectoryTexture = new Texture("circle.png");
        Vector2 velocity = new Vector2(currentTouchPosition).sub(initialTouchPosition).scl(LAUNCH_POWER_MULTIPLIER);
        velocity.x = -velocity.x;
        velocity.y = -velocity.y;
        if (velocity.len() > MAX_DRAG_DISTANCE) {
            velocity.nor().scl(MAX_DRAG_DISTANCE);
        }
        float gravity = 27f;
        float timeStep = 0.5f;
        float maxTrajectoryTime = 10f;
        Vector2 startPos = new Vector2(BIRD_POSITION);
        Vector2 currentPos = new Vector2(startPos);
        Vector2 currentVelocity = new Vector2(velocity);

        game.batch.begin();
        float initialScale = 2f;
        float minScale = 0.5f;

        for (float time = 0; time < maxTrajectoryTime; time += timeStep) {
            currentPos.x = startPos.x + currentVelocity.x * time;
            currentPos.y = startPos.y + currentVelocity.y * time - (0.5f * gravity * time * time);
            float distanceFromStart = currentPos.dst(startPos);
            float scale = Math.max(initialScale - (distanceFromStart / (MAX_DRAG_DISTANCE * 4)), minScale);
            game.batch.draw(
                trajectoryTexture,
                currentPos.x - (trajectoryTexture.getWidth() * scale / 2),
                currentPos.y - (trajectoryTexture.getHeight() * scale / 2),
                trajectoryTexture.getWidth() * scale,
                trajectoryTexture.getHeight() * scale
            );
            if (currentPos.y < ground.getHeight()) {
                break;
            }
        }
        game.batch.end();
        trajectoryTexture.dispose();
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
                if (isBirdGroundCollision(fixtureA, fixtureB)) {
                    birdCollided = true;
                }
            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold manifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse contactImpulse) {

            }
        };
        world.setContactListener(contactListener);
    }

    private void removeCurrentBird() {
        if (currentBird != null && currentBird.getBody() != null) {
            destroyBody.add(currentBird.getBody());
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

    private boolean isBirdGroundCollision(Fixture fixtureA, Fixture fixtureB) {
        return (isGround(fixtureA) && isBird(fixtureB)) || (isGround(fixtureB) && isBird(fixtureA));
    }
    private boolean isBird(Fixture fixture) {
        return currentBird != null && fixture.getBody() == currentBird.getBody();
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
//        debugRenderer.dispose();
        shapeRenderer.dispose();
    }
}
