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
    List<Drawable> drawableObjects = new ArrayList<>();
    List<Updateable> updateableObjects = new ArrayList<>();
    List<Enemy> enemyList = new ArrayList<>();
    List<Platform> platformList = new ArrayList<>();
    PlayerController playerController;
    boolean goalReached;
    int startingX;
    int startingY;
    Goal goal;
    
    //constructor
    public Level(PlayerController pc){
        //set up player starting conditions
        startingX = 0;
        startingY = 180;
        goalReached = false;
        playerController = pc;
        pc.restart(startingX, startingY);
        
        //add goal
        goal = new Goal(200, 190, 10, 10);
        drawableObjects.add(goal);
        
        //create and add platforms
        addPlatform(0,200,80,10);
        addPlatformWithEnemy(120, 200, 100, 10);
        
    }
    
    //methods
    public void update(){
        //update enemies and platforms
        for(Updateable updateObject : updateableObjects){
            updateObject.update();
        }
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
    
    public List<Platform> getPlatforms(){
        return platformList;
    }
    public List<Enemy> getEnemies(){
        return enemyList;
    }
    public Goal getGoal(){
        return goal;
    }
    
    public void setGoalReached(){
        goalReached = true;
    }
    
    public int getStartingX(){
        return startingX;
    }
    public int getStartingY(){
        return startingY;
    }
    
    
    private void addPlatform(int x, int y, int width, int height){
        Platform platform = new Platform(x,y,width,height);
        platformList.add(platform);
        drawableObjects.add(platform);
    }
    
    private void addEnemy(Platform p){
        Enemy enemy = new Enemy(p);
        drawableObjects.add(enemy);
        updateableObjects.add(enemy);
        enemyList.add(enemy);
    }
    
    private void addPlatformWithEnemy(int x, int y, int width, int height){
        Platform platform = new Platform(x,y,width,height);
        platformList.add(platform);
        drawableObjects.add(platform);
        addEnemy(platform);
    }
}
