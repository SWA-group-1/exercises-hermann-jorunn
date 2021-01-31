package com.hermannm.helicopter.states.task4

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle
import com.hermannm.helicopter.HelicopterGame
import com.hermannm.helicopter.sprites.task4.Helicopter4
import com.hermannm.helicopter.states.GameStateManager
import com.hermannm.helicopter.sprites.task4.Paddle
import com.hermannm.helicopter.states.PlayState
import com.badlogic.gdx.graphics.g2d.BitmapFont


class PlayState4(stateManager: GameStateManager): PlayState(stateManager) {
    init {
        camera.setToOrtho(false, HelicopterGame.WIDTH, HelicopterGame.HEIGHT)
    }
    private var background: Texture = Texture("background.jpg")
    private val helicopter: Helicopter4 = Helicopter4(150F, 100F);
    private val winningScore: Int = 21;
    private val playerPaddle: Paddle = Paddle(HelicopterGame.WIDTH-50F, 125.5F);
    private val comPaddle: Paddle = Paddle(50F, 125.5F)
    private val font: BitmapFont = BitmapFont()
    private var text: String = "";
    init {
        font.data.scale(2f)
    }
    // [playerScore, comScore]
    private var scores: Array<Int> = arrayOf(0,0);
    private val topBound: Rectangle = Rectangle(
            0F, //chopper.getTexture().getWidth().toFloat(),
            0F,
            HelicopterGame.WIDTH,
            HelicopterGame.HEIGHT - helicopter.sprite.height
    )
    private val bottomBound: Rectangle = Rectangle(
            0F,
            helicopter.sprite.height,
            HelicopterGame.WIDTH,
            HelicopterGame.HEIGHT - helicopter.sprite.height
    )
    private val topPaddleBound: Rectangle = Rectangle(
            0F, //chopper.getTexture().getWidth().toFloat(),
            0F,
            HelicopterGame.WIDTH,
            HelicopterGame.HEIGHT - playerPaddle.texture.height.toFloat()
    )
    private val bottomPaddleBound: Rectangle = Rectangle(
            0F,
            playerPaddle.texture.getHeight().toFloat(),
            HelicopterGame.WIDTH,
            HelicopterGame.HEIGHT - playerPaddle.texture.height.toFloat()
    )
    private fun paddleWallCollision(paddle: Paddle): Array<Boolean>{
        return arrayOf(
            !(paddle.bounds.overlaps(topPaddleBound)),
            !(paddle.bounds.overlaps(bottomPaddleBound))
        )
    }
    override fun handleInput() {
        if (Gdx.input.isTouched) {
            if (helicopter.velocity.x == 0F && helicopter.velocity.y == 0F){
                helicopter.setVelocity(arrayOf(-1F, 0.5F))
                scores[0]=0;
                scores[1]=0;
                text = "";
                comPaddle.changeDirection(arrayOf(true,false))
            }
            val y: Int = Gdx.input.getY();
            if(y>HelicopterGame.HEIGHT){
                playerPaddle.changeDirection(arrayOf(false, true))
            }
            else{
                playerPaddle.changeDirection(arrayOf(true, false))
            }
        }
    }
    private fun wallCollisions(): Array<Boolean> {
        var collisions = arrayOf(
            !(helicopter.bounds.overlaps(topBound)),
            !(helicopter.bounds.overlaps(bottomBound))
        )
        return collisions
    }
    private fun checkRoundFinished(): Boolean{
        if ((helicopter.position.x + helicopter.texture.width) < 0) {
            scores[0] += 1;
            return true;
        }
        if ((helicopter.position.x) > HelicopterGame.WIDTH) {
            scores[1] += 1
            return true;
        }
        return false
    }
    private fun paddleCollision(paddle: Paddle): Float{
        if (helicopter.position.y > (paddle.texture.height / 2 - helicopter.texture.height / 2)) {
            return (
                -(
                    paddle.position.y + paddle.texture.height / 2
                    - helicopter.texture.height / 2 - helicopter.position.y
                ) / (paddle.texture.height / 2 + helicopter.texture.height / 2)
            )
        }
        else{
            return (
                (
                    helicopter.position.y - paddle.position.y
                    - paddle.texture.height / 2 + helicopter.texture.height / 2
                ) / (paddle.texture.height / 2 - helicopter.texture.height / 2)
            )
        }
    }
    private fun comPaddleController(){
        if (comPaddle.velocity.y==0.0F && scores[0]<winningScore && scores[1]< winningScore){
            comPaddle.changeDirection(arrayOf(false,true))
        }
        var collisions: Array<Boolean> = paddleWallCollision(comPaddle);
        if (collisions[0]){
            comPaddle.changeDirection(arrayOf(false,true))
        }
        else if (collisions[1]){
            comPaddle.changeDirection(arrayOf(true,false))
        }
    }
    override fun update(deltaTime: Float) {
        handleInput();
        if(!checkRoundFinished()){
            comPaddleController()
            playerPaddle.handleCollision(paddleWallCollision(playerPaddle))
            if (comPaddle.bounds.overlaps(helicopter.bounds) && (helicopter.position.x > comPaddle.position.x)){
                helicopter.changeVelocity(wallCollisions(), true, arrayOf(1F, paddleCollision(comPaddle)))
            } else if (
                playerPaddle.bounds.overlaps(helicopter.bounds)
                && (helicopter.position.x + helicopter.texture.width)
                < (playerPaddle.position.x + playerPaddle.texture.width)
            ) {
                helicopter.changeVelocity(wallCollisions(), true, arrayOf(-1F, paddleCollision(playerPaddle)))
            } else {
                helicopter.changeVelocity(wallCollisions(), false, arrayOf(0F, 0F))
            }
            playerPaddle.update(deltaTime);
            comPaddle.update(deltaTime);
            helicopter.update(deltaTime);
        } else {
            if (scores[0]>=winningScore) {
                text = "You Win!! Press to play again"
                helicopter.setVelocity(arrayOf(0F,0F))
                playerPaddle.setPosition(HelicopterGame.WIDTH-50F, 125.5F)
                playerPaddle.changeDirection(arrayOf(false, false))
                comPaddle.setPosition(50F, 125.5F)
                comPaddle.changeDirection(arrayOf(false, false))
            }
            if (scores[1]>=winningScore) {
                text = "You Lose. Press to play again"
                helicopter.setVelocity(arrayOf(0F,0F))
                playerPaddle.setPosition(HelicopterGame.WIDTH-50F, 125.5F)
                playerPaddle.changeDirection(arrayOf(false, false))
                comPaddle.setPosition(50F, 125.5F)
                comPaddle.changeDirection(arrayOf(false, false))
            }
            helicopter.setPosition(
                HelicopterGame.WIDTH/2-helicopter.texture.width/2,
                HelicopterGame.HEIGHT/2-helicopter.texture.height/2
            )
        }
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
        sprites.draw(
            playerPaddle.texture,
            playerPaddle.position.x,
            playerPaddle.position.y
        )
        sprites.draw(
                comPaddle.texture,
                comPaddle.position.x,
                comPaddle.position.y
        )
        font.draw(sprites, scores[1].toString(), 200F, HelicopterGame.HEIGHT -20F)
        font.draw(sprites, scores[0].toString(), HelicopterGame.WIDTH -200F, HelicopterGame.HEIGHT -20F)
        if(text!=""){
            font.draw(sprites, text, 130F, 200F)
        }
        sprites.end()
        super.render(sprites)
    }
    override fun dispose() {
        super.dispose()
        background.dispose()
        playerPaddle.dispose()
        comPaddle.dispose()
    }
}