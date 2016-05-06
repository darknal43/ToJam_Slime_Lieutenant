package state.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import tools.Constants;

/**
 *
 * Created by Hongyu Wang on 5/5/2016.
 */
public class TitleScreen extends AbstractScreen implements Constants {
    private SpriteBatch spriteBatch;
    private Animation noGlowAnimation, glowAnimation, gridAnimation;
    private Image greenTitle, magentaTitle;

    private float stateTime;

    @Override
    protected void subclassInit() {
        animationInit();
        spriteBatch = new SpriteBatch();
        disposables.add(spriteBatch);
        stateTime = 0;
        titleInit();
    }




    private void animationInit(){
        TextureAtlas noGlow = new TextureAtlas("menuSlime\\menuSlimeNoGlow\\pack.atlas");
        disposables.add(noGlow);
        noGlowAnimation = setUpAnimation(noGlow, 1F/12);

        TextureAtlas glow = new TextureAtlas("menuSlime\\menuSlimeGlow\\pack.atlas");
        disposables.add(glow);
        glowAnimation = setUpAnimation(glow, 1F/12);

        noGlowAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        glowAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        gridInit();


    }

    private void gridInit(){
        TextureRegion [] textures = new TextureRegion[3];
        Texture texture;
        for (int i = 0; i < 3; i ++){
            String name = "menuGrid\\retroGrid0000"+i+".png";
            textures[i] = new TextureRegion(texture = new Texture(name));
            disposables.add(texture);
        }

        gridAnimation = new Animation(1F/12, textures);
    }

    private static Animation setUpAnimation(TextureAtlas textureAtlas, float frameRate){
        int size = textureAtlas.getRegions().size;
        TextureRegion [] noGlowAnimationRegion = new TextureRegion[size];

        int index = 0;
        for (TextureRegion animation : textureAtlas.getRegions())
            noGlowAnimationRegion[index++] = animation;

        return new Animation(frameRate, noGlowAnimationRegion);
    }


    private void titleInit(){
        Texture texture;
        magentaTitle = new Image(texture = new Texture("menuTitle\\mainTitleMagenta.png"));
        disposables.add(texture);

        float realWidth = (WIDTH - texture.getWidth()*SCALE)/2;
        float realHeight = (HEIGHT - texture.getHeight()*SCALE)/2;

        magentaTitle.setBounds(realWidth, realHeight, texture.getWidth() * SCALE, texture.getHeight() * SCALE);

        greenTitle = new Image(texture = new Texture("menuTitle\\mainTitleGreen.png"));
        disposables.add(texture);

        greenTitle.setBounds(realWidth-2, realHeight, texture.getWidth() * SCALE, texture.getHeight() * SCALE);

        stage.addActor(greenTitle);
        stage.addActor(magentaTitle);
    }







    @Override
    protected void draw() {
        super.draw();
        spriteBatch.begin();
        drawBackground();
        drawSlimes();


        spriteBatch.end();
    }

    private void drawBackground(){
        TextureRegion region;
        spriteBatch.draw(
            region = gridAnimation.getKeyFrame(stateTime, true),
            0,
            -200*SCALE,
            region.getRegionWidth()*SCALE,
            region.getRegionHeight()*SCALE
        );
    }


    private void drawSlimes(){
        for (int i = 0; i < 4; i ++) {
            TextureRegion region;
            spriteBatch.draw(
                    region = glowAnimation.getKeyFrame(stateTime, true),
                    i * WIDTH / 4,
                    0,
                    region.getRegionWidth() * SCALE,
                    region.getRegionHeight() * SCALE
            );

            region.flip(false, true);
            spriteBatch.draw(
                    region,
                    i * WIDTH / 4,
                    HEIGHT - region.getRegionHeight() * SCALE,
                    region.getRegionWidth() * SCALE,
                    region.getRegionHeight() * SCALE
            );
            region.flip(false, true);
            spriteBatch.draw(
                    region = noGlowAnimation.getKeyFrame(stateTime, true),
                    i * WIDTH / 4,
                    0,
                    region.getRegionWidth() * SCALE,
                    region.getRegionHeight()*SCALE
            );


            region.flip(false, true);
            spriteBatch.draw(
                    region,
                    i * WIDTH / 4,
                    HEIGHT - region.getRegionHeight() * SCALE,
                    region.getRegionWidth()*SCALE,
                    region.getRegionHeight()*SCALE
            );
            region.flip(false, true);
        }
    }


    @Override
    protected void update(float delta) {
        super.update(delta);
        stateTime += delta;

    }



}
