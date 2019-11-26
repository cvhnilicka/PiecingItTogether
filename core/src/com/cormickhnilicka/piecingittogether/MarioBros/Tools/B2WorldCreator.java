package com.cormickhnilicka.piecingittogether.MarioBros.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.cormickhnilicka.piecingittogether.MarioBros.MarioBros;
import com.cormickhnilicka.piecingittogether.MarioBros.Screens.PlayScreen;
import com.cormickhnilicka.piecingittogether.MarioBros.Sprites.Enemys.Goomba;
import com.cormickhnilicka.piecingittogether.MarioBros.Sprites.Items.Brick;
import com.cormickhnilicka.piecingittogether.MarioBros.Sprites.Items.Coin;

public class B2WorldCreator {


    private Array<Goomba> goombas;
    public B2WorldCreator(PlayScreen screen) {

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;
        World world = screen.getWorld();
        TiledMap map = screen.getMap();


        // create ground bodies/fixtures
        for (MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX()+rect.getWidth()/2)/ MarioBros.PPM, (rect.getY()+rect.getHeight()/2)/MarioBros.PPM);

            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth()/2/MarioBros.PPM, rect.getHeight()/2/MarioBros.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);

        }

        // create pipe bodies/fixtures
        for (MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX()+rect.getWidth()/2)/ MarioBros.PPM, (rect.getY()+rect.getHeight()/2)/MarioBros.PPM);

            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth()/2/MarioBros.PPM, rect.getHeight()/2/MarioBros.PPM);
            fdef.shape = shape;
            fdef.filter.categoryBits = MarioBros.OBJECT_BIT;  // when an enemy hits a pip, he can turn around
            body.createFixture(fdef);

        }

        // create brick bodies/fixtures
        for (MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Coin(screen, rect);

        }

        // create brick bodies/fixtures
        for (MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Brick(screen, rect);

        }
        // create all goombas
        goombas = new Array<Goomba>();
        for (MapObject object : map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            goombas.add(new Goomba(screen, rect.getX()/MarioBros.PPM, rect.getY()/MarioBros.PPM));

        }
    }
    public Array<Goomba> getGoombas() {
        return goombas;
    }
}

