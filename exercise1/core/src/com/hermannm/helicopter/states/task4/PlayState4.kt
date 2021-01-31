package com.hermannm.helicopter.states.task4

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle
import com.hermannm.helicopter.HelicopterGame
import com.hermannm.helicopter.sprites.task1.Helicopter1
import com.hermannm.helicopter.sprites.task4.Helicopter4
import com.hermannm.helicopter.states.GameStateManager
import com.hermannm.helicopter.states.State
import com.hermannm.helicopter.sprites.task4.Paddle
import com.hermannm.helicopter.states.PlayState


class PlayState4(stateManager: GameStateManager): PlayState(stateManager) {
    init {
        camera.setToOrtho(false, HelicopterGame.WIDTH, HelicopterGame.HEIGHT)
    }
    private var background: Texture = Texture("background.jpg")
    private val helicopter: Helicopter4 = Helicopter4(150F, 100F);
    private val playerPaddle: Paddle = Paddle(HelicopterGame.WIDTH-50F, 125.5F);
    private val comPaddle: Paddle = Paddle(50F, 125.5F);
    private val topBound: Rectangle = Rectangle(
            0F, //chopper.getTexture().getWidth().toFloat(),
            0F,
            HelicopterGame.WIDTH,
            HelicopterGame.HEIGHT - helicopter.getTexture().getHeight().toFloat()
    )
    private val rightBound: Rectangle = Rectangle(
            0F,
            0F,
            HelicopterGame.WIDTH - helicopter.getTexture().getWidth().toFloat(),
            HelicopterGame.HEIGHT
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

    private val topPaddleBound: Rectangle = Rectangle(
            0F, //chopper.getTexture().getWidth().toFloat(),
            0F,
            HelicopterGame.WIDTH,
            HelicopterGame.HEIGHT - playerPaddle.getTexture().getHeight().toFloat()
    )
    private val bottomPaddleBound: Rectangle = Rectangle(
            0F,
            playerPaddle.getTexture().getHeight().toFloat(),
            HelicopterGame.WIDTH,
            HelicopterGame.HEIGHT - playerPaddle.getTexture().getHeight().toFloat()
    )

    fun paddleWallCollision(paddle: Paddle): Array<Boolean>{
        var collisions = arrayOf(
                !(paddle.getBounds().overlaps(topPaddleBound)),
                !(paddle.getBounds().overlaps(bottomPaddleBound))
        )
        return collisions
    }

    override fun handleInput() {
        if (Gdx.input.isTouched) {
            var y: Int = Gdx.input.getY();
            if(y>HelicopterGame.HEIGHT){
                playerPaddle.changeDirection(arrayOf(false, true))
            }
            else{
                playerPaddle.changeDirection(arrayOf(true, false))
            }
        }
    }
    fun wallCollisions(): Array<Boolean> {
        var collisions = arrayOf(
                !(helicopter.getBounds().overlaps(topBound)),
                !(helicopter.getBounds().overlaps(bottomBound))
        )
        return collisions
    }
    fun paddleCollision(paddle: Paddle): Float{
        if(helicopter.getPosition().y>(paddle.getTexture().height/2-helicopter.texture.height/2)){
            return (-(paddle.getPosition().y +paddle.getTexture().height/2-helicopter.texture.height/2-helicopter.getPosition().y)/(paddle.getTexture().height/2+helicopter.texture.height/2))
        }
        else{
            return((helicopter.getPosition().y - paddle.getPosition().y -paddle.getTexture().height/2+helicopter.texture.height/2)/(paddle.getTexture().height/2-helicopter.texture.height/2))
        }
    }

    override fun update(deltaTime: Float) {
        handleInput();
        playerPaddle.handleCollision(paddleWallCollision(playerPaddle))
        if (comPaddle.getBounds().overlaps(helicopter.getBounds()) && (helicopter.getPosition().x > comPaddle.getPosition().x)){
            helicopter.changeVelocity(wallCollisions(), true, arrayOf(1F, paddleCollision(comPaddle)))
        }
        else if (playerPaddle.getBounds().overlaps(helicopter.getBounds()) && (helicopter.getPosition().x + helicopter.texture.width) < (playerPaddle.getPosition().x + playerPaddle.getTexture().width)){
            helicopter.changeVelocity(wallCollisions(), true, arrayOf(-1F, paddleCollision(playerPaddle)))

        }
        else{
            helicopter.changeVelocity(wallCollisions(), false, arrayOf(0F, 0F))
        }
        playerPaddle.update(deltaTime)
        helicopter.update(deltaTime);
    }
    override fun render(sprites: SpriteBatch) {
        sprites.setProjectionMatrix(camera.combined)
        sprites.begin()
        sprites.draw(background, 0F, 0F, HelicopterGame.WIDTH, HelicopterGame.HEIGHT)
        sprites.draw(
                helicopter.getTexture(),
                helicopter.getPosition().x,
                helicopter.getPosition().y
        )
        sprites.draw(
                playerPaddle.getTexture(),
                playerPaddle.getPosition().x,
                playerPaddle.getPosition().y
        )
        sprites.draw(
                comPaddle.getTexture(),
                comPaddle.getPosition().x,
                comPaddle.getPosition().y
        )
        sprites.end()
        super.render(sprites)
    }
    override fun dispose() {
        super.dispose()
    }
}