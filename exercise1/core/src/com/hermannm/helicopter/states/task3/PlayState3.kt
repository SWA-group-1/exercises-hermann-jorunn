package com.hermannm.helicopter.states.task3

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle
import com.hermannm.helicopter.HelicopterGame
import com.hermannm.helicopter.sprites.task3.Helicopter3
import com.hermannm.helicopter.states.GameStateManager
import com.hermannm.helicopter.states.PlayState

class PlayState3(stateManager: GameStateManager): PlayState(stateManager) {
    init {
        camera.setToOrtho(false, HelicopterGame.WIDTH, HelicopterGame.HEIGHT)
    }
    private val background: Texture = Texture("background.jpg")
    private val helicopters: Array<Helicopter3> = arrayOf(
            Helicopter3(150F, 100F),
            Helicopter3(300F, 350F),
            Helicopter3(600F, 150F)
    )
    private fun wallCollisions(helicopter: Helicopter3): HashMap<String, Boolean> {
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
    private fun helicopterCollisions(thisHelicopter: Helicopter3): HashMap<String, Boolean> {
        val collisions: HashMap<String, Boolean> = hashMapOf(
            "top" to false,
            "right" to false,
            "bottom" to false,
            "left" to false
        )
        val thisX: Float = thisHelicopter.position.x
        val thisY: Float = thisHelicopter.position.y
        val thisHeight: Float = thisHelicopter.sprite.height
        val thisWidth: Float = thisHelicopter.sprite.width
        for (helicopter in helicopters) {
            if(thisHelicopter != helicopter && thisHelicopter.bounds.overlaps(helicopter.bounds)) {
                val x: Float = helicopter.position.x
                val y: Float = helicopter.position.y
                val xOverlap = if (thisX < x) {
                    thisX - x + thisWidth
                } else {
                    x - thisX + thisWidth
                }
                val yOverlap = if (thisY < y) {
                    thisY - y + thisHeight
                } else {
                    y - thisY + thisHeight
                }
                if (xOverlap > yOverlap){
                    if (thisY < y) {
                        collisions["top"] = true
                    } else {
                        collisions["bottom"] = true
                    }
                } else {
                    if (thisX < x) {
                        collisions["right"] = true
                    } else {
                        collisions["left"] = true
                    }
                }
            }
        }
        return collisions
    }
    override fun update(deltaTime: Float) {
        handleInput();
        for (helicopter in helicopters) {
            helicopter.changeDirection(wallCollisions(helicopter))
            helicopter.update(deltaTime)
            helicopter.changeDirection(helicopterCollisions(helicopter))
        }
    }
    override fun render(sprites: SpriteBatch) {
        sprites.projectionMatrix = camera.combined
        sprites.begin()
        sprites.draw(background, 0F, 0F, HelicopterGame.WIDTH, HelicopterGame.HEIGHT)
        for (helicopter in helicopters) {
            sprites.draw(
                    helicopter.sprite,
                    helicopter.position.x,
                    helicopter.position.y
            )
        }
        sprites.end()
        super.render(sprites)
    }
    override fun dispose() {
        background.dispose()
        super.dispose()
    }
    override fun handleInput() {}
}