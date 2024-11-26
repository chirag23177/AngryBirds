package com.project.orion.angrybirds.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.project.orion.angrybirds.GameLauncher;

public class LoadGameScreen implements Screen {
    private final GameLauncher game;
    private Texture background;
    private Texture logo_img;
    private Texture loadSlot1;
    private Texture loadSlot2;
    private Texture loadSlot3;
    private Texture loadSlot4;
    private Texture loadSlotHover1;
    private Texture loadSlotHover2;
    private Texture loadSlotHover3;
    private Texture loadSlotHover4;
    private Texture backButtonTexture;
    private final Stage stage;
    private final Table table;
    Button loadSlot1Button;
    Button loadSlot2Button;
    Button loadSlot3Button;
    Button loadSlot4Button;
    Button backButton;


    public LoadGameScreen(GameLauncher game) {
        this.game = game;
        stage = new Stage(game.viewport, game.batch);
        table = new Table();
        table.center();
        table.setFillParent(true);
    }

    @Override
    public void show() {
        background = new Texture("main_background.png");
        logo_img = new Texture("logo.png");
        loadSlot1 = new Texture("slot1.png");
        loadSlot2 = new Texture("slot2.png");
        loadSlot3 = new Texture("slot3.png");
        loadSlot4 = new Texture("slot4.png");
        loadSlotHover1 = new Texture("slot1_hover.png");
        loadSlotHover2 = new Texture("slot2_hover.png");
        loadSlotHover3 = new Texture("slot3_hover.png");
        loadSlotHover4 = new Texture("slot4_hover.png");
        backButtonTexture = new Texture("back_button.png");

        // Implementing the hover button effect
        Button.ButtonStyle loadSlot1Style = new Button.ButtonStyle();
        loadSlot1Style.up = new TextureRegionDrawable(loadSlot1);
        loadSlot1Style.over = new TextureRegionDrawable(loadSlotHover1);

        Button.ButtonStyle loadSlot2Style = new Button.ButtonStyle();
        loadSlot2Style.up = new TextureRegionDrawable(loadSlot2);
        loadSlot2Style.over = new TextureRegionDrawable(loadSlotHover2);

        Button.ButtonStyle loadSlot3Style = new Button.ButtonStyle();
        loadSlot3Style.up = new TextureRegionDrawable(loadSlot3);
        loadSlot3Style.over = new TextureRegionDrawable(loadSlotHover3);

        Button.ButtonStyle loadSlot4Style = new Button.ButtonStyle();
        loadSlot4Style.up = new TextureRegionDrawable(loadSlot4);
        loadSlot4Style.over = new TextureRegionDrawable(loadSlotHover4);

        Button.ButtonStyle backButtonStyle = new Button.ButtonStyle();
        backButtonStyle.up = new TextureRegionDrawable(backButtonTexture);

        loadSlot1Button = new Button(loadSlot1Style);
        loadSlot2Button = new Button(loadSlot2Style);
        loadSlot3Button = new Button(loadSlot3Style);
        loadSlot4Button = new Button(loadSlot4Style);
        backButton = new Button(backButtonStyle);

        // Making the buttons clickable
        loadSlot1Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Load the game from slot 1
            }
        });

        loadSlot2Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Load the game from slot 2
            }
        });

        loadSlot3Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Load the game from slot 3
            }
        });

        loadSlot4Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Load the game from slot 4
            }
        });

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
                dispose();
            }
        });

        Image logoImage = new Image(new TextureRegionDrawable(logo_img));
        table.add(backButton).size(backButton.getWidth()+10,backButton.getHeight()+10).top().left().pad(30).expandX().colspan(2).padTop(-200).row();
        table.add(logoImage).colspan(2).padBottom(60).padTop(-90).row();
        table.add(loadSlot1Button).padBottom(50).padRight(-460);
        table.add(loadSlot2Button).padBottom(50).padLeft(-460).row();
        table.add(loadSlot3Button).padBottom(50).padRight(-460);
        table.add(loadSlot4Button).padBottom(50).padLeft(-460);

        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.viewport.apply();
        game.batch.setProjectionMatrix(game.viewport.getCamera().combined);
        game.batch.begin();
        game.batch.draw(background, 0, 0, game.viewport.getWorldWidth(), game.viewport.getWorldHeight());
        game.batch.end();
        stage.act(v);
        stage.draw();
    }

    @Override
    public void resize(int i, int i1) {
        game.viewport.update(i, i1);
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
        logo_img.dispose();
        loadSlot1.dispose();
        loadSlot2.dispose();
        loadSlot3.dispose();
        loadSlot4.dispose();
        loadSlotHover1.dispose();
        loadSlotHover2.dispose();
        loadSlotHover3.dispose();
        loadSlotHover4.dispose();
        stage.dispose();
    }
}
