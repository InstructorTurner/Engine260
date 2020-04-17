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
public class Goal extends PositionalObject implements Drawable{
    
    public Goal(int x, int y, int h, int w){
        this.xPosition = x;
        this.yPosition = y;
        this.width = w;
        this.height = h;
    }

    @Override
    public void draw(GraphicsContext g) {
        g.setFill(Color.GREEN);
        g.fillRect(this.getXPosition(), this.getYPosition(), this.getWidth(), this.getHeight());
    }

    @Override
    public void drawShifted(GraphicsContext g, int cameraX, int cameraY) {
        g.setFill(Color.GREEN);
        g.fillRect(this.getXPosition() - cameraX, this.getYPosition() - cameraY, this.getWidth(), this.getHeight());
    }
}
