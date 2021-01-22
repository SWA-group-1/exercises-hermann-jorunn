package com.hermannm.helicopter.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hermannm.helicopter.Helicopter;

public class PlayState extends State {
    private Texture helicopter;
    public PlayState(GameStateManager stateManager) {
        super(stateManager);
        helicopter = new Texture("attackhelicopter.png");
        camera.setToOrtho(false, Helicopter.WIDTH / 2, Helicopter.HEIGHT / 2);
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void render(SpriteBatch sprites) {
        sprites.setProjectionMatrix(camera.combined);
        sprites.begin();
        sprites.draw(helicopter, 50, 50);
        sprites.end();
    }

    @Override
    public void dispose() {

    }
}
