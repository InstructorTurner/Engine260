/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine260;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Steven Turner
 */
public class Level {
    //attributes
    List<PositionalObject> backgroundObjects = new ArrayList<>();
    PlayerController playerController;
    boolean goalReached;
    int startingX;
    int startingY;
    Goal goal;
    CollisionManager collisionManager;
    
    //constructor
    public Level(PlayerController pc){
        startingX = 0;
        startingY = 180;
        goalReached = false;
        playerController = pc;
        pc.restart(startingX, startingY);
        goal = new Goal(200, 190, 10, 10);
        backgroundObjects.add(goal);
        backgroundObjects.add(new Platform(0, 200, 80, 10));
        backgroundObjects.add(new Platform(120, 200, 100, 10));
        
        //We should be passing in the collisionManager, but we'll worry about that
        //when it makes sense to.  I should try to not get too ahead of myself.
        collisionManager = new CollisionManager();
        
        //set up collisionActions using Lambda Notation
        collisionManager.addCollisionListener((primary, other) ->{
            if(primary instanceof PlayerModel && other instanceof Platform){
                PlayerModel model = (PlayerModel) primary;
                Platform platform = (Platform) other;
                model.land(platform.getYPosition() - platform.getHeight());
            }
            if(primary instanceof PlayerModel && other instanceof Goal){
                goalReached = true;
            }
        });
    }
    
    //methods
    public void update(){
        playerController.update();
        
        //Check collisions for the level
        collisionManager.handleCollisions(playerController.getCollisionArea(), backgroundObjects);
    }
    
    //Now the level is just drawing itself, which is a little better
    //We may need to still tweak this in the future
    public void draw(GraphicsContext g){
        //draw background
        g.setFill(Color.WHITE);
        g.fillRect(0,0,400,400);
        
        //draw level stuff
        for(PositionalObject item : backgroundObjects){
            if(item instanceof Platform){
                g.setFill(Color.BLUE);
            }
            if(item instanceof Goal){
                g.setFill(Color.GREEN);
            }
            g.fillRect(item.getXPosition(), item.getYPosition(), item.getWidth(), item.getHeight());
        }
        
        //draw player
        playerController.draw(g);
        
        //draw win screen
        if(goalReached){
            g.fillText("You Win!", 50, 50);
        }
    }
    

    
}
