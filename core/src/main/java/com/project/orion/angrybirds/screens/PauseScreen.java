package com.project.orion.angrybirds.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.project.orion.angrybirds.GameLauncher;

public class PauseScreen implements Screen {
    private GameLauncher game;
    private Texture background;
    private Texture popup;
    private Texture sound;
    private Texture retry;
    private Texture level;
    private Texture cross;
    private Texture save;
    private Texture saveHover;
    private Stage stage;
    private Table table;

    public PauseScreen(GameLauncher game) {
        this.game = game;
        stage = new Stage(game.viewport, game.batch);
        table = new Table();
        table.center();
        table.setFillParent(true);
    }
    @Override
    public void show() {
        background = new Texture("gameScreen.png");
        popup = new Texture("pausePopup.png");
        sound = new Texture("sound.png");
        retry = new Texture("retry.png");
        cross = new Texture("cross.png");
        save = new Texture("slot4.png");
        saveHover = new Texture("slot4_hover.png");
        level = new Texture("levelSelection.png");

        Button.ButtonStyle saveStyle = new Button.ButtonStyle();
        saveStyle.up = new TextureRegionDrawable(save);
        saveStyle.over = new TextureRegionDrawable(saveHover);

        Button saveButton = new Button(saveStyle);
        Button crossButton = new Button(new TextureRegionDrawable(cross));
        Button soundButton = new Button(new TextureRegionDrawable(sound));
        Button retryLevel = new Button(new TextureRegionDrawable(retry));
        Button levelSelection = new Button(new TextureRegionDrawable(level));

        saveButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new LoadGameScreen(game));
                dispose();
            }
        });

        crossButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainGameScreen(game));
                dispose();
            }
        });

        soundButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //action according to it
            }
        });

        retryLevel.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainGameScreen(game));
                dispose();
            }
        });

        levelSelection.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new LevelSelectionScreen(game));
                dispose();
            }
        });

        table.add(crossButton).size(crossButton.getWidth()+20, crossButton.getHeight()+20).colspan(3).padLeft(680).row();
        table.add(saveButton).size(saveButton.getWidth(), saveButton.getHeight()).colspan(3).padTop(200).row();
        table.add(soundButton).size(soundButton.getWidth()+50, soundButton.getHeight()+50).padTop(75).padRight(-50);
        table.add(retryLevel).size(retryLevel.getWidth()+50, retryLevel.getHeight()+50).padTop(75).padRight(-50);
        table.add(levelSelection).size(levelSelection.getWidth()+50, levelSelection.getHeight()+50).padTop(75);
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
        game.batch.draw(background, 0, 0, worldWidth, worldHeight);

        float popupx = (worldWidth - popup.getWidth()) / 2;
        float popupy = (worldHeight - popup.getHeight()) / 2;
        game.batch.draw(popup, popupx-100, popupy-100, popup.getWidth()+200, popup.getHeight()+200);
        game.batch.end();
        stage.act(v);
        stage.draw();
    }

    @Override
    public void resize(int i, int i1) {

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
