/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine260;

import java.util.LinkedList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Steven Turner
 */
public class GameManager {
    
    //attributes
    Level currentLevel;
    List<Level> levelList;
    PlayerController playerController;
    boolean paused;
    boolean betweenLevels;
    boolean gameOver;
    int interstitialCount;
    Interstitial interstitial;
    
    //constructor
    public GameManager(PlayerController pc){
        playerController = pc;
        paused = false;
        betweenLevels = false;
        gameOver = false;
        interstitialCount = 0;
        
        levelList = new LinkedList<>();
    }
    
    //methods
    public void update(){
        //if we're between levels (you just beat a level
        if(betweenLevels){
            //count to 5 seconds to show an interstitial screen
            if(interstitialCount < 300){
                interstitialCount++;
            } else { //if the count is up...
                //turn off the interstitial and get back to the game
                betweenLevels = false;
                interstitialCount = 0;
            }
        }
        else if(!paused && !gameOver){
            //currentLevel.update();
            //update the player
            playerController.update();

            //update the level
            currentLevel.update();

            //check for Collisions
            checkCollisions(); 

            //check for goal reached
            if(currentLevel.goalReached()){
                goToNextLevel();
                playerController.restart(currentLevel.getStartingX(), currentLevel.getStartingY());
            }
        }
    }
    
    public void draw(GraphicsContext g){
        if(betweenLevels || gameOver){
            interstitial.draw(g);
        } else {
            drawBackground(g);
            currentLevel.draw(g);
            playerController.draw(g);
        }
    }
    
    public void setCurrentLevel(Level level){
        currentLevel = level;
    }
    
    public void addLevel(Level level){
        levelList.add(level);
    }
    
    
    private void drawBackground(GraphicsContext g){
        //draw background
        g.setFill(Color.WHITE);
        g.fillRect(0,0,400,400);
    }
    
    private void checkCollisions(){
        //get the objects to check collisions
        CollisionBody playerBody = playerController.getPlayerBody();
        CollisionBody playerFeet = playerController.getPlayerFeet();
        //set a flag so we know if the player walked off a platform without jumping
        boolean playerOnGround = false;
        
        //check for collisions on all the objects
        for(PositionalObject platform : currentLevel.getPlatforms()){
            //if the player's feet collide with anything, they're on solid ground
            if(CollisionManager.isColliding(playerFeet, platform)){
                playerOnGround = true;
            }
            //if the player's body is colliding with anything, handle that
            if(CollisionManager.isColliding(playerBody, platform)){
                playerController.land(platform.getYPosition() - platform.getHeight());
                playerOnGround = true;
            }
        }
        //if the player's feet never collided with anything
        if(!playerOnGround && !playerController.hasJumped()){
            playerController.startFalling();
        }
        
        //if the player has fallen off the stage
        if(playerBody.getYPosition() > 400){
            losePlayerLife();
        }
        
        //check goal collision
        if(CollisionManager.isColliding(playerBody, currentLevel.getGoal())){
            currentLevel.setGoalReached();
        }
        
        //check enemy collision
        for(Enemy enemy : currentLevel.getEnemies()){
            //if an enemy hits a player
            if(CollisionManager.isColliding(playerBody, enemy)){
                //player restarts
                losePlayerLife(); 
            }
            
            //if the enemy has walked off its home platform, turn it around
            if(!CollisionManager.isColliding(enemy, enemy.getHomePlatform())){
                enemy.reverseDirection();
            }
        }
    }
    
    private void losePlayerLife(){
        playerController.loseLife();
        if(playerController.getLives() > 0){
            playerController.restart(currentLevel.getStartingX(), currentLevel.getStartingY());
        } else {
            //game over
            interstitial = new Interstitial("Game Over");
            gameOver = true;
        }
    }

    public void togglePause() {
        paused = !paused;
    }
    
    private void goToNextLevel(){
        if(!levelList.isEmpty()){
            currentLevel = levelList.get(0);
            levelList.remove(0);
            betweenLevels = true;
            interstitial = new Interstitial("Next Level");
        }
    }
}
