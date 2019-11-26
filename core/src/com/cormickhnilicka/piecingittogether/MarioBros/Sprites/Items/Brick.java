package com.cormickhnilicka.piecingittogether.MarioBros.Sprites.Items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import com.cormickhnilicka.piecingittogether.MarioBros.MarioBros;
import com.cormickhnilicka.piecingittogether.MarioBros.Scenes.Hud;
import com.cormickhnilicka.piecingittogether.MarioBros.Screens.PlayScreen;
import com.cormickhnilicka.piecingittogether.MarioBros.Sprites.InteractiveTileObject;

public class Brick extends InteractiveTileObject {
    public Brick(PlayScreen screen, Rectangle bounds) {
        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter(MarioBros.BRICK_BIT);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Brick", "Collision");
        setCategoryFilter(MarioBros.DESTROYED_BIT);
        getCell().setTile(null);
        Hud.addScore(200);
//        MarioBros.manager.get("audio/sounds/breakblock.wav", Sound.class).play();
    }
}
