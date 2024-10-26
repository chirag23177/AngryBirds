package com.project.orion.angrybirds.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.project.orion.angrybirds.GameLauncher;

public class LevelSelectionScreen implements Screen {
    private final GameLauncher game;
    private Texture main_background;
    private Texture logo_img;
    private Texture levelOne;
    private Texture levelTwo;
    private Texture levelThree;
    private Texture levelThreeHover;
    private Texture levelTwoHover;
    private Texture levelOneHover;
    private final Stage stage;
    private final Table table;
    private Button levelOneButton;
    private Button levelTwoButton;
    private Button levelThreeButton;

    public LevelSelectionScreen(GameLauncher game) {
        this.game = game;
        stage = new Stage(game.viewport, game.batch);
        table = new Table();
        table.center();
        table.setFillParent(true);
    }

    @Override
    public void show() {
        main_background = new Texture("main_background.png");
        logo_img = new Texture("logo.png");
        levelOne = new Texture("levelOne.png");
        levelTwo = new Texture("levelTwo.png");
        levelThree = new Texture("levelThree.png");
        levelThreeHover = new Texture("levelThreeHover.png");
        levelTwoHover = new Texture("levelTwoHover.png");
        levelOneHover = new Texture("levelOneHover.png");

        // Implementing the hover button effect
        Button.ButtonStyle levelOneButtonStyle = new Button.ButtonStyle();
        levelOneButtonStyle.up = new TextureRegionDrawable(levelOne);
        levelOneButtonStyle.over = new TextureRegionDrawable(levelOneHover);

        Button.ButtonStyle levelTwoButtonStyle = new Button.ButtonStyle();
        levelTwoButtonStyle.up = new TextureRegionDrawable(levelTwo);
        levelTwoButtonStyle.over = new TextureRegionDrawable(levelTwoHover);

        Button.ButtonStyle levelThreeButtonStyle = new Button.ButtonStyle();
        levelThreeButtonStyle.up = new TextureRegionDrawable(levelThree);
        levelThreeButtonStyle.over = new TextureRegionDrawable(levelThreeHover);

        levelOneButton = new Button(levelOneButtonStyle);
        levelTwoButton = new Button(levelTwoButtonStyle);
        levelThreeButton = new Button(levelThreeButtonStyle);

        levelOneButton.setSize(200, 150);
        levelTwoButton.setSize(200, 150);
        levelThreeButton.setSize(200, 150);

        // Making the buttons clickable
        levelOneButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainGameScreen(game));
                dispose();
            }
        });

        levelTwoButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainGameScreen(game));
                dispose();
            }
        });

        levelThreeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainGameScreen(game));
                dispose();
            }
        });

        Image logoImage = new Image(new TextureRegionDrawable(logo_img));
        table.add(logoImage).colspan(3).padBottom(70).padTop(-240).row();
        table.add(levelOneButton).size(levelOneButton.getWidth(), levelOneButton.getHeight()).padRight(20);
        table.add(levelTwoButton).size(levelTwoButton.getWidth(), levelTwoButton.getHeight()).padRight(20);
        table.add(levelThreeButton).size(levelThreeButton.getWidth(), levelThreeButton.getHeight());
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.viewport.apply();
        game.batch.setProjectionMatrix(game.viewport.getCamera().combined);
        game.batch.begin();
        float worldWidth = game.viewport.getWorldWidth();
        float worldHeight = game.viewport.getWorldHeight();
        game.batch.draw(main_background, 0, 0, worldWidth, worldHeight);
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new MainMenuScreen(game));
            dispose();
        }
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
        main_background.dispose();
        logo_img.dispose();
        levelOne.dispose();
        levelTwo.dispose();
        levelThree.dispose();
        levelThreeHover.dispose();
        levelTwoHover.dispose();
        levelOneHover.dispose();
        stage.dispose();
    }
}
