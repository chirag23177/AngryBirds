package com.project.orion.angrybirds.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.project.orion.angrybirds.GameLauncher;
import com.project.orion.angrybirds.classes.*;

public class Level1GameScreen implements Screen {
    private final GameLauncher game;
    private Texture background;
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private Stage stage;
    private Structure1 structure;
    private Bird redBird;

    public Level1GameScreen(GameLauncher game) {
        this.game = game;
        stage = new Stage(game.viewport, game.batch);
    }

    @Override
    public void show() {
        background = new Texture("game_background.png");
        world = new World(new Vector2(0, -9.81f), true);
        debugRenderer = new Box2DDebugRenderer();
        structure = new Structure1(world);
        redBird = new RedBird(world, 210, 300);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.step(1/60f, 6, 2);

        game.viewport.apply();
        game.batch.setProjectionMatrix(game.viewport.getCamera().combined);

        game.batch.begin();
        game.batch.draw(background, 0, 0, game.viewport.getWorldWidth(), game.viewport.getWorldHeight());
        structure.render(game.batch);
        redBird.render(game.batch);
        game.batch.end();

        debugRenderer.render(world, game.viewport.getCamera().combined);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        game.viewport.update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        background.dispose();
        structure.dispose();
        redBird.dispose();
        world.dispose();
        stage.dispose();
        debugRenderer.dispose();
    }
}
