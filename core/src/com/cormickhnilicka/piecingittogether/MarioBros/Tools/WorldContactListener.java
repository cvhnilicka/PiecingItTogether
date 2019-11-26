package com.cormickhnilicka.piecingittogether.MarioBros.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.cormickhnilicka.piecingittogether.MarioBros.MarioBros;
import com.cormickhnilicka.piecingittogether.MarioBros.Sprites.Enemys.Enemy;
import com.cormickhnilicka.piecingittogether.MarioBros.Sprites.InteractiveTileObject;
import com.cormickhnilicka.piecingittogether.MarioBros.Sprites.Items.Item;
import com.cormickhnilicka.piecingittogether.MarioBros.Sprites.Mario;

// Contact listeners are used/called when two fixtures in box2d collide with each other
public class WorldContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        // begin to collide
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;

        if (fixA.getUserData() == "head" || fixB.getUserData() == "head") {
            // collision has occured with marios head
            Fixture head = fixA.getUserData() == "head" ? fixA : fixB;
            Fixture object = head == fixA ? fixB : fixA;

            // returns true if the object is an interactive tile object
            if (object.getUserData() != null && InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())){
                ((InteractiveTileObject) object.getUserData()).onHeadHit();
            }
        }
        // switch between collision types
        switch(cDef) {
            case MarioBros.ENEMY_HEAD_BIT | MarioBros.MARIO_BIT:
                if (fixA.getFilterData().categoryBits == MarioBros.ENEMY_HEAD_BIT)
                    ((Enemy)fixA.getUserData()).hitOnHead();
                else
                    ((Enemy)fixB.getUserData()).hitOnHead();
                break;
            case MarioBros.ENEMY_BIT | MarioBros.OBJECT_BIT:
                if (fixA.getFilterData().categoryBits == MarioBros.ENEMY_BIT)
                    ((Enemy)fixA.getUserData()).reverseVelocity(true, false);
                else
                    ((Enemy)fixB.getUserData()).reverseVelocity(true, false);
                break;
            case MarioBros.MARIO_BIT | MarioBros.ENEMY_BIT:
                Gdx.app.log("Mario","Died");
                break;
            case MarioBros.ENEMY_BIT | MarioBros.ENEMY_BIT:
                ((Enemy)fixA.getUserData()).reverseVelocity(true, false);
                ((Enemy)fixB.getUserData()).reverseVelocity(true, false);
                break;
            case MarioBros.ITEM_BIT | MarioBros.OBJECT_BIT:
                if (fixA.getFilterData().categoryBits == MarioBros.ITEM_BIT)
                    ((Item)fixA.getUserData()).reverseVelocity(true, false);
                else
                    ((Item)fixB.getUserData()).reverseVelocity(true, false);
                break;
            case MarioBros.ITEM_BIT | MarioBros.MARIO_BIT:
                if (fixA.getFilterData().categoryBits == MarioBros.ITEM_BIT)
                    ((Item)fixA.getUserData()).use((Mario)fixB.getUserData());
                else
                    ((Item)fixB.getUserData()).use((Mario)fixA.getUserData());
                break;

        }
    }

    @Override
    public void endContact(Contact contact) {
        // two fixtures disconnect from each other
        Gdx.app.log("End Contact","");
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        // once something has collided, you have the opportunity to change the characteristics of the collision
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        // gives the results of what happens after the collision
    }
}

