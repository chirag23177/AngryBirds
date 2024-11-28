package com.project.orion.angrybirds.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.project.orion.angrybirds.GameLauncher;

public class PausePopupScreen extends Stage {
    private GameLauncher game;
    private Stage parentStage;
    private Texture popup;
    private Texture sound;
    private Texture retry;
    private Texture level;
    private Texture cross;
    private Texture save;
    private Texture saveHover;
    private Table table;

    public PausePopupScreen(GameLauncher game, Stage parentStage) {
        this.game = game;
        this.parentStage = parentStage;
        table = new Table();
        table.center();
        table.setFillParent(true);

        popup = new Texture("pausePopup.png");
        sound = new Texture("sound.png");
        retry = new Texture("retry.png");
        cross = new Texture("cross.png");
        save = new Texture("savegamebutton.png");
        saveHover = new Texture("savegamehoverbutton.png");
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

//        crossButton.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
////                game.setScreen(game.getScreen());
////                parentStage.getActors().removeValue(PausePopupScreen.this, true);
////                clear();
//                dispose();
////                Gdx.input.setInputProcessor(parentStage);
//            }
//        });

        crossButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Explicitly set input processor back to the parent stage
                ((Level1GameScreen)game.getScreen()).setPausePopupScreen();
                Gdx.input.setInputProcessor(parentStage);

                // Reset game state
                ((Level1GameScreen)game.getScreen()).setGameEnded(false);

                // Dispose of textures to prevent memory leaks
                dispose();
//                clear();
            }
        });

        soundButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // action according to it
            }
        });

        retryLevel.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.getScreen().dispose();
                if (game.getScreen() instanceof Level1GameScreen) {
                    game.setScreen(new Level1GameScreen(game));
                } else if (game.getScreen() instanceof Level2GameScreen) {
                    game.setScreen(new Level2GameScreen(game));
                } else if (game.getScreen() instanceof Level3GameScreen) {
                    game.setScreen(new Level3GameScreen(game));
                }
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

        table.add(crossButton).size(crossButton.getWidth(), crossButton.getHeight()).colspan(3).padLeft(470).row();
        table.add(saveButton).size(saveButton.getWidth()-30, saveButton.getHeight()-30).colspan(3).padTop(70).padLeft(60).row();
        table.add(soundButton).size(soundButton.getWidth() + 20, soundButton.getHeight() + 20).padTop(20).padLeft(50).padBottom(50);
        table.add(retryLevel).size(retryLevel.getWidth() + 20, retryLevel.getHeight() + 20).padTop(20).padBottom(50);
        table.add(levelSelection).size(levelSelection.getWidth() + 20, levelSelection.getHeight() + 20).padTop(20).padBottom(50);

        addActor(table);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void draw() {
        getBatch().begin();
        float popupx = (getViewport().getWorldWidth() - popup.getWidth()) / 2;
        float popupy = (getViewport().getWorldHeight() - popup.getHeight()) / 2;
        getBatch().draw(popup, popupx + 80, popupy + 80, popup.getWidth() * 0.8f, popup.getHeight() * 0.8f);
        getBatch().end();

        super.draw();
    }

    @Override
    public void dispose() {
        // Dispose of textures
        if (popup != null) popup.dispose();
        if (sound != null) sound.dispose();
        if (retry != null) retry.dispose();
        if (level != null) level.dispose();
        if (cross != null) cross.dispose();
        if (save != null) save.dispose();
        if (saveHover != null) saveHover.dispose();

        // Clear the stage
        clear();
    }
}
