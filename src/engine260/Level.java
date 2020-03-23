/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine260;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Steven Turner
 */
public class Level {
    //attributes
    List<Platform> platforms = new ArrayList<>();
    Player p;
    
    //constructor
    public Level(Player thePlayer){
        p = thePlayer;
        platforms.add(new Platform(0, 200, 80, 10));
        platforms.add(new Platform(120, 200, 100, 10));
    }
    
    //methods
    public void update(){
        p.update();
        
        //as stated below, a Level shouldn't be in charge of handling collisions.
        //We'll need to change this.
        handleCollisions();
    }
    
    //Hmm... letting the Level manage drawing seems like a violation of the Single Responsibility principle.
    //A level should just manage the items inside of a level
    //We'll need to think of a way to separate out all the drawing logic somewhere else
    //...Perhaps a "Drawable" interface?
    public void draw(GraphicsContext g){
        g.setFill(Color.WHITE);
        g.fillRect(0,0,400,400);
        
        g.setFill(Color.BLUE);
        for(Platform platform : platforms){
            g.fillRect(platform.getXPosition(), platform.getYPosition(), platform.getWidth(), platform.getHeight());
        }
        
        
        g.setFill(Color.RED);
        g.fillRect(p.getXPosition(), p.getYPosition(), p.WIDTH, p.HEIGHT);
    }
    
    //Handling collisions DEFINITELY shouldn't be managed by the level,
    //but let's just get it working before we worry about refactoring
    private void handleCollisions(){
        for(Platform platform : platforms){
            //if the player overlaps with a platform, it's colliding
            if(isColliding(p, platform)){
                p.land(platform.getYPosition() - platform.getHeight()); //land the player on the top of the platform
            }
        }
    }
    //Programming to concretions instead of abstractions, also bad.  This needs to be changed later.
    private boolean isColliding(Player p, Platform platform){
        int playerRightSide = p.getXPosition() + p.WIDTH;
        int playerLeftSide = p.getXPosition();
        int playerTopSide = p.getYPosition();
        int playerBottomSide = p.getYPosition() + p.HEIGHT;
        
        int platformRightSide = platform.getXPosition() + platform.getWidth();
        int platformLeftSide = platform.getXPosition();
        int platformTopSide = platform.getYPosition();
        int platformBottomSide = platform.getYPosition() + platform.getHeight();
        
        return (
                playerRightSide >= platformLeftSide
                && playerLeftSide <= platformRightSide
                && playerBottomSide >= platformTopSide
                && playerTopSide <= platformBottomSide
                );
    }
    
}
