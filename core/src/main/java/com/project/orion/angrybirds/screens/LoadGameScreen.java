package com.project.orion.angrybirds.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.project.orion.angrybirds.GameLauncher;

public class LoadGameScreen implements Screen {
    private final GameLauncher game;
    Texture background;

    public LoadGameScreen(GameLauncher game) {
        this.game = game;
    }

    @Override
    public void show() {
        background = new Texture("main_background.png");
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(background, 0, 0, 1920, 1080);
        game.batch.end();
    }

    @Override
    public void resize(int i, int i1) {
        game.viewport.update(i, i1);
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
