package com.project.orion.angrybirds.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
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
        System.out.println(Gdx.graphics.getWidth());
        System.out.println(Gdx.graphics.getHeight());
    }

    @Override
    public void show() {
        main_background = new Texture("lselectionbg.png");
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

        game.batch.begin();
        game.batch.draw(main_background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        float logo_x = (Gdx.graphics.getWidth() - logo_img.getWidth()) / 2;
        float logo_y = ((Gdx.graphics.getHeight() - logo_img.getHeight()) / 2 )+350;
        game.batch.draw(logo_img, logo_x, logo_y);

//        float x = (Gdx.graphics.getWidth() - level1.getWidth()) / 2;
//        float y = (Gdx.graphics.getHeight() - level1.getHeight()) / 2 + 100;
//
//        game.batch.draw(level1, x, y);
        float base_width = 200;
        float base_height = 150;

        float b1x = (Gdx.graphics.getWidth() - base_width) / 2 - 400;
        float b2x = (Gdx.graphics.getWidth() - base_width) / 2;
        float b3x = (Gdx.graphics.getWidth() - base_width) / 2 + 400;
        float by = (Gdx.graphics.getHeight() - base_height) / 2;


        game.batch.draw(levelCommon, b1x, by, base_width, base_height);
        game.batch.draw(levelCommon, b2x, by, base_width, base_height);
        game.batch.draw(levelCommon, b3x, by, base_width, base_height);

        float number_width = 100;
        float number_height = 100;
        float l1x = ((Gdx.graphics.getWidth() - base_width + number_width) / 2) - 395;
        float l1y = (Gdx.graphics.getHeight() - base_height) / 2 + 20;
        game.batch.draw(one, l1x, l1y, number_width, number_height);

        float l2x = ((Gdx.graphics.getWidth() - base_width + number_width) / 2) + 5;
        float l2y = (Gdx.graphics.getHeight() - base_height) / 2 + 20;
        game.batch.draw(two, l2x, l2y, number_width, number_height);

        float l3x = ((Gdx.graphics.getWidth() - base_width + number_width) / 2) + 405;
        float l3y = (Gdx.graphics.getHeight() - base_height) / 2 + 20;
        game.batch.draw(three, l3x, l3y, number_width, number_height);

        //selected level 1
        if (Gdx.input.getX() > l3x && Gdx.input.getX() < l3x + number_width && Gdx.input.getY() > l3y && Gdx.input.getY() < l3y + number_height) {
            game.batch.draw(acvthree, l3x, l3y, number_width, number_height);
        }
        else if (Gdx.input.getX() > l2x && Gdx.input.getX() < l2x + number_width && Gdx.input.getY() > l2y && Gdx.input.getY() < l2y + number_height) {
            game.batch.draw(acvtwo, l2x, l2y, number_width, number_height);
        }
        else if (Gdx.input.getX() > l1x && Gdx.input.getX() < l1x + number_width && Gdx.input.getY() > l1y && Gdx.input.getY() < l1y + number_height) {
            game.batch.draw(acvone, l1x, l1y, number_width, number_height);
        }
        game.batch.end();
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
