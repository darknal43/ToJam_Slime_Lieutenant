package entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import tools.WorldFactory;

/**
 *
 * Created by Hongyu Wang on 5/5/2016.
 */
public abstract class GameEntity extends Actor {
    protected Sprite sprite;
    protected Body body;
    protected Array<? extends TextureRegion> assets;
    protected World world;


    public GameEntity(String spriteFilePath){
        this(spriteFilePath, 0, 0, 0, 0);
    }


    public GameEntity(String spriteFilePath, float x, float y, float width, float height){
        setBounds(x, y, width, height);
        init();
        initSprite(spriteFilePath);
    }

    protected void init(){
        assets = new Array<>();
        world = WorldFactory.getWorld();
        initBox2D();


    }

    private void initSprite(String spriteFilePath){
        Texture texture = new Texture(spriteFilePath);
        sprite = new Sprite(texture);
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

    @Override
    public boolean remove() {
        return super.remove();
    }
}