/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine260;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Steven Turner
 */
public class GameManager {
    
    //attributes
    Level currentLevel;
    PlayerController playerController;
    boolean paused;
    
    //constructor
    public GameManager(PlayerController pc){
        playerController = pc;
        paused = false;
    }
    
    //methods
    public void update(){
        if(!paused){
        //currentLevel.update();
        //update the player
        playerController.update();
        
        //update the level
        currentLevel.update();
        
        //check for Collisions
        checkCollisions(); 
        }
    }
    
    public void draw(GraphicsContext g){
        drawBackground(g);
        currentLevel.draw(g);
        playerController.draw(g);
    }
    
    public void setCurrentLevel(Level level){
        currentLevel = level;
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
        }
    }

    public void togglePause() {
        paused = !paused;
    }
}
