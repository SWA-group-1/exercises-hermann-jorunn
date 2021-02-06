package com.hermannm.helicopter

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle
import com.hermannm.helicopter.model.Helicopter
import com.hermannm.helicopter.view.Navigation
import kotlin.math.roundToInt

class PlayState2 : PlayState() {
    init {
        camera.setToOrtho(false, HelicopterGame.WIDTH, HelicopterGame.HEIGHT)
    }
    private val background: Texture = Texture("background.jpg")
    private val helicopter: Helicopter = Helicopter(150F, 100F);
    private val navigation: Navigation = Navigation()
    private val font: BitmapFont = BitmapFont()
    init {
        font.data.scale(2f)
    }
    private fun wallCollisions(helicopter: Helicopter): HashMap<String, Boolean> {
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
            navigation.upPressed -> {
                helicopter.control(0, 1, wallCollisions(helicopter))
            }
            navigation.rightPressed -> {
                helicopter.control(1, 0, wallCollisions(helicopter))
            }
            navigation.downPressed -> {
                helicopter.control(0, -1, wallCollisions(helicopter))
            }
            navigation.leftPressed -> {
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
        sprites.projectionMatrix = camera.combined
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
        navigation.draw()
        super.render(sprites)
    }
    override fun dispose() {
        navigation.dispose()
        super.dispose()
    }
}