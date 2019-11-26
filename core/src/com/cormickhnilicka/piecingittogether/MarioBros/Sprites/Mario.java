package com.cormickhnilicka.piecingittogether.MarioBros.Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.cormickhnilicka.piecingittogether.MarioBros.MarioBros;
import com.cormickhnilicka.piecingittogether.MarioBros.Screens.PlayScreen;

public class Mario extends Sprite {
    public enum State { FALLING, JUMPING, STANDING, RUNNING };
    public State currentState;
    public State previousState;

    public World world; // the world he will live in
    public Body b2body;
    private TextureRegion marioStand;
    private Animation marioRun;
    private Animation marioJump;
    private boolean runningRight;
    private float stateTimer;

    public Mario(PlayScreen screen) {
        super(screen.getAtlas().findRegion("little_mario"));
        this.world = screen.getWorld();
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;

        Array<TextureRegion> frames = new Array<TextureRegion>();
        // little mario run animation
        for (int i = 1; i < 4; i++) {
            frames.add(new TextureRegion(screen.getAtlas().findRegion("little_mario"), i * 16, 0, 16, 16));
//            frames.add(new TextureRegion(getTexture(), i * 16, 10, 16, 16)); // had to change the y offest to 11
        }
        marioRun = new Animation(0.1f, frames);
        frames.clear();
        // end little mario run



        // little mario jump animation
        for (int i = 4; i < 6; i++)
            frames.add(new TextureRegion(screen.getAtlas().findRegion("little_mario"), i * 16, 0, 16, 16));
        marioJump = new Animation(0.1f, frames);
        // end little mario jump animation
//        frames.clear();

        marioStand = new TextureRegion(screen.getAtlas().findRegion("little_mario"), 0,0,16,16);
        defineMario();
        setBounds(0,0,16/MarioBros.PPM,16/MarioBros.PPM);
        setRegion(marioStand);
    }

    public void defineMario(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(32/ MarioBros.PPM,32/MarioBros.PPM); //tmp
        bdef.type = BodyDef.BodyType.DynamicBody;

        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(7/MarioBros.PPM);
        fdef.filter.categoryBits = MarioBros.MARIO_BIT;
        fdef.filter.maskBits = MarioBros.GROUND_BIT |
                MarioBros.COIN_BIT |
                MarioBros.BRICK_BIT |
                MarioBros.ENEMY_BIT |
                MarioBros.OBJECT_BIT |
                MarioBros.ENEMY_HEAD_BIT |
                MarioBros.ITEM_BIT;

        fdef.shape = shape;
        b2body.createFixture(fdef);

        //create a sensor on marios head
        EdgeShape head = new EdgeShape(); // basically a line between 2 points
        head.set(new Vector2(-2/ MarioBros.PPM, 6/MarioBros.PPM), new Vector2(2/MarioBros.PPM, 6/MarioBros.PPM)); // we are basically defining this line around/above marios head
        fdef.shape = head;
        fdef.isSensor = true; // it will not collide with anything when it is a sensor
        b2body.createFixture(fdef).setUserData("head");


    }

    public State getState() {
        if (b2body.getLinearVelocity().y > 0 || (b2body.getLinearVelocity().y < 0 && previousState == State.JUMPING))
            return State.JUMPING;
        else if (b2body.getLinearVelocity().y < 0)
            return State.FALLING;
        else if (b2body.getLinearVelocity().x != 0)
            return State.RUNNING;
        else
            return State.STANDING;
    }

    public TextureRegion getFrame(float dt) {
        currentState = getState();
        TextureRegion region;
        switch (currentState) {
            case JUMPING:
                region = (TextureRegion)marioJump.getKeyFrame(stateTimer);
                break;
            case RUNNING:
                region = (TextureRegion)marioRun.getKeyFrame(stateTimer, true);
                break;
            case FALLING:
            case STANDING:
            default:
                region = marioStand;
                break;
        }
        if((b2body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()){
            region.flip(true,false);
            runningRight = false;
        } else if ((b2body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()) {
            region.flip(true, false);
            runningRight = true;
        }

        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;
        return region;
    }

    public void update(float dt) {
        setPosition(b2body.getPosition().x-getWidth()/2, b2body.getPosition().y-getHeight()/2);
        setRegion(getFrame(dt));
    }
}
