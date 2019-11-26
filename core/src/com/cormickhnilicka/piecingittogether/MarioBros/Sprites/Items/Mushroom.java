package com.cormickhnilicka.piecingittogether.MarioBros.Sprites.Items;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.cormickhnilicka.piecingittogether.MarioBros.MarioBros;
import com.cormickhnilicka.piecingittogether.MarioBros.Screens.PlayScreen;
import com.cormickhnilicka.piecingittogether.MarioBros.Sprites.Mario;

public class Mushroom extends Item {
    public Mushroom(PlayScreen screen, float x, float y) {
        super(screen, x, y);
        setRegion(screen.getAtlas().findRegion("mushroom"), 0,0,16,16);
        velocity = new Vector2(0.7f,0); // not moving for now
    }

    @Override
    public void defineItem() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(getX(), getY()); //tmp
        bdef.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(7/ MarioBros.PPM);
        // what is it?
        fdef.filter.categoryBits = MarioBros.ITEM_BIT;
        // what can it collide with?
        fdef.filter.maskBits = MarioBros.MARIO_BIT | MarioBros.OBJECT_BIT | MarioBros.GROUND_BIT |
                MarioBros.COIN_BIT | MarioBros.BRICK_BIT;

        fdef.shape = shape;
        body.createFixture(fdef).setUserData(this);
    }

    @Override
    public void use(Mario mario) {
        destroy();
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        setPosition(body.getPosition().x-getWidth()/2, body.getPosition().y-getHeight()/2);
        velocity.y = body.getLinearVelocity().y;
        body.setLinearVelocity(velocity);
    }
}

