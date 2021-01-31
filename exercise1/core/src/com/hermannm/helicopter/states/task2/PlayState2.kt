package com.hermannm.helicopter.states.task2

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle
import com.hermannm.helicopter.Controller
import com.hermannm.helicopter.HelicopterGame
import com.hermannm.helicopter.sprites.task1.Helicopter1
import com.hermannm.helicopter.sprites.task2.Helicopter2
import com.hermannm.helicopter.states.GameStateManager
import com.hermannm.helicopter.states.PlayState
import java.lang.Math.round
import kotlin.math.roundToInt

class PlayState2(stateManager: GameStateManager): PlayState(stateManager) {
    init {
        camera.setToOrtho(false, HelicopterGame.WIDTH, HelicopterGame.HEIGHT)
    }
    private val background: Texture = Texture("background.jpg")
    private val helicopter: Helicopter2 = Helicopter2(150F, 100F);
    private val controller: Controller = Controller()
    private val font: BitmapFont = BitmapFont()
    init {
        font.data.scale(2f)
    }
    private fun wallCollisions(helicopter: Helicopter2): HashMap<String, Boolean> {
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
    override fun handleInput() {
        when {
            controller.upPressed -> {
                helicopter.control(0, 1, wallCollisions(helicopter))
            }
            controller.rightPressed -> {
                helicopter.control(1, 0, wallCollisions(helicopter))
            }
            controller.downPressed -> {
                helicopter.control(0, -1, wallCollisions(helicopter))
            }
            controller.leftPressed -> {
                helicopter.control(-1, 0, wallCollisions(helicopter))
            }
            else -> {
                helicopter.control(0, 0, wallCollisions(helicopter))
            }
        }
    }
    override fun update(deltaTime: Float) {
        handleInput();
        helicopter.update(deltaTime);
    }
    override fun render(sprites: SpriteBatch) {
        sprites.setProjectionMatrix(camera.combined)
        sprites.begin()
        sprites.draw(background, 0F, 0F, HelicopterGame.WIDTH, HelicopterGame.HEIGHT)
        sprites.draw(
                helicopter.sprite,
                helicopter.position.x,
                helicopter.position.y
        )
        font.draw(sprites, "x: " + helicopter.position.x.roundToInt().toString(), 150f, HelicopterGame.HEIGHT - 20f)
        font.draw(sprites, "y: " + helicopter.position.y.roundToInt().toString(), 300f, HelicopterGame.HEIGHT - 20f)
        sprites.end()
        controller.draw()
        super.render(sprites)
    }
    override fun dispose() {
        controller.dispose()
        super.dispose()
    }
}