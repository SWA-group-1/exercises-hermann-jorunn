package com.hermannm.helicopter.view

import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.hermannm.helicopter.model.Bounds
import com.hermannm.helicopter.model.Helicopter
import kotlin.math.roundToInt

class CoordinateView(
    val helicopter: Helicopter
) {
    private val font: BitmapFont = BitmapFont()
    init {
        font.data.scale(2f)
    }
    fun draw(sprites: SpriteBatch) {
        font.draw(sprites, "x: " + helicopter.position.x.roundToInt().toString(), 150f, Bounds.INSTANCE.top - 20f)
        font.draw(sprites, "y: " + helicopter.position.y.roundToInt().toString(), 300f, Bounds.INSTANCE.top - 20f)
    }
}