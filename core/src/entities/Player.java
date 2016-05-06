package entities;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.graphics.Texture;
import tools.WorldFactory;

/**
 *
 * Created by Hongyu Wang on 5/5/2016.
 */
public class Player extends GameEntity {
    private Texture texture;
    private Sprite sprite;
    private Body body;
    private World world;


    public Player(){
        world = WorldFactory.getWorld();


        initSprite();

        initBox2D();
    }

    //----------Init methods -------------------------

    private void initSprite(){
        texture = new Texture("badlogic.jpg");
        sprite = new Sprite(texture);
        setBounds(50, 50, 100, 100);
        sprite.setSize(100, 100);
        sprite.setOrigin(getOriginX(), getOriginY());
    }

    private void initBox2D(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(getX(), getY());
        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(sprite.getWidth() / 2, sprite.getHeight() / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1;


        body.createFixture(fixtureDef);

        shape.dispose();
    }


    //------------EVENT HANDLERS ------------------------------------------------------
    @Override
    public boolean fire(Event event) {

        return event instanceof InputEvent && handleInput((InputEvent)event);
    }

    private boolean handleInput(InputEvent event) {
        if (event.getType() != InputEvent.Type.keyDown)
            return false;



        return true;
    }
    //---------------------------------------------------------------------------------


    //-------- Your Update Loops ------------------------------------------------------
    @Override
    public void act(float delta) {
        super.act(delta);
        sprite.setX(getX());
        sprite.setY(getY());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        sprite.draw(batch, parentAlpha);
    }

    //------------------------------------------------------------------------------------

    @Override
    public boolean remove() {
        texture.dispose();
        return super.remove();
    }
}

