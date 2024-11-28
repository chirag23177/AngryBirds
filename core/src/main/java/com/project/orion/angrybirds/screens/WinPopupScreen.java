package com.project.orion.angrybirds.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.project.orion.angrybirds.GameLauncher;

public class WinPopupScreen extends Stage {
    private GameLauncher game;
    private Texture popup;
    private Texture next;
    private Texture retry;
    private Texture level;
    private Table table;

    public WinPopupScreen(GameLauncher game) {
        this.game = game;
        table = new Table();
        table.center();
        table.setFillParent(true);

        popup = new Texture("Level1Won.png");
        next = new Texture("retry.png");
        retry = new Texture("nextLevel.png");
        level = new Texture("levelSelection.png");

        Button nextLevel = new Button(new TextureRegionDrawable(retry));
        Button retryLevel = new Button(new TextureRegionDrawable(next));
        Button levelSelection = new Button(new TextureRegionDrawable(level));

        nextLevel.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (game.getScreen() instanceof Level1GameScreen) {
                    game.setScreen(new Level2GameScreen(game));
                } else if (game.getScreen() instanceof Level2GameScreen) {
                    game.setScreen(new Level3GameScreen(game));
                } else if (game.getScreen() instanceof Level3GameScreen) {
                    game.setScreen(new Level1GameScreen(game));
                }
                dispose();
            }
        });

        retryLevel.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
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


        table.add(nextLevel).size(nextLevel.getWidth() + 20, nextLevel.getHeight() + 20).padTop(210).padRight(40).padLeft(50);
        table.add(retryLevel).size(retryLevel.getWidth() + 20, retryLevel.getHeight() + 20).padTop(210).padRight(40);
        table.add(levelSelection).size(levelSelection.getWidth() + 20, levelSelection.getHeight() + 20).padTop(210);

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
}
