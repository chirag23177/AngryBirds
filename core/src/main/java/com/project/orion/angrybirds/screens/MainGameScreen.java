//package com.project.orion.angrybirds.screens;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.audio.Music;
//import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
//import com.badlogic.gdx.physics.box2d.World;
//import com.badlogic.gdx.scenes.scene2d.InputEvent;
//import com.badlogic.gdx.scenes.scene2d.Stage;
//import com.badlogic.gdx.scenes.scene2d.ui.Button;
//import com.badlogic.gdx.scenes.scene2d.ui.Table;
//import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
//import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
//import com.project.orion.angrybirds.GameLauncher;
//import com.project.orion.angrybirds.classes.RedBird;
//import com.project.orion.angrybirds.classes.YellowBird;
//
//public class MainGameScreen implements Screen {
//    private GameLauncher game;
//    private Texture background;
//    private Texture slingshot;
//    private Texture horizontal_bamboo;
//    private Texture vertical_bamboo;
//    private Texture bamboo_box;
//    private Texture red_bird;
//    private Texture mvng_chuck;
//    private Texture bomb;
//    private Texture minion_pig;
//
//    private Music gameMusic;
//
//    private final Stage stage;
//    private final Table table;
//    private Texture pause;
//    private Texture win;
//    private Texture loss;
//    private Texture winHover;
//    private Texture lossHover;
//    private Button pauseButton;
//    private Button winButton;
//    private Button lossButton;
//
//
//    public MainGameScreen(GameLauncher game) {
//        this.game = game;
//        stage = new Stage(game.viewport, game.batch);
//        table = new Table();
//        table.setFillParent(true);
//        table.top();
//    }
//    @Override
//    public void show() {
//        game.introMusic.pause();
//        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("game_theme.mp3"));
//        gameMusic.setLooping(true);
//        gameMusic.play();
//        background = new Texture("game_background.png");
//        slingshot = new Texture("slingshot.png");
//        horizontal_bamboo = new Texture("horizontalBamboo.png");
//        vertical_bamboo = new Texture("verticalBamboo.png");
//        bamboo_box = new Texture("bambooBox.png");
//        red_bird = new Texture("redBird.png");
//        mvng_chuck = new Texture("chukMoving.png");
//        bomb = new Texture("bomb.png");
//        minion_pig = new Texture("minionPig.png");
//
//        pause = new Texture("pause.png");
//        pauseButton = new Button(new TextureRegionDrawable(pause));
//
//        win = new Texture("win.png");
//        loss = new Texture("loss.png");
//        winHover = new Texture("winHover.png");
//        lossHover = new Texture("lossHover.png");
//
//        Button.ButtonStyle winStyle = new Button.ButtonStyle();
//        winStyle.up = new TextureRegionDrawable(win);
//        winStyle.over = new TextureRegionDrawable(winHover);
//
//        Button.ButtonStyle lossStyle = new Button.ButtonStyle();
//        lossStyle.up = new TextureRegionDrawable(loss);
//        lossStyle.over = new TextureRegionDrawable(lossHover);
//
//        winButton = new Button(winStyle);
//        lossButton = new Button(lossStyle);
//
//        pauseButton.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                game.setScreen(new PauseScreen(game));
//                dispose();
//            }
//        });
//
//        winButton.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                game.setScreen(new WonScreen(game));
//                dispose();
//            }
//        });
//
//        lossButton.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                game.setScreen(new LoseScreen(game));
//                dispose();
//            }
//        });
//
//        table.add(pauseButton).padRight(1100);
//        table.add(winButton).padTop(15);
//        table.add(lossButton).padTop(15);
//        stage.addActor(table);
//        Gdx.input.setInputProcessor(stage);
//
//    }
//
//    @Override
//    public void render(float v) {
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//
//        game.viewport.apply();
//        game.batch.setProjectionMatrix(game.viewport.getCamera().combined);
//        float worldWidth = game.viewport.getWorldWidth();
//        float worldHeight = game.viewport.getWorldHeight();
//
//
//        game.batch.begin();
//        game.batch.draw(background, 0, 0, worldWidth, worldHeight);
//
//        game.batch.draw(slingshot, 200, 150, 100, slingshot.getHeight());
//
//        float birdWidth = 70;
//        float birdHeight = 70;
//        float slingshotlaunchx = 210;
//        float slingshotlaunchy = 300;
//        game.batch.draw(red_bird, slingshotlaunchx, slingshotlaunchy, birdWidth, birdHeight);
//
//        // Structure
//        game.batch.draw(vertical_bamboo, 1600, 140, vertical_bamboo.getWidth(), vertical_bamboo.getHeight()-200);
//        game.batch.draw(vertical_bamboo, 1300, 140, vertical_bamboo.getWidth(), vertical_bamboo.getHeight()-200);
//        game.batch.draw(horizontal_bamboo, 1265, 290);
//        game.batch.draw(bamboo_box, 1390, 320);
//        game.batch.draw(minion_pig, 1460, 490, 80, 80);
//
//        // Moving yellow bird
//        game.batch.draw(mvng_chuck, 900, 600, birdWidth, birdHeight);
//
//        // Black bird in queue
//        game.batch.draw(bomb, 120, 150, birdWidth, birdHeight+10);
//        game.batch.end();
//        stage.act(v);
//        stage.draw();
//    }
//
//    @Override
//    public void resize(int width, int height) {
//        game.viewport.update(width, height, true);
//    }
//
//    @Override
//    public void pause() {
//
//    }
//
//    @Override
//    public void resume() {
//
//    }
//
//    @Override
//    public void hide() {
//
//    }
//
//    @Override
//    public void dispose() {
//        background.dispose();
//        slingshot.dispose();
//        horizontal_bamboo.dispose();
//        vertical_bamboo.dispose();
//        bamboo_box.dispose();
//        red_bird.dispose();
//        mvng_chuck.dispose();
//        bomb.dispose();
//        minion_pig.dispose();
//        gameMusic.dispose();
//    }
//}
//// My name is sanskar






