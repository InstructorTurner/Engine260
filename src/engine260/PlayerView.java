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
public class PlayerView implements Drawable{
    //attributes
    private PlayerModel pm;
    
    public PlayerView(PlayerModel model){
        this.pm = model;
    }

    @Override
    public void draw(GraphicsContext g) {
        g.setFill(Color.RED);
        g.fillRect(pm.getXPosition(), pm.getYPosition(), pm.getWidth(), pm.getHeight());
    }
    
}