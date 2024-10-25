package com.project.orion.angrybirds.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.project.orion.angrybirds.GameLauncher;

public class LevelSelectionScreen implements Screen {
    private final GameLauncher game;
    private Texture main_background;
    private Texture logo_img;
    private Texture levelCommon;
    private Texture one;
    private Texture two;
    private Texture three;
    private Texture acvthree;
    private Texture acvtwo;
    private Texture acvone;

    public LevelSelectionScreen(GameLauncher game) {
        this.game = game;
    }

    @Override
    public void show() {
//        main_background = new Texture("lselectionbg.png");
        main_background = new Texture("main_background.png");
        logo_img = new Texture("logo.png");
        levelCommon = new Texture("LevelCommon.png");
        one = new Texture("one.png");
        two = new Texture("two.png");
        three = new Texture("three.png");
        acvthree = new Texture("acvthree.png");
        acvtwo = new Texture("acvtwo.png");
        acvone = new Texture("acvone.png");
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

        float logo_x = (worldWidth - logo_img.getWidth()) / 2;
        float logo_y = ((worldHeight - logo_img.getHeight()) / 2 )+350;
        game.batch.draw(logo_img, logo_x, logo_y);

        float base_width = 200;
        float base_height = 150;

        float b1x = (worldWidth - base_width) / 2 - 400;
        float b2x = (worldWidth - base_width) / 2;
        float b3x = (worldWidth - base_width) / 2 + 400;
        float by = (worldHeight - base_height) / 2;


        game.batch.draw(levelCommon, b1x, by, base_width, base_height);
        game.batch.draw(levelCommon, b2x, by, base_width, base_height);
        game.batch.draw(levelCommon, b3x, by, base_width, base_height);

        float number_width = 100;
        float number_height = 100;
        float l1x = ((worldWidth - base_width + number_width) / 2) - 395;
        float l1y = (worldHeight - base_height) / 2 + 20;
        game.batch.draw(one, l1x, l1y, number_width, number_height);

        float l2x = ((worldWidth - base_width + number_width) / 2) + 5;
        float l2y = (worldHeight - base_height) / 2 + 20;
        game.batch.draw(two, l2x, l2y, number_width, number_height);

        float l3x = ((worldWidth - base_width + number_width) / 2) + 405;
        float l3y = (worldHeight - base_height) / 2 + 20;
        game.batch.draw(three, l3x, l3y, number_width, number_height);

        Vector3 coordinates = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);

        game.viewport.getCamera().unproject(coordinates);

        float worldx = coordinates.x;
        float worldy = coordinates.y;

        if (worldx > l3x && worldx < l3x + number_width && worldy > l3y && worldy < l3y + number_height) {
            game.batch.draw(acvthree, l3x, l3y, number_width, number_height);
        }
        else if (worldx > l2x && worldx < l2x + number_width && worldy > l2y && worldy < l2y + number_height) {
            game.batch.draw(acvtwo, l2x, l2y, number_width, number_height);
        }
        else if (worldx > l1x && worldx < l1x + number_width && worldy > l1y && worldy < l1y + number_height) {
            game.batch.draw(acvone, l1x, l1y, number_width, number_height);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new MainMenuScreen(game));
            dispose();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            game.setScreen(new MainGameScreen(game));
            dispose();
        }
        game.batch.end();
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
