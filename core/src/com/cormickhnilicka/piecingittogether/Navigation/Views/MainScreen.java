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

public class MainScreen implements Screen {
    private PiecingItTogether parent;
    private Stage stage;
    private Skin skin;

    public MainScreen(PiecingItTogether parent) {
        this.parent = parent;
        stage = new Stage(new ScreenViewport());
    }
    @Override
    public void show() {
        // lets make something to show the levels

        Gdx.input.setInputProcessor(stage);

        // Create a table that fills the screen. Everything else will go inside this table.
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        stage.addActor(table);

        skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

        // create the button for level 1
        TextButton level1 = new TextButton("Level 1", skin);

        // add it to the table
        table.add(level1).fillX().uniform();
        table.row().pad(10,0,10,0);

        // create the button for level 1
        TextButton level2 = new TextButton("Level 2", skin);

        // add it to the table
        table.add(level2).fillX().uniform();
        table.row().pad(10,0,10,0);

        // create the button for level 1
        TextButton level3 = new TextButton("Level 3", skin);

        // add it to the table
        table.add(level3).fillX().uniform();
        table.row().pad(10,0,10,0);


        level1.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.setLevel(0);
            }
        });

        level2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.setLevel(1);
            }
        });

        level3.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.setLevel(2);
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
        stage.dispose();

    }
}
