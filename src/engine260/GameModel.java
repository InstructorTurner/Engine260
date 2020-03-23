/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine260;

import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author Steven Turner
 */
public class GameModel {
    
    //GameModel has been reconfigured to let the currentLevel manage what's happening
    //We'll give GameModel more functionality later when it needs to manage multiple levels
    
    
    //attributes
    Level currentLevel;
    
    //constructor
    public GameModel(){

    }
    
    //methods
    public void update(){
        currentLevel.update();
    }
    
    public void setCurrentLevel(Level level){
        currentLevel = level;
    }
    
    public void draw(GraphicsContext g){
        currentLevel.draw(g);
    }
}
