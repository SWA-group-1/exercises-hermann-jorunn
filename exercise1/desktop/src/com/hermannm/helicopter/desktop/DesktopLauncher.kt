package com.hermannm.helicopter.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.hermannm.helicopter.HelicopterGame

object DesktopLauncher {
    @JvmStatic
    fun main(arg: Array<String>) {
        val config = LwjglApplicationConfiguration()
        config.width = HelicopterGame.WIDTH.toInt()
        config.height = HelicopterGame.HEIGHT.toInt()
        config.title = HelicopterGame.TITLE
        LwjglApplication(HelicopterGame(), config)
    }
}