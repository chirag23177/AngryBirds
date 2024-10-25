package com.project.orion.angrybirds.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.project.orion.angrybirds.GameLauncher;

import java.awt.*;

public class MainMenuScreen implements Screen {
    private final GameLauncher game;
    private Texture main_background;
    private Texture logo_img;
//    private Texture play_button;
//    private Texture load_button;
//    private Texture exit_button;
//    private float timer = 0;
    private FitViewport viewport;
    private Stage stage;
    private Table table;


    public MainMenuScreen(GameLauncher game) {
        this.game = game;
        stage = new Stage(game.viewport, game.batch);
        table = new Table();
    }

    @Override
    public void show() {
        main_background = new Texture("main_background.png");
        logo_img = new Texture("logo.png");

        // Creating buttons
        Texture playTexture = new Texture("play_button.png");
        Texture playHoverTexture = new Texture("Play_button_custom_hover1.png");
        Texture loadTexture = new Texture("load_button.png");
        Texture loadHoverTexture = new Texture("load_button_hover.png");
        Texture exitTexture = new Texture("exit_button.png");
        Texture exitHoverTexture = new Texture("exit_button_hover.png");

        Button.ButtonStyle playButtonStyle = new Button.ButtonStyle();
        playButtonStyle.up = new TextureRegionDrawable(playTexture);
        playButtonStyle.over = new TextureRegionDrawable(playHoverTexture);

        Button.ButtonStyle loadButtonStyle = new Button.ButtonStyle();
        loadButtonStyle.up = new TextureRegionDrawable(loadTexture);
        loadButtonStyle.over = new TextureRegionDrawable(loadHoverTexture);

        Button.ButtonStyle exitButtonStyle = new Button.ButtonStyle();
        exitButtonStyle.up = new TextureRegionDrawable(exitTexture);
        exitButtonStyle.over = new TextureRegionDrawable(exitHoverTexture);

        Button playButton = new Button(playButtonStyle);
        Button loadButton = new Button(loadButtonStyle);
        Button exitButton = new Button(exitButtonStyle);

        // set size of the buttons
//        playButton.setSize(400, 200);
//        loadButton.setSize(300, 100);
//        exitButton.setSize(300, 100);

        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new LevelSelectionScreen(game));
                dispose();
            }
        });

        loadButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Load game
                Gdx.app.exit();
            }
        });

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        // Creating table

        table.center();
        table.setFillParent(true);

        Image logoImage = new Image(new TextureRegionDrawable(logo_img));
        table.add(logoImage).padBottom(100).row();
        table.add(playButton).padBottom(20).row();
        table.add(playButton)
        table.add(loadButton).padBottom(20).row();
        table.add(exitButton).row();
//        table.add(loadButton).padBottom(20).row();
//        table.add(exitButton).padBottom(20).row();

        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);

//        play_button = new Texture("play_button.png");
//        load_button = new Texture("load_button.png");
//        exit_button = new Texture("exit_button.png");
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.viewport.apply();
        game.batch.setProjectionMatrix(game.viewport.getCamera().combined);
        game.batch.begin();
        game.batch.draw(main_background, 0, 0, game.viewport.getWorldWidth(), game.viewport.getWorldHeight());
//        float logo_x = (Gdx.graphics.getWidth() - logo_img.getWidth()) / 2;
//        float logo_y = ((Gdx.graphics.getHeight() - logo_img.getHeight()) / 2 )+350;
//        game.batch.draw(logo_img, logo_x, logo_y);

//        float play_x = (Gdx.graphics.getWidth() - play_button.getWidth()) / 2;
//        float play_y = (Gdx.graphics.getHeight() - play_button.getHeight()) / 2 +100;
//        game.batch.draw(play_button, play_x, play_y, play_button.getWidth()-70,play_button.getHeight()-70);
//
//        float load_x = (Gdx.graphics.getWidth() - load_button.getWidth()) / 2;
//        float load_y = (Gdx.graphics.getHeight() - load_button.getHeight()) / 2 - 50;
//        game.batch.draw(load_button, load_x, load_y, load_button.getWidth()-70,load_button.getHeight()-70);
//
//        float exit_x = (Gdx.graphics.getWidth() - exit_button.getWidth()) / 2;
//        float exit_y = (Gdx.graphics.getHeight() - exit_button.getHeight()) / 2 - 200;
//        game.batch.draw(exit_button, exit_x, exit_y, exit_button.getWidth()-70,exit_button.getHeight()-70);
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

    }
}
