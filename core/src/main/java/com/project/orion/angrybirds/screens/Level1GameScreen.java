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
import com.project.orion.angrybirds.classes.Ground;
import com.project.orion.angrybirds.classes.RedBird;
import com.project.orion.angrybirds.classes.Structure1;

public class Level1GameScreen implements Screen {
    private GameLauncher game;
    private Texture background;
    private Music gameMusic;
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private ShapeRenderer shapeRenderer;

    private RedBird redBird;
    private Ground ground;
    private Structure1 structure1;

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

    public Level1GameScreen(GameLauncher game) {
        this.game = game;
        stage = new Stage(game.viewport, game.batch);
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void show() {
        game.introMusic.pause();
        background = new Texture("game_background.png");

        // Setup world with realistic gravity
        world = new World(new Vector2(0, -9.8f), true);
        debugRenderer = new Box2DDebugRenderer();

        // Create ground and structure
        ground = new Ground(world, 130);
        structure1 = new Structure1(world);

        // Create bird at the specified position
        redBird = new RedBird(world, BIRD_POSITION.x, BIRD_POSITION.y);
        redBird.setStatic(); // Keep bird stationary until launched

        // Touch input handling
        stage.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Vector2 touchPos = game.viewport.unproject(new Vector2(x, y));
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
                    currentTouchPosition.set(game.viewport.unproject(new Vector2(x, y)));
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
                    if (launchVelocity.len() > MAX_DRAG_DISTANCE) {
                        launchVelocity.nor().scl(MAX_DRAG_DISTANCE);
                    }
                    redBird.setDynamic();
                    redBird.getBody().setLinearVelocity(launchVelocity);
                    isDragging = false;
                }
            }
        });

        Gdx.input.setInputProcessor(stage);
    }

    private boolean isNearBird(Vector2 touchPos) {
        Vector2 birdPos = redBird.getBody().getPosition();
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
        redBird.render(game.batch);
        structure1.render(game.batch);
        game.batch.end();

        debugRenderer.render(world, game.viewport.getCamera().combined);

        stage.act(delta);
        stage.draw();
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
        gameMusic.dispose();
        redBird.dispose();
        world.dispose();
        debugRenderer.dispose();
        shapeRenderer.dispose();
    }
}
