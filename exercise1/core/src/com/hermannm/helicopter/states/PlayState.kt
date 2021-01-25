package com.hermannm.helicopter.states

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle
import com.hermannm.helicopter.Helicopter
import com.hermannm.helicopter.sprites.Chopper

class PlayState(stateManager: GameStateManager): State(stateManager) {
    init {
        camera.setToOrtho(false, Helicopter.WIDTH, Helicopter.HEIGHT)
    }
    private var background: Texture = Texture("background.jpg")
    private val chopper: Chopper = Chopper(150F, 100F);
    private val topBound: Rectangle = Rectangle(
            0F, //chopper.getTexture().getWidth().toFloat(),
            0F,
            Helicopter.WIDTH,
            Helicopter.HEIGHT - chopper.getTexture().getHeight().toFloat()
    )
    private val bottomBound: Rectangle = Rectangle(
            0F,
            chopper.getTexture().getHeight().toFloat(),
            Helicopter.WIDTH,
            Helicopter.HEIGHT - chopper.getTexture().getHeight().toFloat()
    )
    private val leftBound: Rectangle = Rectangle(
            chopper.getTexture().getWidth().toFloat(),
            0F,
            Helicopter.WIDTH - chopper.getTexture().getWidth().toFloat(),
            Helicopter.HEIGHT
    )
    private val rightBound: Rectangle = Rectangle(
            0F,
            0F,
            Helicopter.WIDTH - chopper.getTexture().getWidth().toFloat(),
            Helicopter.HEIGHT
    )
    override fun handleInput() {

    }
    fun collisions(): Array<Boolean> {
        var collisions = arrayOf(
                !(chopper.getBounds().overlaps(topBound)),
                !(chopper.getBounds().overlaps(bottomBound)),
                !(chopper.getBounds().overlaps(leftBound)),
                !(chopper.getBounds().overlaps(rightBound))
        )
        return collisions
    }
    override fun update(deltaTime: Float) {
        handleInput();
        chopper.changeDirection(collisions())
        chopper.update(deltaTime);
    }
    override fun render(sprites: SpriteBatch) {
        sprites.setProjectionMatrix(camera.combined)
        sprites.begin()
        sprites.draw(background, 0F, 0F, Helicopter.WIDTH, Helicopter.HEIGHT)
        sprites.draw(
                chopper.getSprite(),
                chopper.getPosition().x,
                chopper.getPosition().y
        )
        sprites.end()
    }
    override fun dispose() {
    }
}