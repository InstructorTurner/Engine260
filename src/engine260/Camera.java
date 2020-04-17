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
public class Camera {
    //attributes
    int xPosition;
    int yPosition;
    
    //constructor
    public Camera(){
        this.xPosition = 0;
        this.yPosition = 0;
    }
    
    //methods
    public int getXPosition(){
        return xPosition;
    }
    public int getYPosition(){
        return yPosition;
    }
    public void setPosition(int x, int y){
        xPosition = x;
        yPosition = y;
    }
    public void draw(GraphicsContext g, List<Drawable> drawableObjects){
        for(Drawable drawable : drawableObjects){
            //drawable.draw(g);
            drawable.drawShifted(g, xPosition, yPosition);
        }
    }
    public void draw(GraphicsContext g, Drawable drawableObject){
        //drawableObject.draw(g);
        drawableObject.drawShifted(g, xPosition, yPosition);
    }
    public void updatePosition(CollisionBody trackedBody){
        //canvas is 400 x 400
        //camera position is based on top left 0,0
        //if player is over 300px past camera or below 100px past camera, update camera position
        if(trackedBody.getXPosition() - xPosition > 300){
            xPosition = trackedBody.getXPosition() - 300;
        }
        if(trackedBody.getXPosition() - xPosition < 100){
            xPosition = trackedBody.getXPosition() - 100;
        }
    }
}
