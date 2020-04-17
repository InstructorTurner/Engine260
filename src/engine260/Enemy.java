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
 * 
 * This is an extremely simple enemy that walks back and forth.  It's given
 * a "home platform" on which it will walk.  It's starting position is determined
 * by that platform, and it's placed so that it just barely collides with the platform.
 * If it ceases to collide with the platform, that means it's walked off, so 
 * the GameManager will turn it around.
 * 
 * A more complex enemy may have more similarities with a player, such as specific
 * collision areas, gravity, velocity and so on.
 */
public class Enemy extends PositionalObject implements Drawable, Updateable, HorizontalMover{
    //attributes
    boolean movingLeft;
    Platform homePlatform;  //to reduce the strain of collision checks, these enemies have a "home platform" that they're expected to keep in contact with
    
    public Enemy(Platform home){
        homePlatform = home;
        this.width = 5;
        this.height = 5;
        //place enemy on their home platform
        this.xPosition = homePlatform.getXPosition();
        this.yPosition = homePlatform.getYPosition() - height + 1;
        
        movingLeft = true;
    }

    @Override
    public void draw(GraphicsContext g) {
        g.setFill(Color.BROWN);
        g.fillOval(xPosition, yPosition, width, height);
    }
    
    public void drawShifted(GraphicsContext g, int cameraX, int cameraY) {
        g.setFill(Color.BROWN);
        g.fillOval(xPosition - cameraX, yPosition - cameraY, width, height);
    }
    

    @Override
    public void update() {
        if(movingLeft){
            moveLeft();
        } else {
            moveRight();
        }
    }

    @Override
    public void moveLeft() {
        this.xPosition -= 1;
    }

    @Override
    public void moveRight() {
        this.xPosition += 1;
    }
    
    public void reverseDirection(){
        movingLeft = !movingLeft;
    }
    
    public Platform getHomePlatform(){
        return homePlatform;
    }

}
