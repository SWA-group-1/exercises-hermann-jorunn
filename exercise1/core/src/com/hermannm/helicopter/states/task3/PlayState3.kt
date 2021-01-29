package com.hermannm.helicopter.states.task3

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle
import com.hermannm.helicopter.HelicopterGame
import com.hermannm.helicopter.sprites.task3.Helicopter3
import com.hermannm.helicopter.states.GameStateManager
import com.hermannm.helicopter.states.State

class PlayState3(stateManager: GameStateManager): State(stateManager) {
    init {
        camera.setToOrtho(false, HelicopterGame.WIDTH, HelicopterGame.HEIGHT)
    }
    private var background: Texture = Texture("background.jpg")
    private val helicopters: Array<Helicopter3> = arrayOf(
            Helicopter3(150F, 100F),
            Helicopter3(300F, 350F),
            Helicopter3(600F, 150F)
    )
    private val topBound: Rectangle = Rectangle(
            0F, //chopper.getTexture().getWidth().toFloat(),
            0F,
            HelicopterGame.WIDTH,
            HelicopterGame.HEIGHT - helicopters[0].getTexture().getHeight().toFloat()
    )
    private val rightBound: Rectangle = Rectangle(
            0F,
            0F,
            HelicopterGame.WIDTH - helicopters[0].getTexture().getWidth().toFloat(),
            HelicopterGame.HEIGHT
    )
    private val bottomBound: Rectangle = Rectangle(
            0F,
            helicopters[0].getTexture().getHeight().toFloat(),
            HelicopterGame.WIDTH,
            HelicopterGame.HEIGHT - helicopters[0].getTexture().getHeight().toFloat()
    )
    private val leftBound: Rectangle = Rectangle(
            helicopters[0].getTexture().getWidth().toFloat(),
            0F,
            HelicopterGame.WIDTH - helicopters[0].getTexture().getWidth().toFloat(),
            HelicopterGame.HEIGHT
    )
    override fun handleInput() {

    }
    fun wallCollisions(helicopter: Helicopter3): Array<Boolean> {
        return arrayOf(
                !(helicopter.getBounds().overlaps(topBound)),
                !(helicopter.getBounds().overlaps(rightBound)),
                !(helicopter.getBounds().overlaps(bottomBound)),
                !(helicopter.getBounds().overlaps(leftBound))
        )
    }
    override fun update(deltaTime: Float) {
        handleInput();
        for (helicopter in helicopters) {
            helicopter.changeDirection(wallCollisions(helicopter))
            helicopter.update(deltaTime);
        }
    }
    override fun render(sprites: SpriteBatch) {
        sprites.setProjectionMatrix(camera.combined)
        sprites.begin()
        sprites.draw(background, 0F, 0F, HelicopterGame.WIDTH, HelicopterGame.HEIGHT)
        for (helicopter in helicopters) {
            sprites.draw(
                    helicopter.helicopterSprite,
                    helicopter.getPosition().x,
                    helicopter.getPosition().y
            )
        }
        sprites.end()
    }
    override fun dispose() {
    }
}