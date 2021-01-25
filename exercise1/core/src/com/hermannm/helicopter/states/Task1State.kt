package com.hermannm.helicopter.states

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle
import com.hermannm.helicopter.HelicopterGame
import com.hermannm.helicopter.sprites.Task1Helicopter

class Task1State(stateManager: GameStateManager): State(stateManager) {
    init {
        camera.setToOrtho(false, HelicopterGame.WIDTH, HelicopterGame.HEIGHT)
    }
    private var background: Texture = Texture("background.jpg")
    private val helicopter: Task1Helicopter = Task1Helicopter(150F, 100F);
    private val topBound: Rectangle = Rectangle(
            0F, //chopper.getTexture().getWidth().toFloat(),
            0F,
            HelicopterGame.WIDTH,
            HelicopterGame.HEIGHT - helicopter.getTexture().getHeight().toFloat()
    )
    private val bottomBound: Rectangle = Rectangle(
            0F,
            helicopter.getTexture().getHeight().toFloat(),
            HelicopterGame.WIDTH,
            HelicopterGame.HEIGHT - helicopter.getTexture().getHeight().toFloat()
    )
    private val leftBound: Rectangle = Rectangle(
            helicopter.getTexture().getWidth().toFloat(),
            0F,
            HelicopterGame.WIDTH - helicopter.getTexture().getWidth().toFloat(),
            HelicopterGame.HEIGHT
    )
    private val rightBound: Rectangle = Rectangle(
            0F,
            0F,
            HelicopterGame.WIDTH - helicopter.getTexture().getWidth().toFloat(),
            HelicopterGame.HEIGHT
    )
    override fun handleInput() {

    }
    fun collisions(): Array<Boolean> {
        var collisions = arrayOf(
                !(helicopter.getBounds().overlaps(topBound)),
                !(helicopter.getBounds().overlaps(bottomBound)),
                !(helicopter.getBounds().overlaps(leftBound)),
                !(helicopter.getBounds().overlaps(rightBound))
        )
        return collisions
    }
    override fun update(deltaTime: Float) {
        handleInput();
        helicopter.changeDirection(collisions())
        helicopter.update(deltaTime);
    }
    override fun render(sprites: SpriteBatch) {
        sprites.setProjectionMatrix(camera.combined)
        sprites.begin()
        sprites.draw(background, 0F, 0F, HelicopterGame.WIDTH, HelicopterGame.HEIGHT)
        sprites.draw(
                helicopter.getSprite(),
                helicopter.getPosition().x,
                helicopter.getPosition().y
        )
        sprites.end()
    }
    override fun dispose() {
    }
}