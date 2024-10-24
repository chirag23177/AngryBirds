package com.project.orion.angrybirds.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.project.orion.angrybirds.GameLauncher;

public class MainMenuScreen implements Screen {
    private final GameLauncher game;
    private Texture main_background;
    private Texture logo_img;
    private Texture play_button;
    private Texture load_button;
    private Texture exit_button;

    public MainMenuScreen(GameLauncher game) {
        this.game = game;
    }

    @Override
    public void show() {
        main_background = new Texture("main_background.png");
        logo_img = new Texture("logo.png");
        play_button = new Texture("play_button.png");
        load_button = new Texture("load_button.png");
        exit_button = new Texture("exit_buttom.png");
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.viewport.apply();
        game.batch.setProjectionMatrix(game.viewport.getCamera().combined);
        game.batch.begin();
        game.batch.draw(main_background, 0, 0, game.viewport.getWorldWidth(), game.viewport.getWorldHeight());
        float logo_x = (Gdx.graphics.getWidth() - logo_img.getWidth()) / 2;
        float logo_y = ((Gdx.graphics.getHeight() - logo_img.getHeight()) / 2 )+350;
        game.batch.draw(logo_img, logo_x, logo_y);

        float play_x = (Gdx.graphics.getWidth() - play_button.getWidth()) / 2;
        float play_y = (Gdx.graphics.getHeight() - play_button.getHeight()) / 2 +100;
        game.batch.draw(play_button, play_x, play_y, play_button.getWidth()-70,play_button.getHeight()-70);

        float load_x = (Gdx.graphics.getWidth() - load_button.getWidth()) / 2;
        float load_y = (Gdx.graphics.getHeight() - load_button.getHeight()) / 2 - 50;
        game.batch.draw(load_button, load_x, load_y, load_button.getWidth()-70,load_button.getHeight()-70);

        float exit_x = (Gdx.graphics.getWidth() - exit_button.getWidth()) / 2;
        float exit_y = (Gdx.graphics.getHeight() - exit_button.getHeight()) / 2 - 200;
        game.batch.draw(exit_button, exit_x, exit_y, exit_button.getWidth()-70,exit_button.getHeight()-70);
        game.batch.end();

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
