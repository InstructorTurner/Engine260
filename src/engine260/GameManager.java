/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine260;

import java.util.List;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author Steven Turner
 */
public class GameManager {
    
    //attributes
    Level currentLevel;
    PlayerController playerController;
    CollisionManager collisionManager;
    
    //constructor
    public GameManager(PlayerController pc){
        playerController = pc;
        collisionManager = new CollisionManager();
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
    
    public void setCurrentLevel(Level level){
        currentLevel = level;
    }
    
    public void draw(GraphicsContext g){
        currentLevel.draw(g);
    }
    
    private void checkCollisions(){
        PlayerModel model = playerController.getModel();
        List<PositionalObject> backgroundObjects = currentLevel.getBackgroundObjects();
        boolean playerOnGround = false;
        for(PositionalObject backgroundObject : backgroundObjects){
            if(collisionManager.isColliding(model.getFootPosition(), backgroundObject)){
                playerOnGround = true;
            }
            if(collisionManager.isColliding(model, backgroundObject)){
                if(backgroundObject instanceof Platform){
                    model.land(backgroundObject.getYPosition() - backgroundObject.getHeight());
                    playerOnGround = true;
                }
                
                if(backgroundObject instanceof Goal){
                    currentLevel.setGoalReached();
                }
            }
            
        }
        if(!playerOnGround && !playerController.getModel().hasJumped()){
            playerController.getModel().startFalling();
        }
    }
}
