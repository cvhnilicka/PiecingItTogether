package com.cormickhnilicka.piecingittogether.Navigation.Views;

import com.badlogic.gdx.Screen;
import com.cormickhnilicka.piecingittogether.PiecingItTogether;

public class LoadingScreen implements Screen {
    private PiecingItTogether parent;

    public LoadingScreen(PiecingItTogether parent) {
        this.parent = parent;
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // its done loading, lets change the screen
        parent.changeScreen(PiecingItTogether.MENU);
    }

    @Override
    public void resize(int width, int height) {

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

    }
}
