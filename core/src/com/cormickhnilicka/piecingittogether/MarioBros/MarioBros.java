package com.cormickhnilicka.piecingittogether.MarioBros;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cormickhnilicka.piecingittogether.MarioBros.Screens.PlayScreen;

public class MarioBros extends Game {
    public static final int V_WITDH = 400;    //virtual dimensions of the game
    public static final int V_HEIGHT = 208;
    public static final float PPM = 100;
    public SpriteBatch batch;  // thhis allows all screens to access it
    public static final short GROUND_BIT = 1;
    public static final short MARIO_BIT = 2;
    public static final short BRICK_BIT = 4;
    public static final short COIN_BIT = 8;
    public static final short DESTROYED_BIT = 16;
    public static final short OBJECT_BIT = 32;
    public static final short ENEMY_BIT = 64;
    public static final short ENEMY_HEAD_BIT = 128;
    public static final short ITEM_BIT = 256;

    public MarioBros() {
        batch = new SpriteBatch();
    }
    @Override
    public void create() {
        batch = new SpriteBatch();
//        setScreen(new PlayScreen(this));
    }
    @Override
    public void render () {
        super.render();
    }

    @Override
    public void dispose () {
//        manager.dispose();
        batch.dispose();
    }
}