//package com.project.orion.angrybirds.screens;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.audio.Music;
//import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.math.Vector2;
//import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
//import com.badlogic.gdx.physics.box2d.World;
//import com.badlogic.gdx.scenes.scene2d.InputEvent;
//import com.badlogic.gdx.scenes.scene2d.InputListener;
//import com.badlogic.gdx.scenes.scene2d.Stage;
//import com.badlogic.gdx.scenes.scene2d.ui.Table;
//import com.project.orion.angrybirds.GameLauncher;
//import com.project.orion.angrybirds.classes.BlackBird;
//import com.project.orion.angrybirds.classes.RedBird;
//import com.project.orion.angrybirds.classes.YellowBird;
//
//public class MainGameScreen implements Screen {
//    private GameLauncher game;
//    private Texture background;
//    private Music gameMusic;
//    private World world;
//    private Box2DDebugRenderer debugRenderer;
//
//    private RedBird redBird;
////    private YellowBird yellowBird;
////    private BlackBird blackBird;
//
//    private final Stage stage;
////    private final Table table;
//
//    private boolean isDragging = false;
//    private Vector2 initialTouch = new Vector2();
//    private Vector2 launchVelocity = new Vector2();
//
//    public MainGameScreen(GameLauncher game) {
//        this.game = game;
//        stage = new Stage(game.viewport, game.batch);
////        table = new Table();
////        table.setFillParent(true);
////        table.top();
//    }
//
//    @Override
//    public void show() {
//        game.introMusic.pause();
//        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("game_theme.mp3"));
//        gameMusic.setLooping(true);
//        gameMusic.play();
//        background = new Texture("game_background.png");
//
//        world = new World(new Vector2(0, -9.8f), true);
//        debugRenderer = new Box2DDebugRenderer();
//
//        float screenWidth = game.viewport.getWorldWidth();
//        float screenHeight = game.viewport.getWorldHeight();
//        float birdY = screenHeight / 2;
//
//        redBird = new RedBird(world, screenWidth / 4, birdY);
////        yellowBird = new YellowBird(world, screenWidth / 2, birdY);
////        blackBird = new BlackBird(world, 3 * screenWidth / 4, birdY);
//
//        stage.addListener(new InputListener() {
//            @Override
//            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//                redBird.setDynamic();
////                yellowBird.setDynamic();
////                blackBird.setDynamic();
//                return true;
//            }
//        });
//
//        Gdx.input.setInputProcessor(stage);
//    }
//
//    @Override
//    public void render(float delta) {
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//
//        world.step(1 / 60f, 6, 2);
//
//        game.viewport.apply();
//        game.batch.setProjectionMatrix(game.viewport.getCamera().combined);
//        float worldWidth = game.viewport.getWorldWidth();
//        float worldHeight = game.viewport.getWorldHeight();
//
//        game.batch.begin();
//        game.batch.draw(background, 0, 0, worldWidth, worldHeight);
//        redBird.render(game.batch);
////        yellowBird.render(game.batch);
////        blackBird.render(game.batch);
//        game.batch.end();
//
//        debugRenderer.render(world, game.viewport.getCamera().combined);
//
//        stage.act(delta);
//        stage.draw();
//    }
//
//    @Override
//    public void resize(int width, int height) {
//        game.viewport.update(width, height, true);
//    }
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
//        background.dispose();
//        gameMusic.dispose();
//        redBird.dispose();
////        yellowBird.dispose();
////        blackBird.dispose();
//        world.dispose();
//        debugRenderer.dispose();
//    }
//}












