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
public class Platform extends PositionalObject implements Drawable{
    //attributes
    
    //constructor
    public Platform(int xPosition, int yPosition, int width, int height){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.width = width;
        this.height = height;
    }
    
    //methods

    @Override
    public void draw(GraphicsContext g) {
        g.setFill(Color.BLUE);
        g.fillRect(this.getXPosition(), this.getYPosition(), this.getWidth(), this.getHeight());
    }

}
