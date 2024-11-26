package com.project.orion.angrybirds.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.project.orion.angrybirds.GameLauncher;

public class MainGameScreen implements Screen {
    private GameLauncher game;
    private Texture background;
    private Texture slingshot;
    private Texture horizontal_bamboo;
    private Texture vertical_bamboo;
    private Texture bamboo_box;
    private Texture red_bird;
    private Texture mvng_chuck;
    private Texture bomb;
    private Texture minion_pig;

    private Music gameMusic;

    private final Stage stage;
    private final Table table;
    private Texture pause;
    private Texture win;
    private Texture loss;
    private Texture winHover;
    private Texture lossHover;
    private Button pauseButton;
    private Button winButton;
    private Button lossButton;

//    private World world;
//    private Box2DDebugRenderer debugRenderer


    public MainGameScreen(GameLauncher game) {
        this.game = game;
        stage = new Stage(game.viewport, game.batch);
        table = new Table();
        table.setFillParent(true);
        table.top();
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

        winButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new WonScreen(game));
                dispose();
            }
        });

        lossButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new LoseScreen(game));
                dispose();
            }
        });

        table.add(pauseButton).padRight(1100);
        table.add(winButton).padTop(15);
        table.add(lossButton).padTop(15);
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.viewport.apply();
        game.batch.setProjectionMatrix(game.viewport.getCamera().combined);
        float worldWidth = game.viewport.getWorldWidth();
        float worldHeight = game.viewport.getWorldHeight();


        game.batch.begin();
        game.batch.draw(background, 0, 0, worldWidth, worldHeight);

        game.batch.draw(slingshot, 200, 150, 100, slingshot.getHeight());

        float birdWidth = 70;
        float birdHeight = 70;
        float slingshotlaunchx = 210;
        float slingshotlaunchy = 300;
        game.batch.draw(red_bird, slingshotlaunchx, slingshotlaunchy, birdWidth, birdHeight);

        // Structure
        System.out.println("Width: " + horizontal_bamboo.getWidth() + " Height: " + horizontal_bamboo.getHeight());
        game.batch.draw(vertical_bamboo, 1600, 140, vertical_bamboo.getWidth(), vertical_bamboo.getHeight()-200);
        game.batch.draw(vertical_bamboo, 1300, 140, vertical_bamboo.getWidth(), vertical_bamboo.getHeight()-200);
        game.batch.draw(horizontal_bamboo, 1265, 290);
        game.batch.draw(bamboo_box, 1390, 320);
        game.batch.draw(minion_pig, 1460, 490, 80, 80);

        // Moving yellow bird
        game.batch.draw(mvng_chuck, 900, 600, birdWidth, birdHeight);

        // Black bird in queue
        game.batch.draw(bomb, 120, 150, birdWidth, birdHeight+10);
        game.batch.end();
        stage.act(v);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        game.viewport.update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        background.dispose();
        slingshot.dispose();
        horizontal_bamboo.dispose();
        vertical_bamboo.dispose();
        bamboo_box.dispose();
        red_bird.dispose();
        mvng_chuck.dispose();
        bomb.dispose();
        minion_pig.dispose();
        gameMusic.dispose();
    }
}
// My name is sanskar
