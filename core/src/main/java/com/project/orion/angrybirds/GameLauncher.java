package com.project.orion.angrybirds;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.project.orion.angrybirds.screens.*;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */

public class GameLauncher extends Game {

    public SpriteBatch batch;
    public FitViewport viewport;
    public Music introMusic;

    @Override
    public void create() {
        batch = new SpriteBatch();
        viewport = new FitViewport(1920,1080);
        introMusic = Gdx.audio.newMusic(Gdx.files.internal("game_intro.mp3"));
        introMusic.setLooping(true);
        introMusic.play();
//        this.setScreen(new StartupScreen(this));
//        this.setScreen(new MainMenuScreen(this));
//        this.setScreen(new LevelSelectionScreen(this));
//        this.setScreen(new LoadGameScreen(this));
//        this.setScreen(new WonScreen(this));
//        this.setScreen(new LoseScreen(this));
//        this.setScreen(new PauseScreen(this));
        this.setScreen(new MainGameScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        introMusic.dispose();
    }
}

