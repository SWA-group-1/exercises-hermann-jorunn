package com.hermannm.helicopter.states

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.hermannm.helicopter.Helicopter
import com.hermannm.helicopter.sprites.Chopper

class PlayState(stateManager: GameStateManager): State(stateManager) {
    init {
        camera.setToOrtho(false, Helicopter.WIDTH, Helicopter.HEIGHT)
    }
    private val chopper: Chopper = Chopper(50F, 100F);
    override fun handleInput() {

    }
    override fun update(deltaTime: Float) {
        handleInput();
        chopper.update(deltaTime);
    }
    override fun render(sprites: SpriteBatch) {
        sprites.setProjectionMatrix(camera.combined)
        sprites.begin()
        sprites.draw(chopper.getTexture(), chopper.getPosition().x, chopper.getPosition().y)
        sprites.end()
    }
    override fun dispose() {
    }
}