package com.project.orion.angrybirds.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.project.orion.angrybirds.GameLauncher;
import com.project.orion.angrybirds.classes.BlackBird;
import com.project.orion.angrybirds.classes.RedBird;
import com.project.orion.angrybirds.classes.YellowBird;

public class MainGameScreen implements Screen {
    private GameLauncher game;
    private Texture background;
    private Music gameMusic;
    private World world;
    private Box2DDebugRenderer debugRenderer;

    private RedBird redBird;
//    private YellowBird yellowBird;
//    private BlackBird blackBird;

    private final Stage stage;
//    private final Table table;

    private boolean isDragging = false;
    private Vector2 initialTouch = new Vector2();
    private Vector2 launchVelocity = new Vector2();

    public MainGameScreen(GameLauncher game) {
        this.game = game;
        stage = new Stage(game.viewport, game.batch);
//        table = new Table();
//        table.setFillParent(true);
//        table.top();
    }

    @Override
    public void show() {
        game.introMusic.pause();
        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("game_theme.mp3"));
        gameMusic.setLooping(true);
        gameMusic.play();
        background = new Texture("game_background.png");

        slingshot = new Texture("slingshot.png");
        horizontal_bamboo = new Texture("horizontalBamboo.png");
        vertical_bamboo = new Texture("verticalBamboo.png");
        bamboo_box = new Texture("bambooBox.png");
        red_bird = new Texture("redBird.png");
        mvng_chuck = new Texture("chukMoving.png");
        bomb = new Texture("blackBird.png");
        minion_pig = new Texture("minionPig.png");

        pause = new Texture("pause.png");
        pauseButton = new Button(new TextureRegionDrawable(pause));

        win = new Texture("win.png");
        loss = new Texture("loss.png");
        winHover = new Texture("winHover.png");
        lossHover = new Texture("lossHover.png");

        Button.ButtonStyle winStyle = new Button.ButtonStyle();
        winStyle.up = new TextureRegionDrawable(win);
        winStyle.over = new TextureRegionDrawable(winHover);

        Button.ButtonStyle lossStyle = new Button.ButtonStyle();
        lossStyle.up = new TextureRegionDrawable(loss);
        lossStyle.over = new TextureRegionDrawable(lossHover);

        winButton = new Button(winStyle);
        lossButton = new Button(lossStyle);

        pauseButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new PauseScreen(game));
                dispose();
            }
        });


        world = new World(new Vector2(0, -9.8f), true);
        debugRenderer = new Box2DDebugRenderer();

        float screenWidth = game.viewport.getWorldWidth();
        float screenHeight = game.viewport.getWorldHeight();
        float birdY = screenHeight / 2;

        redBird = new RedBird(world, screenWidth / 4, birdY);
//        yellowBird = new YellowBird(world, screenWidth / 2, birdY);
//        blackBird = new BlackBird(world, 3 * screenWidth / 4, birdY);

//        stage.addListener(new InputListener() {
//            @Override
//            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//                redBird.setDynamic();
////                yellowBird.setDynamic();
////                blackBird.setDynamic();
//                return true;
//            }
//        });
//
//        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.step(1 / 60f, 6, 2);
        debugRenderer.render(world, game.viewport.getCamera().combined);

        game.viewport.apply();
        game.batch.setProjectionMatrix(game.viewport.getCamera().combined);
        float worldWidth = game.viewport.getWorldWidth();
        float worldHeight = game.viewport.getWorldHeight();

        game.batch.begin();
        game.batch.draw(background, 0, 0, worldWidth, worldHeight);

        Vector2 birdPosition = redBird.getBody().getPosition();
        float birdSize = 50.0f;
        game.batch.draw(redBird.getTexture(), birdPosition.x - birdSize / 2, birdPosition.y - birdSize / 2, birdSize, birdSize);
//        redBird.render(game.batch);
        handleInput(birdPosition, birdSize);
