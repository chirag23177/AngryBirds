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


    public MainMenuScreen(GameLauncher game) {
        this.game = game;
        viewport = new FitViewport(2000, 1000);
        stage = new Stage(viewport, game.batch);
    }

    @Override
    public void show() {
        main_background = new Texture("main_background.png");
        logo_img = new Texture("logo.png");

        // Creating buttons
        Texture playTexture = new Texture("play_button.png");
        Texture loadTexture = new Texture("load_button.png");
        Texture exitTexture = new Texture("exit_button.png");

        Button playButton = new Button(new TextureRegionDrawable(playTexture));
        Button loadButton = new Button(new TextureRegionDrawable(loadTexture));
        Button exitButton = new Button(new TextureRegionDrawable(exitTexture));

        // set size of the buttons
        playButton.setSize(300, 100);
        loadButton.setSize(300, 100);
        exitButton.setSize(300, 100);

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
            }
        });

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        // Creating table
        Table table = new Table();
        table.center();
        table.setFillParent(true);

        Image logoImage = new Image(new TextureRegionDrawable(logo_img));
        table.add(logoImage).padBottom(100).row();
        table.add(playButton).size(playButton.getWidth(),playButton.getHeight()).padBottom(20).row();
        table.add(loadButton).size(loadButton.getWidth(),loadButton.getHeight()).padBottom(20).row();
        table.add(exitButton).size(exitButton.getWidth(),exitButton.getHeight()).padBottom(20).row();
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
//        timer += v;
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        viewport.apply();
//        game.batch.setProjectionMatrix(viewport.getCamera().combined);

        game.batch.begin();
        game.batch.draw(main_background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
//        if (timer > 4){
//            game.setScreen(new LevelSelectionScreen(game));
//            dispose();
//        }

    }

    @Override
    public void resize(int i, int i1) {
        game.batch.getProjectionMatrix().setToOrtho2D(0, 0, i, i1);
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
