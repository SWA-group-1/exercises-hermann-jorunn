package com.hermannm.helicopter.states

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch

abstract class State(protected var stateManager: GameStateManager) {
    protected val camera: OrthographicCamera = OrthographicCamera()
    abstract fun handleInput()
    abstract fun update(deltaTime: Float)
    abstract fun render(sprites: SpriteBatch)
    abstract fun dispose()
}