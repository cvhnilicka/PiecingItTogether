package com.cormickhnilicka.piecingittogether.MarioBros.Sprites.Items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.cormickhnilicka.piecingittogether.MarioBros.MarioBros;
import com.cormickhnilicka.piecingittogether.MarioBros.Scenes.Hud;
import com.cormickhnilicka.piecingittogether.MarioBros.Screens.PlayScreen;
import com.cormickhnilicka.piecingittogether.MarioBros.Sprites.InteractiveTileObject;

public class Coin extends InteractiveTileObject {
    private static TiledMapTileSet tileSet;
    private final int BLANK_COIN = 28;
    public Coin(PlayScreen screen, Rectangle bounds) {
        super(screen, bounds);
        tileSet = map.getTileSets().getTileSet("tileset_gutter");
        fixture.setUserData(this);
        setCategoryFilter(MarioBros.COIN_BIT);
    }

    @Override
    public void onHeadHit() {

        Gdx.app.log("Coin", "Collision");
        if(getCell().getTile().getId() == BLANK_COIN)
            Gdx.app.log("Coin", "Collision");
//            MarioBros.manager.get("audio/sounds/bump.wav", Sound.class).play();
        else {
//            MarioBros.manager.get("audio/sounds/coin.wav", Sound.class).play();
            screen.spawnItem(new ItemDef(new Vector2(body.getPosition().x, body.getPosition().y + 16 / MarioBros.PPM), Mushroom.class));
        }
        getCell().setTile(tileSet.getTile(BLANK_COIN));
        Hud.addScore(400);
    }
}
