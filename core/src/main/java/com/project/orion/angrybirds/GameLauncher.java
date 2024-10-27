package com.project.orion.angrybirds;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.project.orion.angrybirds.screens.*;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class GameLauncher extends Game {

    public SpriteBatch batch;
    public BitmapFont font;
    public FitViewport viewport;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        viewport = new FitViewport(1920,1080);
        this.setScreen(new StartupScreen(this));
//        this.setScreen(new MainMenuScreen(this));
//        this.setScreen(new LevelSelectionScreen(this));
//        this.setScreen(new LoadGameScreen(this));
//        this.setScreen(new WonScreen(this));
//        this.setScreen(new LoseScreen(this));
//        this.setScreen(new PauseScreen(this));
//        this.setScreen(new MainGameScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}

