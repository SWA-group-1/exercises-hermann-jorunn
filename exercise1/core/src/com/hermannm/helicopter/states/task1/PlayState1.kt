package com.hermannm.helicopter.states.task1

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle
import com.hermannm.helicopter.HelicopterGame
import com.hermannm.helicopter.sprites.task1.Helicopter1
import com.hermannm.helicopter.states.GameStateManager
import com.hermannm.helicopter.states.PlayState

class PlayState1(stateManager: GameStateManager): PlayState(stateManager) {
    init {
        camera.setToOrtho(false, HelicopterGame.WIDTH, HelicopterGame.HEIGHT)
    }
    private val background: Texture = Texture("background.jpg")
    private val helicopter1: Helicopter1 = Helicopter1(150F, 100F);
    private val topBound: Rectangle = Rectangle(
            0F, //chopper.getTexture().getWidth().toFloat(),
            0F,
            HelicopterGame.WIDTH,
            HelicopterGame.HEIGHT - helicopter1.sprite.getHeight()
    )
    private val rightBound: Rectangle = Rectangle(
            0F,
            0F,
            HelicopterGame.WIDTH - helicopter1.sprite.getWidth(),
            HelicopterGame.HEIGHT
    )
    private val bottomBound: Rectangle = Rectangle(
            0F,
            helicopter1.sprite.getHeight(),
            HelicopterGame.WIDTH,
            HelicopterGame.HEIGHT - helicopter1.sprite.getHeight().toFloat()
    )
    private val leftBound: Rectangle = Rectangle(
            helicopter1.sprite.getTexture().getWidth().toFloat(),
            0F,
            HelicopterGame.WIDTH - helicopter1.sprite.getTexture().getWidth().toFloat(),
            HelicopterGame.HEIGHT
    )
    fun collisions(): Array<Boolean> {
        return arrayOf(
            !(helicopter1.bounds.overlaps(topBound)),
            !(helicopter1.bounds.overlaps(rightBound)),
            !(helicopter1.bounds.overlaps(bottomBound)),
            !(helicopter1.bounds.overlaps(leftBound))
        )
    }
    override fun update(deltaTime: Float) {
        helicopter1.changeDirection(collisions())
        helicopter1.update(deltaTime)
    }
    override fun render(sprites: SpriteBatch) {
        sprites.setProjectionMatrix(camera.combined)
        sprites.begin()
        sprites.draw(background, 0F, 0F, HelicopterGame.WIDTH, HelicopterGame.HEIGHT)
        sprites.draw(
                helicopter1.sprite,
                helicopter1.position.x,
                helicopter1.position.y
        )
        sprites.end()
        super.render(sprites)
    }
    override fun dispose() {
        background.dispose()
        super.dispose()
    }
    override fun handleInput() {}
}