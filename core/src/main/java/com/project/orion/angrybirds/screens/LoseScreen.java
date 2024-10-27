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

public class LoseScreen implements Screen {
    private GameLauncher game;
    private Texture background;
    private Texture popup;
    private Texture retry;
    private Texture level;
    private Stage stage;
    private Table table;

    public LoseScreen(GameLauncher game) {
        this.game = game;
        stage = new Stage(game.viewport, game.batch);
        table = new Table();
        table.center();
        table.setFillParent(true);
    }
    @Override
    public void show() {
        background = new Texture("gameScreen.png");
        popup = new Texture("Level1Lose.png");
        retry = new Texture("retry.png");
        level = new Texture("levelSelection.png");

        Button retryLevel = new Button(new TextureRegionDrawable(retry));
        Button levelSelection = new Button(new TextureRegionDrawable(level));

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

        table.add(retryLevel).size(retryLevel.getWidth()+50, retryLevel.getHeight()+50).padTop(500).padRight(140);
        table.add(levelSelection).size(levelSelection.getWidth()+50, levelSelection.getHeight()+50).padTop(500);
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
