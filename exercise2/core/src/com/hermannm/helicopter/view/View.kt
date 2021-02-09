package com.hermannm.helicopter.view

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.hermannm.helicopter.model.Bounds
import com.hermannm.helicopter.model.Model

class View(
    model: Model
) {
    private val camera: OrthographicCamera = OrthographicCamera()
    init {
        camera.setToOrtho(false, Bounds.INSTANCE.right, Bounds.INSTANCE.top)
    }
    private val backgroundView: BackgroundView = BackgroundView()
    private val helicopterView: HelicopterView = HelicopterView(model.helicopter)
    private val coordinateView: CoordinateView = CoordinateView(model.helicopter)
    val navigation: Navigation = Navigation(camera)
    fun render(sprites: SpriteBatch) {
        sprites.projectionMatrix = camera.combined
        sprites.begin()
        backgroundView.draw(sprites)
        helicopterView.draw(sprites)
        coordinateView.draw(sprites)
        sprites.end()
        navigation.draw()
    }
}