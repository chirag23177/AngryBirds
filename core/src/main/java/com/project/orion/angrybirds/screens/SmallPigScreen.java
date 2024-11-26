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
import com.project.orion.angrybirds.classes.Ground;
import com.project.orion.angrybirds.classes.MediumPig;
import com.project.orion.angrybirds.classes.SmallPig;

public class SmallPigScreen implements Screen {
    private final GameLauncher game;
    private Texture background;
    private MediumPig smallPig;
    private World world;
    private final Stage stage;
    private Box2DDebugRenderer debugRenderer;
    private Ground ground;

    public SmallPigScreen(GameLauncher game) {
        this.game = game;
        stage = new Stage(game.viewport, game.batch);
    }

    @Override
    public void show() {
        background = new Texture("game_background.png");
        world = new World(new Vector2(0, -9.81f), true);
        ground = new Ground(world, 140); // Adjust the groundY value as needed
        smallPig = new MediumPig(world, 500, 400); // Ensure the pig is above the ground
        debugRenderer = new Box2DDebugRenderer();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.step(1/60f, 6, 2);

        game.viewport.apply();
        game.batch.setProjectionMatrix(game.viewport.getCamera().combined);

        game.batch.begin();
        game.batch.draw(background, 0, 0, game.viewport.getWorldWidth(), game.viewport.getWorldHeight());
        smallPig.render(game.batch);
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
        smallPig.dispose();
        ground.dispose();
        world.dispose();
        stage.dispose();
        debugRenderer.dispose();
    }
}
