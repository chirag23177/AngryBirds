package com.project.orion.angrybirds;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import javax.swing.text.Utilities;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class GameLauncher extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;
    private Texture image2;
    private Texture image3;
    private float x;
    private float y;

    @Override
    public void create() {
//        System.out.println(System.getProperty("user.home"));
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");
//        image2 = new Texture("ss.png");
//        image3 = new Texture(Gdx.files.external("ss2.png"));
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
//        batch.draw(image2,0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(image, x, y);

        // Logic to react on user input
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            y = y+3;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            y = y-3;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            x = x-3;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            x = x+3;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            x = 0;
            y = 0;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            x = Gdx.graphics.getWidth() - image.getWidth();
            y = Gdx.graphics.getHeight() - image.getHeight();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.BACKSPACE)) {
            x = 0;
            y = Gdx.graphics.getHeight() - image.getHeight();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.TAB)) {
            x = Gdx.graphics.getWidth() - image.getWidth();
            y = 0;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.F1)) {
            x = Gdx.graphics.getWidth()/2 - image.getWidth()/2;
            y = Gdx.graphics.getHeight()/2 - image.getHeight()/2;
        }
//        if (Gdx.input.isTouched()){
//            x = Gdx.input.getX() - image.getWidth()/2;
//            y = Gdx.graphics.getHeight() - Gdx.input.getY() - image.getHeight()/2;
//        }
//        if (Gdx.input.isTouched()){
//            x = Gdx.input.getX() - image.getWidth()/2;
//            y = Gdx.graphics.getHeight() - Gdx.input.getY() - image.getHeight()/2;
//        }
        if ((Gdx.input.getX() > x && Gdx.input.getX() < x + image.getWidth()) && (Gdx.graphics.getHeight() - Gdx.input.getY() > y && Gdx.graphics.getHeight() - Gdx.input.getY() < y + image.getHeight())){
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
                x = Gdx.input.getX() - image.getWidth()/2;
                y = Gdx.graphics.getHeight() - Gdx.input.getY() - image.getHeight()/2;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }


//        batch.draw(image3, 0, 0);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
//        image2.dispose();
//        image3.dispose();
    }

    @Override
    public void resize(int width, int height){
        batch.getProjectionMatrix().setToOrtho2D(0, 0, width, height);
    }
}
