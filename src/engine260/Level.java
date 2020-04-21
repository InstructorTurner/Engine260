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
public abstract class Level {
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
    Camera camera;
    
    //constructor
    public Level(){
        //set up player starting conditions
        startingX = 0;
        startingY = 0;
        goalReached = false;
        camera = new Camera();
    }
    
    //methods
    public abstract void initialize(); //should be used to create starting points, goal and create platforms / enemies
    public void createGoal(int x, int y){
        this.goal = new Goal(x, y, 10, 10);
        drawableObjects.add(goal);
    }
    public void setStart(int x, int y){
        startingX = x;
        startingY = y;
    }
    
    public void setPlayerController(PlayerController pc){
        this.playerController = pc;
        pc.restart(startingX, startingY);
    }
    
    public void update(){
        //update enemies and platforms
        for(Updateable updateObject : updateableObjects){
            updateObject.update();
        }
        
        //check player position and update camera
        camera.updatePosition(playerController.getPlayerBody());
    }
    
    //Now the level is just drawing itself, which is a little better
    public void draw(GraphicsContext g){        
        //draw level stuff
//        for(Drawable drawObject : drawableObjects){
//            drawObject.draw(g);
//        }
        camera.draw(g, drawableObjects);
        camera.draw(g, playerController);
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
    public boolean goalReached(){
        return goalReached;
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
    
    
    public void addPlatform(int x, int y, int width, int height){
        Platform platform = new Platform(x,y,width,height);
        platformList.add(platform);
        drawableObjects.add(platform);
    }
    
    public void addEnemy(Platform p){
        Enemy enemy = new Enemy(p);
        drawableObjects.add(enemy);
        updateableObjects.add(enemy);
        enemyList.add(enemy);
    }
    
    public void addPlatformWithEnemy(int x, int y, int width, int height){
        Platform platform = new Platform(x,y,width,height);
        platformList.add(platform);
        drawableObjects.add(platform);
        addEnemy(platform);
    }
    
    public void addBullet(Bullet b){
        drawableObjects.add(b);
        updateableObjects.add(b);
    }
}
