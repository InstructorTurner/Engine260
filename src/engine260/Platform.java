/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine260;

/**
 *
 * @author Steven Turner
 */
public class Platform {
    //attributes
    private int xPosition, yPosition, width, height;
    
    //constructor
    public Platform(int xPosition, int yPosition, int width, int height){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.width = width;
        this.height = height;
    }
    
    //methods
    public void update(){
        
    }
    public int getXPosition(){
        return xPosition;
    }
    public int getYPosition(){
        return yPosition;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
}
