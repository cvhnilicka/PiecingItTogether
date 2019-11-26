package com.cormickhnilicka.piecingittogether.Navigation.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.cormickhnilicka.piecingittogether.PiecingItTogether;

import sun.rmi.runtime.Log;

public class MenuScreen implements Screen {
    private PiecingItTogether parent;
    private Stage stage;
    private Skin skin;


    public MenuScreen(PiecingItTogether parent) {
        this.parent = parent;
        stage = new Stage(new ScreenViewport());
        // send any input from the user to this stage so it can respond



    }
    @Override
    public void show() {
        // WHAT IS SEEN.
        // i.e. build the table you want to be displayed
        Gdx.input.setInputProcessor(stage);



        // Create a table that fills the screen. Everything else will go inside this table.
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        stage.addActor(table);

        skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

        // create the buttons to be used
        TextButton newGame = new TextButton("New Game", skin);
        TextButton exit = new TextButton("Exit Game", skin);


        // Add the buttons to the table
        table.add(newGame).fillX().uniform();
        table.row().pad(10,0,10,0);
        table.add(exit).fillX().uniform();
        // lets add some listeners to the buttons to make them do stuff


        // make exit exit
        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
        // make newgame nav to the game
        newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(PiecingItTogether.APPLICATION);
//                parent.changeScreen(PiecingItTogether.APPLICATION);
            }
        });

    }

    @Override
    public void render(float delta) {
        // first, we should clear the image before drawing the next one,
        // this is to prevent 'flickering' or 'ghosting'
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // Actually render the scene you described in the show() method above.
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        // update the stage (scene) described above to fit the new resized window
        stage.getViewport().update(width,height, true);

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
        // dispose of all the things

        stage.dispose();
    }
}
