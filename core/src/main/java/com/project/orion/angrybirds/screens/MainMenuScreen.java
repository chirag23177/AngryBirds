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
    private Texture playTexture;
    private Texture playHoverTexture;
    private Texture loadTexture;
    private Texture loadHoverTexture;
    private Texture exitTexture;
    private Texture exitHoverTexture;
    private final Stage stage;
    private final Table table;
    private Button playButton;
    private Button loadButton;
    private Button exitButton;


    public MainMenuScreen(GameLauncher game) {
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
        playTexture = new Texture("play_button.png");
        playHoverTexture = new Texture("Play_button_custom_hover1.png");
        loadTexture = new Texture("load_button.png");
        loadHoverTexture = new Texture("load_button_hover.png");
        exitTexture = new Texture("exit_button.png");
        exitHoverTexture = new Texture("exit_button_hover.png");

        // Implementing the hover button effect
        Button.ButtonStyle playButtonStyle = new Button.ButtonStyle();
        playButtonStyle.up = new TextureRegionDrawable(playTexture);
        playButtonStyle.over = new TextureRegionDrawable(playHoverTexture);

        Button.ButtonStyle loadButtonStyle = new Button.ButtonStyle();
        loadButtonStyle.up = new TextureRegionDrawable(loadTexture);
        loadButtonStyle.over = new TextureRegionDrawable(loadHoverTexture);

        Button.ButtonStyle exitButtonStyle = new Button.ButtonStyle();
        exitButtonStyle.up = new TextureRegionDrawable(exitTexture);
        exitButtonStyle.over = new TextureRegionDrawable(exitHoverTexture);

        playButton = new Button(playButtonStyle);
        loadButton = new Button(loadButtonStyle);
        exitButton = new Button(exitButtonStyle);

        // Making the buttons clickable
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

        Image logoImage = new Image(new TextureRegionDrawable(logo_img));
        table.add(logoImage).padBottom(100).row();
        table.add(playButton).padBottom(20).row();
        table.add(loadButton).padBottom(20).row();
        table.add(exitButton).row();

        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.viewport.apply();
        game.batch.setProjectionMatrix(game.viewport.getCamera().combined);
        game.batch.begin();
        game.batch.draw(main_background, 0, 0, game.viewport.getWorldWidth(), game.viewport.getWorldHeight());
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
        playTexture.dispose();
        playHoverTexture.dispose();
        loadTexture.dispose();
        loadHoverTexture.dispose();
        exitTexture.dispose();
        exitHoverTexture.dispose();
        stage.dispose();
    }
}
