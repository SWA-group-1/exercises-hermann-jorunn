package com.hermannm.helicopter.states

import com.badlogic.gdx.graphics.g2d.SpriteBatch

abstract class PlayState(stateManager: GameStateManager) : State(stateManager) {
    protected val backButton: BackButton = BackButton(stateManager)
    override fun render(sprites: SpriteBatch) {
        backButton.draw()
    }
    override fun dispose() {
        backButton.dispose()
    }
}