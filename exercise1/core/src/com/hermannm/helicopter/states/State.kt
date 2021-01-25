package com.hermannm.helicopter.states

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector3

abstract class State(protected var stateManager: GameStateManager) {
    protected var camera: OrthographicCamera = OrthographicCamera()
    protected var mouse: Vector3 = Vector3()
    abstract fun handleInput()
    abstract fun update(deltaTime: Float)
    abstract fun render(sprites: SpriteBatch)
    abstract fun dispose()
}