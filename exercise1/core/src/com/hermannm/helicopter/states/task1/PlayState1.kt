package com.hermannm.helicopter.states.task1

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle
import com.hermannm.helicopter.HelicopterGame
import com.hermannm.helicopter.sprites.task1.Helicopter1
import com.hermannm.helicopter.states.GameStateManager
import com.hermannm.helicopter.states.State

class PlayState1(stateManager: GameStateManager): State(stateManager) {
    init {
        camera.setToOrtho(false, HelicopterGame.WIDTH, HelicopterGame.HEIGHT)
    }
    private var background: Texture = Texture("background.jpg")
    private val helicopter1: Helicopter1 = Helicopter1(150F, 100F);
    private val topBound: Rectangle = Rectangle(
            0F, //chopper.getTexture().getWidth().toFloat(),
            0F,
            HelicopterGame.WIDTH,
            HelicopterGame.HEIGHT - helicopter1.getTexture().getHeight().toFloat()
    )
    private val rightBound: Rectangle = Rectangle(
            0F,
            0F,
            HelicopterGame.WIDTH - helicopter1.getTexture().getWidth().toFloat(),
            HelicopterGame.HEIGHT
    )
    private val bottomBound: Rectangle = Rectangle(
            0F,
            helicopter1.getTexture().getHeight().toFloat(),
            HelicopterGame.WIDTH,
            HelicopterGame.HEIGHT - helicopter1.getTexture().getHeight().toFloat()
    )
    private val leftBound: Rectangle = Rectangle(
            helicopter1.getTexture().getWidth().toFloat(),
            0F,
            HelicopterGame.WIDTH - helicopter1.getTexture().getWidth().toFloat(),
            HelicopterGame.HEIGHT
    )
    override fun handleInput() {

    }
    fun collisions(): Array<Boolean> {
        var collisions = arrayOf(
                !(helicopter1.getBounds().overlaps(topBound)),
                !(helicopter1.getBounds().overlaps(rightBound)),
                !(helicopter1.getBounds().overlaps(bottomBound)),
                !(helicopter1.getBounds().overlaps(leftBound))
        )
        return collisions
    }
    override fun update(deltaTime: Float) {
        handleInput();
        helicopter1.changeDirection(collisions())
        helicopter1.update(deltaTime);
    }
    override fun render(sprites: SpriteBatch) {
        sprites.setProjectionMatrix(camera.combined)
        sprites.begin()
        sprites.draw(background, 0F, 0F, HelicopterGame.WIDTH, HelicopterGame.HEIGHT)
        sprites.draw(
                helicopter1.getTexture(),
                helicopter1.getPosition().x,
                helicopter1.getPosition().y
        )
        sprites.end()
    }
    override fun dispose() {
    }
}