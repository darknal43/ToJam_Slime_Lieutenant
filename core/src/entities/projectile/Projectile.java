package entities.projectile;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import entities.GameEntity;
import entities.Player;
import tools.Utils;

/**
 * Created by Hairuo on 2016-05-06.
 */
public class Projectile extends GameEntity {
    private float targetX;
    private float targetY;
    private float speed;
    private float size;
    private float xVelo;
    private float turnSpeed;
    private float yVelo;
    private Player player;
    private boolean returning;

    public Projectile(Player player, String fileStringPath, float targetX, float targetY, float size, float speed){
        super(fileStringPath);
        this.targetX = targetX;
        this.targetY = targetY;
        this.speed = speed;
        this.size = size;
        this.player = player;
        this.returning = false;
        this.turnSpeed = 1;
        setRotation(0);
        initSprites();



    }

    public void initSprites(){
        Texture texture = new Texture("badlogic.jpg");
        sprite = new Sprite(texture);
        setBounds(50, 50, 100, 100);
        sprite.setSize(100, 100);
        sprite.setOrigin(getOriginX(), getOriginY());
    }


    @Override
    public void act(float delta) {
        super.act(delta);
        move();

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

    }

    /**
     * Moves the ship a certain distance based on its state
     */
    public void move(){


        float toTarget = Vector2.dst(targetX, targetY, this.getX(), this.getY());
        float incrementDistance = (float)Math.hypot(xVelo, yVelo);

        if(!returning && toTarget < incrementDistance){
            returning = true;
        }

        if(returning){
            targetX = player.getX();
            targetY = player.getY();
        }

        this.setRotation(Utils.track(targetX, targetY, this.getX(), this.getY(),this.getRotation(), turnSpeed));

        travelVector = new Vector2().setLength(speed).setAngle(this.getRotation());


        this.setX(this.getX() + travelVector.x);
        this.setX(this.getY() + travelVector.y);


    }
}