//        yellowBird.render(game.batch);
//        blackBird.render(game.batch);
        game.batch.end();

        debugRenderer.render(world, game.viewport.getCamera().combined);


        stage.act(delta);
        stage.draw();
    }

        // Structure
        System.out.println("Width: " + horizontal_bamboo.getWidth() + " Height: " + horizontal_bamboo.getHeight());
        game.batch.draw(vertical_bamboo, 1600, 140, vertical_bamboo.getWidth(), vertical_bamboo.getHeight()-200);
        game.batch.draw(vertical_bamboo, 1300, 140, vertical_bamboo.getWidth(), vertical_bamboo.getHeight()-200);
        game.batch.draw(horizontal_bamboo, 1265, 290);
        game.batch.draw(bamboo_box, 1390, 320);
        game.batch.draw(minion_pig, 1460, 490, 80, 80);


    private void handleInput(Vector2 birdPosition, float birdSize) {
        if (Gdx.input.isTouched()) {
            int mouseX = Gdx.input.getX();
            int mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();
            Vector2 touchPosition = new Vector2(mouseX, mouseY);
            if (!isDragging && touchPosition.dst(birdPosition) <= birdSize / 2) {
                initialTouch.set(touchPosition);
                isDragging = true;
            } else if (isDragging) {
                launchVelocity.set(initialTouch).sub(touchPosition).scl(0.1f);
            } else {
                if (isDragging) {
                    redBird.getBody().setLinearVelocity(launchVelocity);
                    isDragging = false;
                }
            }
        }

//            Vector2 touch = game.viewport.unproject(new Vector2(mouseX, mouseY));
//            if (redBird.getBody().getFixtureList().first().testPoint(touch.x, touch.y)) {
//                isDragging = true;
//                initialTouch.set(touch);
//            }
//        } else if (isDragging) {
//            Vector2 touch = game.viewport.unproject(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
//            launchVelocity.set(initialTouch).sub(touch).scl(5);
//            redBird.getBody().setType(BodyDef.BodyType.DynamicBody);
//            redBird.getBody().applyLinearImpulse(launchVelocity, redBird.getBody().getWorldCenter(), true);
//            isDragging = false;
//        }
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
//        yellowBird.dispose();
//        blackBird.dispose();
        world.dispose();
        debugRenderer.dispose();
    }
}



































//package com.project.orion.angrybirds.screens;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.math.Vector2;
//import com.badlogic.gdx.physics.box2d.BodyDef;
//import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
//import com.badlogic.gdx.physics.box2d.World;
//import com.badlogic.gdx.scenes.scene2d.InputEvent;
//import com.badlogic.gdx.scenes.scene2d.InputListener;
//import com.badlogic.gdx.scenes.scene2d.Stage;
//import com.project.orion.angrybirds.GameLauncher;
//import com.project.orion.angrybirds.classes.RedBird;
//
//public class MainGameScreen implements Screen {
//    private GameLauncher game;
//    private Texture background;
//    private World world;
//    private Box2DDebugRenderer debugRenderer;
//    private RedBird redBird;
//    private Vector2 initialTouch;
//    private boolean isDragging;
//    private final Stage stage;
//
//    public MainGameScreen(GameLauncher game) {
//        this.game = game;
//        this.world = new World(new Vector2(0, -9.8f), true); // Gravity applied here
//        this.debugRenderer = new Box2DDebugRenderer();
//        this.redBird = new RedBird(world, 200, 300); // Starting position of the bird
//        this.stage = new Stage(game.viewport, game.batch);
//        this.isDragging = false;
//        this.initialTouch = new Vector2();
//
//        stage.addListener(new InputListener() {
//            @Override
//            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//                // Check if the touch point overlaps the bird
//                if (redBird.getBody().getFixtureList().first().testPoint(x, y)) {
//                    isDragging = true;
//                    initialTouch.set(x, y);
//                    return true;
//                }
//                return false;
//            }
//
//            @Override
//            public void touchDragged(InputEvent event, float x, float y, int pointer) {
//                // Update bird position while dragging
//                if (isDragging) {
//                    redBird.getBody().setTransform(x, y, 0);
//                }
//            }
//
//            @Override
//            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
//                if (isDragging) {
//                    isDragging = false;
//                    Vector2 releasePoint = new Vector2(x, y);
////                    Vector2 launchVector = initialTouch.sub(releasePoint).scl(101); // Adjust scaling for velocity
////                    redBird.setDynamic(); // Change the bird to a DynamicBody
////                    redBird.getBody().applyLinearImpulse(launchVector, redBird.getBody().getWorldCenter(), true);
////                    redBird.getBody().setType(BodyDef.BodyType.DynamicBody);
////                    redBird.getBody().applyLinearImpulse(launchVector, redBird.getBody().getWorldCenter(), true);
//                    // In your touchUp logic
//                    redBird.getBody().setType(BodyDef.BodyType.DynamicBody); // Ensure it's dynamic
//                    Vector2 launchVector = new Vector2(-15f, 100f); // Adjust these values as needed
//                    redBird.getBody().applyLinearImpulse(launchVector, redBird.getBody().getWorldCenter(), true);
//
//
//                }
//            }
//        });
//
//        Gdx.input.setInputProcessor(stage);
//    }
//
//    @Override
//    public void show() {
//        background = new Texture("game_background.png");
//    }
//
//    @Override
//    public void render(float delta) {
//        // Clear the screen
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//
//        // Step the physics simulation
//        world.step(1 / 60f, 6, 2);
//
//        // Draw the game objects
//        game.batch.begin();
//        game.batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        redBird.render(game.batch);
//        game.batch.end();
//
//        // Debug rendering (optional, for testing Box2D bodies)
//        debugRenderer.render(world, game.batch.getProjectionMatrix());
//
//        // Update and draw UI elements
//        stage.act(delta);
//        stage.draw();
//    }
//
//    @Override
//    public void resize(int width, int height) {
//        game.viewport.update(width, height, true);
//    }
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
//        background.dispose();
//        redBird.dispose();
//        world.dispose();
//        debugRenderer.dispose();
//    }
//}




