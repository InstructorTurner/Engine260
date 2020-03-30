/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine260;

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
    PlayerController playerController;
    
    //constructor
    public GameManager(PlayerController pc){
        playerController = pc;
    }
    
    //methods
    public void update(){
        //currentLevel.update();
        //update the player
        playerController.update();
        
        //update the level
        currentLevel.update();
        
        //check for Collisions
        checkCollisions();
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
        List<PositionalObject> backgroundObjects = currentLevel.getBackgroundObjects();
        //set a flag so we know if the player walked off a platform without jumping
        boolean playerOnGround = false;
        
        //check for collisions on all the objects
        for(PositionalObject backgroundObject : backgroundObjects){
            //if the player's feet collide with anything, they're on solid ground
            if(CollisionManager.isColliding(playerFeet, backgroundObject)){
                playerOnGround = true;
            }
            //if the player's body is colliding with anything, handle that
            if(CollisionManager.isColliding(playerBody, backgroundObject)){
                //if it's a platform...
                if(backgroundObject instanceof Platform){
                    playerController.land(backgroundObject.getYPosition() - backgroundObject.getHeight());
                    playerOnGround = true;
                }
                //if it's the goal...
                if(backgroundObject instanceof Goal){
                    currentLevel.setGoalReached();
                }
            }
            
        }
        
        //if the player's feet never collided with anything
        if(!playerOnGround && !playerController.hasJumped()){
            playerController.startFalling();
        }
    }
}
