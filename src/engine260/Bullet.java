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
public class Bullet extends CollisionBody implements Drawable, Updateable{
    
    public Bullet(int x, int y){
        super(3, 3, x, y);
    }
    
    @Override
    public void draw(GraphicsContext g) {
        g.setFill(Color.BLACK);
        g.fillOval(this.getXPosition(), this.getYPosition(), this.getWidth(), this.getHeight());
    }

    @Override
    public void drawShifted(GraphicsContext g, int cameraX, int cameraY) {
        g.setFill(Color.BLACK);
        g.fillOval(this.getXPosition() - cameraX, this.getYPosition() - cameraY, this.getWidth(), this.getHeight());
    }

    @Override
    public void update() {
        //for demo purposes, we'll just shoot right
        this.xPosition += 2;
    }
    
}