//package com.project.orion.angrybirds.screens;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.OrthographicCamera; // Added for camera
//import com.badlogic.gdx.math.Vector2;
//import com.badlogic.gdx.math.Vector3; // Added for coordinate conversion
//import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
//import com.badlogic.gdx.physics.box2d.World;
//import com.badlogic.gdx.scenes.scene2d.InputEvent;
//import com.badlogic.gdx.scenes.scene2d.InputListener;
//import com.badlogic.gdx.scenes.scene2d.Stage;
//import com.badlogic.gdx.utils.viewport.ScreenViewport; // Added for viewport
//import com.project.orion.angrybirds.GameLauncher;
//import com.project.orion.angrybirds.classes.RedBird;
//
//public class MainGameScreen implements Screen {
//    private static final float PPM = 100; // Pixels per meter
//    private GameLauncher game;
//    private Texture background;
//    private World world;
//    private Box2DDebugRenderer debugRenderer;
//    private RedBird redBird;
//    private Vector2 initialTouch;
//    private boolean isDragging;
//    private final Stage stage;
//    private OrthographicCamera camera;
//
//    public MainGameScreen(GameLauncher game) {
//        this.game = game;
//        camera = new OrthographicCamera();
//        camera.setToOrtho(false, Gdx.graphics.getWidth() / PPM, Gdx.graphics.getHeight() / PPM);
//
//        stage = new Stage(new ScreenViewport(camera), game.batch);
//        initialTouch = new Vector2();
//        world = new World(new Vector2(0, -10f), true);
//        debugRenderer = new Box2DDebugRenderer();
//        redBird = new RedBird(world, 200, 300); // Position in meters
//
//        stage.addListener(new InputListener() {
//            @Override
//            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//                Vector3 touchPos = new Vector3(x, y, 0);
//                camera.unproject(touchPos);
//                if (redBird.getBody().getFixtureList().first().testPoint(touchPos.x, touchPos.y)) {
//                    isDragging = true;
//                    initialTouch.set(touchPos.x, touchPos.y);
//                    return true;
//                }
//                return false;
//            }
//
//            @Override
//            public void touchDragged(InputEvent event, float x, float y, int pointer) {
//                if (isDragging) {
//                    Vector3 touchPos = new Vector3(x, y, 0);
//                    camera.unproject(touchPos);
//                    redBird.getBody().setTransform(touchPos.x, touchPos.y, 0);
//                }
//            }
//
//            @Override
//            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
//                if (isDragging) {
//                    Vector3 touchPos = new Vector3(x, y, 0);
//                    camera.unproject(touchPos);
//                    Vector2 releasePoint = new Vector2(touchPos.x, touchPos.y);
//                    Vector2 launchVector = new Vector2(initialTouch).sub(releasePoint).scl(5); // Adjust scaling
//                    redBird.getBody().applyLinearImpulse(launchVector, redBird.getBody().getWorldCenter(), true);
//                    isDragging = false;
//                }
//            }
//        });
//        Gdx.input.setInputProcessor(stage);
//    }
//
//    @Override
//    public void show() {
//        background = new Texture("game_background.png");
//    }
//
//    @Override
//    public void render(float delta) {
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        camera.update();
//        world.step(1 / 60f, 6, 2);
//
//        game.batch.setProjectionMatrix(camera.combined);
//        game.batch.begin();
//        game.batch.draw(background, 0, 0, camera.viewportWidth, camera.viewportHeight);
//        redBird.render(game.batch);
//        game.batch.end();
//
//        debugRenderer.render(world, camera.combined);
//        stage.act(delta);
//        stage.draw();
//    }
//
//    @Override
//    public void resize(int width, int height) {
//        camera.viewportWidth = width / PPM;
//        camera.viewportHeight = height / PPM;
//        camera.update();
//        stage.getViewport().update(width, height, true);
//    }
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
//        background.dispose();
//        world.dispose();
//        debugRenderer.dispose();
//        redBird.dispose();
//        stage.dispose();
//    }
//}

