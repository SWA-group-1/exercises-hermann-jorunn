package com.hermannm.helicopter.states

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.hermannm.helicopter.Helicopter

class PlayState(stateManager: GameStateManager): State(stateManager) {
    init {
        camera.setToOrtho(false, Helicopter.WIDTH / 2, Helicopter.HEIGHT / 2)
    }
    private val helicopter: Texture = Texture("attackhelicopter.png")
    override fun handleInput() {

    }
    override fun update(deltaTime: Float) {

    }
    override fun render(sprites: SpriteBatch) {
        sprites.setProjectionMatrix(camera.combined)
        sprites.begin()
        sprites.draw(helicopter, 50F, 50F)
        sprites.end()
    }
    override fun dispose() {
        helicopter.dispose()
    }
}