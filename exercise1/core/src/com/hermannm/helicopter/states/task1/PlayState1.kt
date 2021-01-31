package com.hermannm.helicopter.states.task1

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle
import com.hermannm.helicopter.HelicopterGame
import com.hermannm.helicopter.sprites.task1.Helicopter1
import com.hermannm.helicopter.sprites.task3.Helicopter3
import com.hermannm.helicopter.states.GameStateManager
import com.hermannm.helicopter.states.PlayState

class PlayState1(stateManager: GameStateManager): PlayState(stateManager) {
    init {
        camera.setToOrtho(false, HelicopterGame.WIDTH, HelicopterGame.HEIGHT)
    }
    private val background: Texture = Texture("background.jpg")
    private val helicopter1: Helicopter1 = Helicopter1(150F, 100F);
    private fun wallCollisions(helicopter: Helicopter1): HashMap<String, Boolean> {
        return hashMapOf(
                "top" to !helicopter.bounds.overlaps(Rectangle(
                        0F,
                        0F,
                        HelicopterGame.WIDTH,
                        HelicopterGame.HEIGHT - helicopter.sprite.height
                )),
                "right" to !helicopter.bounds.overlaps(Rectangle(
                        0F,
                        0F,
                        HelicopterGame.WIDTH - helicopter.sprite.width,
                        HelicopterGame.HEIGHT
                )),
                "bottom" to !helicopter.bounds.overlaps(Rectangle(
                        0F,
                        helicopter.sprite.height,
                        HelicopterGame.WIDTH,
                        HelicopterGame.HEIGHT - helicopter.sprite.height
                )),
                "left" to !helicopter.bounds.overlaps(Rectangle(
                        helicopter.sprite.width,
                        0F,
                        HelicopterGame.WIDTH - helicopter.sprite.width,
                        HelicopterGame.HEIGHT
                ))
        )
    }
    override fun update(deltaTime: Float) {
        helicopter1.changeDirection(wallCollisions(helicopter1))
        helicopter1.update(deltaTime)
    }
    override fun render(sprites: SpriteBatch) {
        sprites.projectionMatrix = camera.combined
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