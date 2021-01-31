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
    fun wallCollisions(helicopter: Helicopter3): Array<Boolean> {
        return arrayOf(
                !(helicopter.getBounds().overlaps(topBound)),
                !(helicopter.getBounds().overlaps(rightBound)),
                !(helicopter.getBounds().overlaps(bottomBound)),
                !(helicopter.getBounds().overlaps(leftBound))
        )
    }
    //top, right, bottom, left
    fun helicopterCollisions(thisHelicopter: Helicopter3): Array<Boolean> {
        var array = arrayOf(false, false, false, false)
        var thisx: Float = thisHelicopter.getPosition().x
        var thisy: Float = thisHelicopter.getPosition().y
        var height: Float = thisHelicopter.getTexture().getHeight().toFloat()
        var width: Float = thisHelicopter.getTexture().getWidth().toFloat()
        for (helicopter in helicopters) {
            if(thisHelicopter != helicopter && thisHelicopter.getBounds().overlaps(helicopter.getBounds())) {
                var xOverlap: Float = 0.toFloat()
                var yOverlap: Float = 0.toFloat()
                var x: Float = helicopter.getPosition().x
                var y: Float = helicopter.getPosition().y
                if(thisx<x){ xOverlap = thisx-x+width }
                else { xOverlap = x-thisx+width }
                if(thisy<y){ yOverlap = thisy-y+height }
                else { yOverlap = y-thisy+height }
                if (xOverlap>yOverlap){
                    if(thisy<y){ array[0]= true }
                    else{ array[2]= true }
                }
                else{
                    if(thisx<x){ array[1]=true }
                    else{ array[3]= true }
                }
            }
        }
        return array
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
                    helicopter.getPosition().x,
                    helicopter.getPosition().y
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