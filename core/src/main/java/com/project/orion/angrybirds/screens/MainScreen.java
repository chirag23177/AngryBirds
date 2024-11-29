package com.project.orion.angrybirds.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainScreen implements Screen {
    private Stage stage;
    private Group popupGroup;
    private TextButton showPopupButton;
    private TextButton closeButton;

    public MainScreen() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        showPopupButton = new TextButton("Show Pop-Up", new Skin(Gdx.files.internal("slot4.png")));
        showPopupButton.setPosition(100, 100);
        showPopupButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                popupGroup.setVisible(true);
            }
        });
        stage.addActor(showPopupButton);

        popupGroup = new Group();
        popupGroup.setVisible(false); // Start hidden

        // Add a semi-transparent background for the pop-up effect
        Image bg = new Image(new Texture("popup_bg.png"));
        bg.setColor(0, 0, 0, 0.7f); // Slightly transparent
        popupGroup.addActor(bg);

        // Add a close button to the pop-up
        closeButton = new TextButton("Close", new Skin(Gdx.files.internal("slot4.png")));
        closeButton.setPosition(150, 150);
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                popupGroup.setVisible(false); // Hide the pop-up
            }
        });
        popupGroup.addActor(closeButton);

        // Position the pop-up in the center of the screen
        popupGroup.setPosition(
            (Gdx.graphics.getWidth() - bg.getWidth()) / 2,
            (Gdx.graphics.getHeight() - bg.getHeight()) / 2
        );

        // Add the pop-up group to the stage
        stage.addActor(popupGroup);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // Clear screen and draw stage
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
        stage.dispose();
    }
    // Other required Screen methods (show, hide, pause, resume) would go here
}