//
//package com.project.orion.angrybirds.screens;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.OrthographicCamera;
//import com.badlogic.gdx.math.Vector2;
//import com.badlogic.gdx.math.Vector3;
//import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
//import com.badlogic.gdx.physics.box2d.World;
//import com.badlogic.gdx.scenes.scene2d.InputEvent;
//import com.badlogic.gdx.scenes.scene2d.InputListener;
//import com.badlogic.gdx.scenes.scene2d.Stage;
//import com.badlogic.gdx.utils.viewport.ScreenViewport;
//import com.project.orion.angrybirds.GameLauncher;
//import com.project.orion.angrybirds.classes.RedBird;
//
//public class MainGameScreen implements Screen {
//    private static final float PPM = 100f; // Pixels per meter
//    private GameLauncher game;
//    private Texture background;
//    private World world;
//    private Box2DDebugRenderer debugRenderer;
//    private RedBird redBird;
//    private Vector2 initialTouch;
//    private boolean isDragging;
//    private final Stage stage;
//    private OrthographicCamera camera;
//    Vector2 result;
//
//    public MainGameScreen(GameLauncher game) {
//        this.game = game;
//
//        // Initialize the camera and viewport
//        camera = new OrthographicCamera();
//        camera.setToOrtho(false, Gdx.graphics.getWidth() / PPM, Gdx.graphics.getHeight() / PPM);
//        stage = new Stage(new ScreenViewport(camera), game.batch);
//
//        // Initialize the world and debug renderer
//        world = new World(new Vector2(0, -10f), true);
//        debugRenderer = new Box2DDebugRenderer();
//
//        // Position the bird at (200, 300) pixels, converted to meters
//        float birdPosX = 200f ;
//        float birdPosY = 300f ;
//        redBird = new RedBird(world, birdPosX, birdPosY);
//
//        initialTouch = new Vector2();
//        setupInputListener();
//
//        Gdx.input.setInputProcessor(stage);
//    }
//
//    private void setupInputListener() {
//        stage.addListener(new InputListener() {
//            @Override
//            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//                Vector3 touchPos = new Vector3(x, y, 0);
//                camera.unproject(touchPos);
//
//                if (redBird.getBody().getFixtureList().first().testPoint(touchPos.x, touchPos.y)) {
//                    isDragging = true;
//                    initialTouch.set(touchPos.x, touchPos.y);
//                    return true;
//                }
//                return false;
//            }
//
//            @Override
//            public void touchDragged(InputEvent event, float x, float y, int pointer) {
//                if (isDragging) {
//                    Vector3 touchPos = new Vector3(x, y, 0);
//                    camera.unproject(touchPos);
//                    redBird.getBody().setTransform(touchPos.x, touchPos.y, 0);
//                }
//            }
//
//            @Override
//            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
//                if (isDragging) {
//                    Vector3 touchPos = new Vector3(x, y, 0);
//                    camera.unproject(touchPos);
//                    Vector2 releasePoint = new Vector2(touchPos.x, touchPos.y);
//
//                    // Calculate the launch vector
//                    Vector2 launchVector = new Vector2(initialTouch).sub(releasePoint).scl(2f); // Adjust the scaling factor as needed
//
//                    // Apply the impulse to the bird's body
//                    redBird.getBody().setLinearVelocity(0, 0); // Reset velocity
//                    redBird.getBody().setAngularVelocity(0);   // Reset angular velocity
//                    redBird.getBody().applyLinearImpulse(launchVector, redBird.getBody().getWorldCenter(), true);
//
//                    isDragging = false;
////                    result = initialTouch.sub(releasePoint);
//                }
//            }
//        });
//    }
//
//    @Override
//    public void show() {
//        background = new Texture("game_background.png");
//    }
//
//    @Override
//    public void render(float delta) {
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//
//        // Update the camera and world
//        camera.update();
//        world.step(1 / 60f, 6, 2);
//
//        // Render the game elements
//        game.batch.setProjectionMatrix(camera.combined);
//        game.batch.begin();
//        game.batch.draw(background, 0, 0, camera.viewportWidth, camera.viewportHeight);
//        redBird.render(game.batch);
//        game.batch.end();
//
//        // Render debug information
//        redBird.getBody().applyLinearImpulse(1f,.3f,200,300,true);
//        debugRenderer.render(world, camera.combined);
//
//        // Update and draw the stage
//        stage.act(delta);
//        stage.draw();
//    }
//
//    @Override
//    public void resize(int width, int height) {
//        camera.viewportWidth = width / PPM;
//        camera.viewportHeight = height / PPM;
//        camera.update();
//        stage.getViewport().update(width, height, true);
//    }
//
//    @Override
//    public void pause() {
//
//    }
//
//    @Override
//    public void resume() {
//
//    }
//
//    @Override
//    public void hide() {
//
//    }
//
//    @Override
//    public void dispose() {
//        background.dispose();
//        world.dispose();
//        debugRenderer.dispose();
//        redBird.dispose();
//        stage.dispose();
//    }
//
//    // Other overridden methods (pause, resume, hide) can remain empty or be implemented as needed
//}




