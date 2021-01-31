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
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.hermannm.helicopter.states.task1.PlayState1


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

    //Playerscore, comScore
    private var scores: Array<Int> = arrayOf(0,0);
    private val topBound: Rectangle = Rectangle(
            0F, //chopper.getTexture().getWidth().toFloat(),
            0F,
            HelicopterGame.WIDTH,
            HelicopterGame.HEIGHT - helicopter.getTexture().getHeight().toFloat()
    )
    private val bottomBound: Rectangle = Rectangle(
            0F,
            helicopter.getTexture().getHeight().toFloat(),
            HelicopterGame.WIDTH,
            HelicopterGame.HEIGHT - helicopter.getTexture().getHeight().toFloat()
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
            if (helicopter.getVelocity().x == 0F && helicopter.getVelocity().y == 0F){
                helicopter.setVelocity(arrayOf(-1F, 0.5F))
                scores[0]=0;
                scores[1]=0;
                text = "";
                comPaddle.changeDirection(arrayOf(true,false))
            }
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

    fun checkRoundFinished(): Boolean{
        if((helicopter.getPosition().x+helicopter.texture.width)<0){
            scores[0]+=1;
            return true;
        }
        if((helicopter.getPosition().x)>HelicopterGame.WIDTH){
            scores[1]+=1
            return true;
        }
        return false
    }

    fun paddleCollision(paddle: Paddle): Float{
        if(helicopter.getPosition().y>(paddle.getTexture().height/2-helicopter.texture.height/2)){
            return (-(paddle.getPosition().y +paddle.getTexture().height/2-helicopter.texture.height/2-helicopter.getPosition().y)/(paddle.getTexture().height/2+helicopter.texture.height/2))
        }
        else{
            return((helicopter.getPosition().y - paddle.getPosition().y -paddle.getTexture().height/2+helicopter.texture.height/2)/(paddle.getTexture().height/2-helicopter.texture.height/2))
        }
    }

    fun comPaddleController(){
        if (comPaddle.getVelocity().y==0.0F && scores[0]<winningScore && scores[1]< winningScore){
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
            if (comPaddle.getBounds().overlaps(helicopter.getBounds()) && (helicopter.getPosition().x > comPaddle.getPosition().x)){
                helicopter.changeVelocity(wallCollisions(), true, arrayOf(1F, paddleCollision(comPaddle)))
            }
            else if (playerPaddle.getBounds().overlaps(helicopter.getBounds()) && (helicopter.getPosition().x + helicopter.texture.width) < (playerPaddle.getPosition().x + playerPaddle.getTexture().width)){
                helicopter.changeVelocity(wallCollisions(), true, arrayOf(-1F, paddleCollision(playerPaddle)))

            }
            else{
                helicopter.changeVelocity(wallCollisions(), false, arrayOf(0F, 0F))
            }
            playerPaddle.update(deltaTime);
            comPaddle.update(deltaTime);
            helicopter.update(deltaTime);
        }
        else{
            if(scores[0]>=winningScore){
                text = "You Win!! Press to play again"
                helicopter.setVelocity(arrayOf(0F,0F))
                playerPaddle.setPosition(HelicopterGame.WIDTH-50F, 125.5F)
                playerPaddle.changeDirection(arrayOf(false, false))
                comPaddle.setPosition(50F, 125.5F)
                comPaddle.changeDirection(arrayOf(false, false))
            }
            if(scores[1]>=winningScore){
                text = "You Lose. Press to play again"
                helicopter.setVelocity(arrayOf(0F,0F))
                playerPaddle.setPosition(HelicopterGame.WIDTH-50F, 125.5F)
                playerPaddle.changeDirection(arrayOf(false, false))
                comPaddle.setPosition(50F, 125.5F)
                comPaddle.changeDirection(arrayOf(false, false))
            }
            helicopter.setPosition(HelicopterGame.WIDTH/2-helicopter.texture.width/2, HelicopterGame.HEIGHT/2-helicopter.texture.height/2)
        }
    }
    override fun render(sprites: SpriteBatch) {
        sprites.setProjectionMatrix(camera.combined)
        sprites.begin()
        sprites.draw(background, 0F, 0F, HelicopterGame.WIDTH, HelicopterGame.HEIGHT)
        sprites.draw(
                helicopter.getSprite(),
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
        font.draw(sprites, scores[1].toString(), 200F, HelicopterGame.HEIGHT -20F)
        font.draw(sprites, scores[0].toString(), HelicopterGame.WIDTH -200F, HelicopterGame.HEIGHT -20F)
        font.draw(sprites, text, 130F, 200F)
        sprites.end()
        super.render(sprites)
    }
    override fun dispose() {
        super.dispose()
        background.dispose()
        helicopter.dispose()
        playerPaddle.dispose()
        comPaddle.dispose()
    }
}