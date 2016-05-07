package entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import tools.WorldFactory;

/**
 *
 * Created by Hongyu Wang on 5/5/2016.
 */
public abstract class GameEntity extends Actor {
    /**
     * This is the current sprite of the actor
     */
    protected Sprite sprite;

    protected Vector2 travelVector;

    protected Body body;

    protected Array<Disposable> disposables;


    /**
     * This is the assets of the array that we're using.
     */
    protected Array<? extends TextureRegion> assets;


    protected World world;


    public GameEntity(String spriteFilePath){
        this(spriteFilePath, 0, 0, 100, 100);
    }


    public GameEntity(String spriteFilePath, float x, float y, float width, float height){

        if (width == 0 || height == 0)
            throw new IllegalStateException("Game Entity Dimensions Cannot Be Zero");

        setBounds(x, y, width, height);

        init();

        initSprite(spriteFilePath);
    }

    protected void init(){
        assets = new Array<>();
        world = WorldFactory.getWorld();
        disposables = new Array<>();
        travelVector = new Vector2(0, 0);
        initBox2D();


    }

    private void initSprite(String spriteFilePath){
        Texture texture = new Texture(spriteFilePath);
        disposables.add(texture);
        sprite = new Sprite(texture);
        sprite.setBounds(getX(), getY(), getWidth(), getHeight());

    }



    private void initBox2D(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(getX(), getY());
        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(getWidth(), getHeight());

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0;


        body.createFixture(fixtureDef);

        shape.dispose();
    }

    @Override
    public boolean remove() {
        for (Disposable disposable : disposables){
            disposable.dispose();
        }
        return super.remove();
    }



}