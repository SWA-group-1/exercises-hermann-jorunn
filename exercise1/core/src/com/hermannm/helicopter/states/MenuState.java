package com.hermannm.helicopter.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hermannm.helicopter.Helicopter;

public class MenuState extends State {
    private Texture background;
    private Texture playButton;
    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("background.jpg");
        playButton = new Texture("playbutton.png");
    }
    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            stateManager.set(new PlayState(stateManager));
            dispose();
        }
    }
    @Override
    public void update(float deltaTime) {
        handleInput();
    }
    @Override
    public void render(SpriteBatch sprites) {
        sprites.begin();
        sprites.draw(background, 0, 0, Helicopter.WIDTH, Helicopter.HEIGHT);
        sprites.draw(playButton, (Helicopter.WIDTH / 2) - (playButton.getWidth() / 2), Helicopter.HEIGHT / 2 + 25);
        sprites.end();
    }
    public void dispose() {
        background.dispose();
        playButton.dispose();
    }
}
