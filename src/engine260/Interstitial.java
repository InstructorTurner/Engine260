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
public class Interstitial implements Drawable {
    //attributes
    String text;
    
    //constructor
    public Interstitial(String text){
        this.text = text;
    }

    @Override
    public void draw(GraphicsContext g) {
        g.setFill(Color.WHITE);
        g.fillRect(0, 0, 400, 400);
        g.setFill(Color.BLACK);
        g.fillText(text, 50, 200);
    }

    @Override
    public void drawShifted(GraphicsContext g, int cameraX, int cameraY) {
        draw(g);
    }
    
}
