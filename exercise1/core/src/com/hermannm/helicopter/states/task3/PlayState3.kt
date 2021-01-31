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
    fun wallCollisions(helicopter: Helicopter3): HashMap<String, Boolean> {
        return hashMapOf(
            "top" to !helicopter.bounds.overlaps(Rectangle(
                0F, //chopper.getTexture().getWidth().toFloat(),
                0F,
                HelicopterGame.WIDTH,
                HelicopterGame.HEIGHT - helicopter.getTexture().getTexture().getHeight()
            )),
            "right" to !helicopter.bounds.overlaps(Rectangle(
                    0F, //chopper.getTexture().getWidth().toFloat(),
                    0F,
                    HelicopterGame.WIDTH,
                    HelicopterGame.HEIGHT - helicopter.getTexture().getTexture().getHeight()
            )),
            "bottom" to !helicopter.bounds.overlaps(Rectangle(
                    0F, //chopper.getTexture().getWidth().toFloat(),
                    0F,
                    HelicopterGame.WIDTH,
                    HelicopterGame.HEIGHT - helicopter.getTexture().getTexture().getHeight()
            )),
            "left" to !helicopter.bounds.overlaps(Rectangle(
                    0F, //chopper.getTexture().getWidth().toFloat(),
                    0F,
                    HelicopterGame.WIDTH,
                    HelicopterGame.HEIGHT - helicopter.getTexture().getTexture().getHeight()
            ))
        )
    }
    //top, right, bottom, left
    fun helicopterCollisions(thisHelicopter: Helicopter3): HashMap<String, Boolean> {
        val collisions: HashMap<String, Boolean> = hashMapOf(
            "top" to false,
            "right" to false,
            "bottom" to false,
            "left" to false
        )
        var thisx: Float = thisHelicopter.position.x
        var thisy: Float = thisHelicopter.position.y
        var height: Float = thisHelicopter.getTexture().getTexture().getHeight().toFloat()
        var width: Float = thisHelicopter.getTexture().getTexture().getWidth().toFloat()
        for (helicopter in helicopters) {
            if(thisHelicopter != helicopter && thisHelicopter.bounds.overlaps(helicopter.bounds)) {
                var xOverlap: Float
                var yOverlap: Float
                var x: Float = helicopter.position.x
                var y: Float = helicopter.position.y
                if (thisx < x) {
                    xOverlap = thisx - x + width
                } else {
                    xOverlap = x - thisx + width
                }
                if (thisy < y) {
                    yOverlap = thisy - y + height
                } else {
                    yOverlap = y - thisy + height
                }
                if (xOverlap > yOverlap){
                    if (thisy < y) {
                        collisions["top"] = true
                    } else {
                        collisions["bottom"] = true
                    }
                } else {
                    if (thisx < x) {
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
            helicopter.update(deltaTime);
            helicopter.changeDirection(helicopterCollisions(helicopter))
        }
    }
    override fun render(sprites: SpriteBatch) {
        sprites.setProjectionMatrix(camera.combined)
        sprites.begin()
        sprites.draw(background, 0F, 0F, HelicopterGame.WIDTH, HelicopterGame.HEIGHT)
        for (helicopter in helicopters) {
            sprites.draw(
                    helicopter.getTexture(),
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