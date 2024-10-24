package com.project.orion.angrybirds.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.project.orion.angrybirds.GameLauncher;

public class StartupScreen implements Screen {
    private final GameLauncher game;
    private Texture startup_img;
//    private Texture logo_img;
    private float timer;


    public StartupScreen(GameLauncher game) {
        this.game = game;
    }


    @Override
    public void show() {
        startup_img = new Texture("startup_image.png");
        timer = 0;
//        startup_img = new Texture("img.png");
//        logo_img = new Texture("logo.png");
    }

    @Override
    public void render(float v) {
        timer += v;
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.viewport.apply();
        game.batch.setProjectionMatrix(game.viewport.getCamera().combined);
        game.batch.begin();
        game.batch.draw(startup_img, 0, 0, game.viewport.getWorldWidth(), game.viewport.getWorldHeight());
        game.batch.end();

        if (timer > 2){
            game.setScreen(new MainMenuScreen(game));
            dispose();
        }

    }

    @Override
    public void resize(int width, int height) {
        game.viewport.update(width, height, true);
    }
//    @Override
//    public void resize(int i, int i1) {
//        game.batch.getProjectionMatrix().setToOrtho2D(0, 0, i, i1);
//    }

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
