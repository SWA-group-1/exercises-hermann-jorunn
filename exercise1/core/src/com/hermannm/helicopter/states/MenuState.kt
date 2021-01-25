package com.hermannm.helicopter.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.hermannm.helicopter.Helicopter

class MenuState(stateManager: GameStateManager): State(stateManager) {
    private var background: Texture = Texture("background.jpg")
    private var playButton: Texture = Texture("playbutton.png")
    override fun handleInput() {
        if (Gdx.input.justTouched()) {
            stateManager.set(PlayState(stateManager))
            dispose()
        }
    }
    override fun update(deltaTime: Float) {
        handleInput()
    }
    override fun render(sprites: SpriteBatch) {
        sprites.begin()
        sprites.draw(background, 0F, 0F, Helicopter.WIDTH, Helicopter.HEIGHT)
        sprites.draw(playButton, (Helicopter.WIDTH / 2) - (playButton.getWidth() / 2), Helicopter.HEIGHT / 2 + 25)
        sprites.end()
    }
    override fun dispose() {
        background.dispose()
        playButton.dispose()
    }
}