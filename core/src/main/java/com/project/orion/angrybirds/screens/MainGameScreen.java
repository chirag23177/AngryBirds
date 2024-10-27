package com.project.orion.angrybirds.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.project.orion.angrybirds.GameLauncher;

public class MainGameScreen implements Screen {
    private GameLauncher game;
    private Texture background;
    private Texture slingshot;
    private Texture horizontal_bamboo;
    private Texture vertical_bamboo;
    private Texture bamboo_box;
    private Texture red_bird;
    private Texture mvng_chuck;
    private Texture bomb;
    private Texture minion_pig;
    private Music gameMusic;

    public MainGameScreen(GameLauncher game) {
        this.game = game;
    }
    @Override
    public void show() {
        game.introMusic.pause();
        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("game_theme.mp3"));
        gameMusic.setLooping(true);
        gameMusic.play();
        background = new Texture("game_background.png");
        slingshot = new Texture("slingshot.png");
        horizontal_bamboo = new Texture("horizontalBamboo.png");
        vertical_bamboo = new Texture("verticalBamboo.png");
        bamboo_box = new Texture("bambooBox.png");
        red_bird = new Texture("redBird.png");
        mvng_chuck = new Texture("chukMoving.png");
        bomb = new Texture("bomb.png");
        minion_pig = new Texture("minionPig.png");
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.viewport.apply();
        game.batch.setProjectionMatrix(game.viewport.getCamera().combined);
        float worldWidth = game.viewport.getWorldWidth();
        float worldHeight = game.viewport.getWorldHeight();


        game.batch.begin();
        game.batch.draw(background, 0, 0, worldWidth, worldHeight);

        game.batch.draw(slingshot, 200, 150, 100, slingshot.getHeight());

        float birdWidth = 70;
        float birdHeight = 70;
        float slingshotlaunchx = 210;
        float slingshotlaunchy = 300;
        game.batch.draw(red_bird, slingshotlaunchx, slingshotlaunchy, birdWidth, birdHeight);

        // Structure
        game.batch.draw(vertical_bamboo, 1600, 140, vertical_bamboo.getWidth(), vertical_bamboo.getHeight()-200);
        game.batch.draw(vertical_bamboo, 1300, 140, vertical_bamboo.getWidth(), vertical_bamboo.getHeight()-200);
        game.batch.draw(horizontal_bamboo, 1265, 290);
        game.batch.draw(bamboo_box, 1390, 320);
        game.batch.draw(minion_pig, 1460, 490, 80, 80);

        // Moving yellow bird
        game.batch.draw(mvng_chuck, 900, 600, birdWidth, birdHeight);

        // Black bird in queue
        game.batch.draw(bomb, 120, 150, birdWidth, birdHeight+10);
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
        background.dispose();
        slingshot.dispose();
        horizontal_bamboo.dispose();
        vertical_bamboo.dispose();
        bamboo_box.dispose();
        red_bird.dispose();
        mvng_chuck.dispose();
        bomb.dispose();
        minion_pig.dispose();
        gameMusic.dispose();
    }
}
