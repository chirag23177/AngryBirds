package com.project.orion.angrybirds.classes;

import com.badlogic.gdx.physics.box2d.World;

public class RedBird extends Bird {
    public RedBird(World world, float x, float y) {
        super(world,"redBird.png", x, y,70);
        this.speed = 2;
        this.impact = 20;
    }
}

//
//
//
//
//
//package screens;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.math.Vector2;
//import com.badlogic.gdx.physics.box2d.*;
//    import com.badlogic.gdx.utils.ScreenUtils;
//import io.github.some_example_name.AngryBirds;
//import entities.Bird;
//import entities.Catapult;
//import entities.Ground;
//
//public class MainGameScreen implements Screen {
//    private static final int PAUSE_BUTTON_X = 20;
//    private static final int PAUSE_BUTTON_Y = 780;
//    private static final int PAUSE_BUTTON_WIDTH = 50;
//    private static final int PAUSE_BUTTON_HEIGHT = 50;
//    private static final int NEXT_LEVEL_BUTTON_X = 950;
//    private static final int NEXT_LEVEL_BUTTON_Y = 780;
//    private static final int NEXT_LEVEL_BUTTON_WIDTH = 50;
//    private static final int NEXT_LEVEL_BUTTON_HEIGHT = 50;
//    private static final int SAVE_AND_EXIT_X = 282;
//    private static final int SAVE_AND_EXIT_Y = 400;
//    private static final int SAVE_AND_EXIT_WIDTH = 463;
//    private static final int SAVE_AND_EXIT_HEIGHT = 104;
//    private static final float NEXT_LEVEL_DELAY = 0.5f; // 1 second delay
//
//    private Texture background;
//    private Texture pauseButton;
//    private Texture nextLevelButton;
//    private Texture saveAndExit;
//    private boolean isSaveAndExitVisible = false;
//    private boolean isPauseButtonPressed = false;
//    private boolean isNextLevelButtonPressed = false;
//    private float nextLevelButtonPressedTime = 0;
//
//    AngryBirds game;
//
//    private boolean isDragging = false;
//    private Vector2 initialTouch = new Vector2();
//    private Vector2 launchVelocity = new Vector2();
//
//    // Box2D variables
//    private World world;
//    private Box2DDebugRenderer debugRenderer;
//    private Bird bird;
//    private Ground ground;
//    private Catapult catapult;
//
//    public MainGameScreen(AngryBirds game) {
//        this.game = game;
//    }
//
//    @Override
//    public void show() {
//        background = new Texture("colored_grass.png");
//        pauseButton = new Texture("pause-circle.png");
//        nextLevelButton = new Texture("NextLevel.png");
//        saveAndExit = new Texture("SaveAndExit.png");
//
//        // Initialize Box2D world
//        world = new World(new Vector2(0, -9.8f), true);
//        debugRenderer = new Box2DDebugRenderer();
//
//        // Create ground
//        ground = new Ground(world, new Vector2(0, 0), 1024, 60);
//
//        // Create bird
//        bird = new Bird(world, "birdRed.png", new Vector2(150, 100), 50f);
//
//        // Create catapult
//        catapult = new Catapult("catapult.png", 100, 90, 100, 100);
//    }
//
//    @Override
//    public void render(float delta) {
//        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
//        world.step(1/60f, 6, 2);
//        debugRenderer.render(world, game.batch.getProjectionMatrix());
//
//        game.batch.begin();
//        game.batch.draw(background, 0, 0);
//        game.batch.draw(pauseButton, PAUSE_BUTTON_X, PAUSE_BUTTON_Y, PAUSE_BUTTON_WIDTH, PAUSE_BUTTON_HEIGHT);
//        game.batch.draw(nextLevelButton, NEXT_LEVEL_BUTTON_X, NEXT_LEVEL_BUTTON_Y, NEXT_LEVEL_BUTTON_WIDTH, NEXT_LEVEL_BUTTON_HEIGHT);
//
//        // Draw catapult
//        game.batch.draw(catapult.getTexture(), catapult.getX(), catapult.getY(), catapult.getWidth(), catapult.getHeight());
//
//        // Draw bird at its Box2D position with increased size
//        Vector2 birdPosition = bird.getPosition();
//        float birdSize = 50f; // Adjust this value to match the bird's radius
//        game.batch.draw(bird.getTexture(), birdPosition.x - birdSize / 2, birdPosition.y - birdSize / 2, birdSize, birdSize);
//
//        // Handle bird dragging and launching
//        handleInput(birdPosition, birdSize);
//
//        game.batch.end();
//    }
//
//    private void handleInput(Vector2 birdPosition, float birdSize) {
//        if (Gdx.input.isTouched()) {
//            int mouseX = Gdx.input.getX();
//            int mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();
//            Vector2 touchPosition = new Vector2(mouseX, mouseY);
//
//            if (!isDragging && touchPosition.dst(birdPosition) <= birdSize / 2) {
//                initialTouch.set(touchPosition);
//                isDragging = true;
//            } else if (isDragging) {
//                launchVelocity.set(initialTouch).sub(touchPosition).scl(0.1f);
//            }
//        } else {
//            if (isDragging) {
//                bird.setLinearVelocity(launchVelocity);
//                isDragging = false;
//            }
//        }
//    }
//
//    @Override
//    public void resize(int width, int height) {}
//
//    @Override
//    public void pause() {}
//
//    @Override
//    public void resume() {}
//
//    @Override
//    public void hide() {}
//
//    @Override
//    public void dispose() {
//        game.batch.dispose();
//        background.dispose();
//        pauseButton.dispose();
//        nextLevelButton.dispose();
//        saveAndExit.dispose();
//        bird.dispose();
//        catapult.dispose();
//        world.dispose();
//        debugRenderer.dispose();
//    }
//}
