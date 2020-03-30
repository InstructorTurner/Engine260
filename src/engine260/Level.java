/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine260;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author Steven Turner
 */
public class Level {
    //attributes
    List<PositionalObject> backgroundObjects = new ArrayList<>();
    List<Drawable> drawableObjects = new ArrayList<>();
    PlayerController playerController;
    boolean goalReached;
    int startingX;
    int startingY;
    Goal goal;
    
    //constructor
    public Level(PlayerController pc){
        startingX = 0;
        startingY = 180;
        goalReached = false;
        playerController = pc;
        pc.restart(startingX, startingY);
        goal = new Goal(200, 190, 10, 10);
        backgroundObjects.add(goal);
        drawableObjects.add(goal);
        
        //create and add platforms
        Platform platform = new Platform(0,200,80,10);
        backgroundObjects.add(platform);
        drawableObjects.add(platform);
        platform = new Platform(120, 200, 100, 10);
        backgroundObjects.add(platform);
        drawableObjects.add(platform);
        
    }
    
    //methods
    public void update(){
        //update enemies and platforms
        
    }
    
    //Now the level is just drawing itself, which is a little better
    public void draw(GraphicsContext g){
                
        //draw level stuff
        for(Drawable drawObject : drawableObjects){
            drawObject.draw(g);
        }
        
        //draw win screen
        if(goalReached){
            g.fillText("You Win!", 50, 50);
        }
    }
    
    public List<PositionalObject> getBackgroundObjects(){
        return backgroundObjects;
    }
    
    public void setGoalReached(){
        goalReached = true;
    }
    
}
