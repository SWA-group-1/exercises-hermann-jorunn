package com.hermannm.helicopter.states.task2

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle
import com.hermannm.helicopter.Controller
import com.hermannm.helicopter.HelicopterGame
import com.hermannm.helicopter.sprites.task2.Helicopter2
import com.hermannm.helicopter.states.GameStateManager
import com.hermannm.helicopter.states.State

class PlayState2(stateManager: GameStateManager): State(stateManager) {
    init {
        camera.setToOrtho(false, HelicopterGame.WIDTH, HelicopterGame.HEIGHT)
    }
    private var background: Texture = Texture("background.jpg")
    private val helicopter2: Helicopter2 = Helicopter2(150F, 100F);
    var controller: Controller = Controller()
    private val topBound: Rectangle = Rectangle(
            0F, //chopper.getTexture().getWidth().toFloat(),
            0F,
            HelicopterGame.WIDTH,
            HelicopterGame.HEIGHT - helicopter2.getTexture().getHeight().toFloat()
    )
    private val rightBound: Rectangle = Rectangle(
            0F,
            0F,
            HelicopterGame.WIDTH - helicopter2.getTexture().getWidth().toFloat(),
            HelicopterGame.HEIGHT
    )
    private val bottomBound: Rectangle = Rectangle(
            0F,
            helicopter2.getTexture().getHeight().toFloat(),
            HelicopterGame.WIDTH,
            HelicopterGame.HEIGHT - helicopter2.getTexture().getHeight().toFloat()
    )
    private val leftBound: Rectangle = Rectangle(
            helicopter2.getTexture().getWidth().toFloat(),
            0F,
            HelicopterGame.WIDTH - helicopter2.getTexture().getWidth().toFloat(),
            HelicopterGame.HEIGHT
    )
    override fun handleInput() {
        if (controller.upPressed) {
            helicopter2.control(0, 1, collisions())
        } else if (controller.rightPressed) {
            helicopter2.control(1, 0, collisions())
        } else if (controller.downPressed) {
            helicopter2.control(0, -1, collisions())
        } else if (controller.leftPressed) {
            helicopter2.control(-1, 0, collisions())
        } else {
            helicopter2.control(0, 0, collisions())
        }
    }
    fun collisions(): Array<Boolean> {
        var collisions = arrayOf(
                !(helicopter2.getBounds().overlaps(topBound)),
                !(helicopter2.getBounds().overlaps(rightBound)),
                !(helicopter2.getBounds().overlaps(bottomBound)),
                !(helicopter2.getBounds().overlaps(leftBound))
        )
        return collisions
    }
    override fun update(deltaTime: Float) {
        handleInput();
        helicopter2.update(deltaTime);
    }
    override fun render(sprites: SpriteBatch) {
        sprites.setProjectionMatrix(camera.combined)
        sprites.begin()
        sprites.draw(background, 0F, 0F, HelicopterGame.WIDTH, HelicopterGame.HEIGHT)
        sprites.draw(
                helicopter2.getSprite(),
                helicopter2.getPosition().x,
                helicopter2.getPosition().y
        )
        sprites.end()
        controller.draw()
    }
    override fun dispose() {
    }
}