//package com.project.orion.angrybirds.screens;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.OrthographicCamera;
//import com.badlogic.gdx.math.Vector2;
//import com.badlogic.gdx.math.Vector3;
//import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
//import com.badlogic.gdx.physics.box2d.World;
//import com.badlogic.gdx.scenes.scene2d.InputEvent;
//import com.badlogic.gdx.scenes.scene2d.InputListener;
//import com.badlogic.gdx.scenes.scene2d.Stage;
//import com.badlogic.gdx.utils.viewport.ScreenViewport;
//import com.project.orion.angrybirds.GameLauncher;
//import com.project.orion.angrybirds.classes.RedBird;
//
//public class MainGameScreen implements Screen {
//    private static final float PPM = 100; // Pixels per meter
//    private GameLauncher game;
//    private Texture background;
//    private World world;
//    private Box2DDebugRenderer debugRenderer;
//    private RedBird redBird;
//    private Vector2 initialTouch;
//    private boolean isDragging;
//    private final Stage stage;
//    private OrthographicCamera camera;
//
//    public MainGameScreen(GameLauncher game) {
//        this.game = game;
//        camera = new OrthographicCamera();
//        camera.setToOrtho(false, Gdx.graphics.getWidth() / PPM, Gdx.graphics.getHeight() / PPM);
//
//        stage = new Stage(new ScreenViewport(camera), game.batch);
//        initialTouch = new Vector2();
//        world = new World(new Vector2(0, -10f), true);
//        debugRenderer = new Box2DDebugRenderer();
//        redBird = new RedBird(world, 200, 300); // Position in meters
//
//        stage.addListener(new InputListener() {
//            @Override
//            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//                Vector3 touchPos = new Vector3(x, y, 0);
//                camera.unproject(touchPos);
//                if (redBird.getBody().getFixtureList().first().testPoint(touchPos.x, touchPos.y)) {
//                    isDragging = true;
//                    initialTouch.set(touchPos.x, touchPos.y);
//                    return true;
//                }
//                return false;
//            }
//
//            @Override
//            public void touchDragged(InputEvent event, float x, float y, int pointer) {
//                if (isDragging) {
//                    Vector3 touchPos = new Vector3(x, y, 0);
//                    camera.unproject(touchPos);
//                    redBird.getBody().setTransform(touchPos.x, touchPos.y, 0);
//                }
//            }
//
//            @Override
//            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
//                if (isDragging) {
//                    Vector3 touchPos = new Vector3(x, y, 0);
//                    camera.unproject(touchPos);
//                    Vector2 releasePoint = new Vector2(touchPos.x, touchPos.y);
//                    Vector2 launchVector = new Vector2(initialTouch).sub(releasePoint).scl(5); // Adjust scaling
//                    redBird.getBody().applyLinearImpulse(launchVector, redBird.getBody().getWorldCenter(), true);
//                    isDragging = false;
//                }
//            }
//        });
//        Gdx.input.setInputProcessor(stage);
//    }
//
//    @Override
//    public void show() {
//        background = new Texture("game_background.png");
//    }
//
//    @Override
//    public void render(float delta) {
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        camera.update();
//        world.step(1 / 60f, 6, 2);
//
//        game.batch.setProjectionMatrix(camera.combined);
//        game.batch.begin();
//        game.batch.draw(background, 0, 0, camera.viewportWidth, camera.viewportHeight);
//        redBird.render(game.batch);
//        game.batch.end();
//
//        debugRenderer.render(world, camera.combined);
//        stage.act(delta);
//        stage.draw();
//    }
//
//    @Override
//    public void resize(int width, int height) {
//        camera.viewportWidth = width / PPM;
//        camera.viewportHeight = height / PPM;
//        camera.update();
//        stage.getViewport().update(width, height, true);
//    }
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
//        background.dispose();
//        world.dispose();
//        debugRenderer.dispose();
//        redBird.dispose();
//        stage.dispose();
//    }
//}
//
//package com.project.orion.angrybirds.screens;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.OrthographicCamera;
//import com.badlogic.gdx.math.Vector2;
//import com.badlogic.gdx.math.Vector3;
//import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
//import com.badlogic.gdx.physics.box2d.World;
//import com.badlogic.gdx.scenes.scene2d.InputEvent;
//import com.badlogic.gdx.scenes.scene2d.InputListener;
//import com.badlogic.gdx.scenes.scene2d.Stage;
//import com.badlogic.gdx.utils.viewport.ScreenViewport;
//import com.project.orion.angrybirds.GameLauncher;
//import com.project.orion.angrybirds.classes.RedBird;
//
//public class MainGameScreen implements Screen {
////    private static final float PPM = 100; // Pixels per meter
//    private GameLauncher game;
//    private Texture background;
//    private World world;
//    private Box2DDebugRenderer debugRenderer;
//    private RedBird redBird;
//    private Vector2 initialTouch;
//    private boolean isDragging;
//    private final Stage stage;
//    private OrthographicCamera camera;
//    Vector2 launchVector;
//
//    public MainGameScreen(GameLauncher game) {
//        this.game = game;
//        camera = new OrthographicCamera();
//        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() );
//
//        stage = new Stage(new ScreenViewport(camera), game.batch);
//        initialTouch = new Vector2();
//        world = new World(new Vector2(0, -10f), true);
//        debugRenderer = new Box2DDebugRenderer();
//        redBird = new RedBird(world, 200, 300); // Position in meters
//
//        stage.addListener(new InputListener() {
//            @Override
//            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//                Vector3 touchPos = new Vector3(x, y, 0);
//                camera.unproject(touchPos);
//                if (redBird.getBody().getFixtureList().first().testPoint(touchPos.x, touchPos.y)) {
//                    isDragging = true;
//                    initialTouch.set(touchPos.x, touchPos.y);
//                    return true;
//                }
//                return false;
//            }
//
//            @Override
//            public void touchDragged(InputEvent event, float x, float y, int pointer) {
//                if (isDragging) {
//                    Vector3 touchPos = new Vector3(x, y, 0);
//                    camera.unproject(touchPos);
//                    redBird.getBody().setTransform(touchPos.x, touchPos.y, 0);
//                }
//            }
//
//            @Override
//            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
//                if (isDragging) {
//                    Vector3 touchPos = new Vector3(x, y, 0);
//                    camera.unproject(touchPos);
//                    Vector2 releasePoint = new Vector2(touchPos.x, touchPos.y);
//                    launchVector = new Vector2(initialTouch).sub(releasePoint).scl(5); // Adjust scaling
//
//                    isDragging = false;
//                }
//            }
//        });
//        Gdx.input.setInputProcessor(stage);
//    }
//
//    @Override
//    public void show() {
//        background = new Texture("game_background.png");
//    }
//
//    @Override
//    public void render(float delta) {
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        redBird.getBody().applyLinearImpulse(launchVector, redBird.getBody().getWorldCenter(), true);
//        camera.update();
//
//
//        game.batch.setProjectionMatrix(camera.combined);
//        game.batch.begin();
//        game.batch.draw(background, 0, 0, camera.viewportWidth, camera.viewportHeight);
//        redBird.render(game.batch);
//        game.batch.end();
//
//        debugRenderer.render(world, camera.combined);
//        stage.act(delta);
//        stage.draw();
//        world.step(1 / 60f, 6, 2);
//    }
//
//    @Override
//    public void resize(int width, int height) {
//        camera.viewportWidth = width;
//        camera.viewportHeight = height;
//        camera.update();
//        stage.getViewport().update(width, height, true);
//    }
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
//        background.dispose();
//        world.dispose();
//        debugRenderer.dispose();
//        redBird.dispose();
//        stage.dispose();
//    }
//}
