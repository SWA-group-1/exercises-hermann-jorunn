package com.hermannm.helicopter.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.hermannm.helicopter.HelicopterGame
import com.hermannm.helicopter.states.task1.PlayState1
import com.hermannm.helicopter.states.task2.PlayState2
import com.hermannm.helicopter.states.task3.PlayState3

class MenuState(stateManager: GameStateManager): State(stateManager) {
    init {
        camera.setToOrtho(false, HelicopterGame.WIDTH, HelicopterGame.HEIGHT)
    }
    private var background: Texture = Texture("background.jpg")
    private var playButton: Texture = Texture("playbutton.png")
    override fun handleInput() {
        if (Gdx.input.justTouched()) {
            stateManager.set(PlayState1(stateManager))
            dispose()
        }
    }
    override fun update(deltaTime: Float) {
        handleInput()
    }
    override fun render(sprites: SpriteBatch) {
        sprites.setProjectionMatrix(camera.combined)
        sprites.begin()
        sprites.draw(background, 0F, 0F)
        sprites.draw(playButton, camera.position.x - (playButton.getWidth() / 2), camera.position.y)
        sprites.end()
    }
    override fun dispose() {
        background.dispose()
        playButton.dispose()
    }
}