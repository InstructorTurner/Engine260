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
public class PlayerController implements Updateable, Drawable{
    //attributes
    private PlayerModel pm;
    private PlayerView pv;
    private MovementState ms;
    
    //constructor
    public PlayerController(PlayerModel model, PlayerView view, MovementState movement){
        pm = model;
        pv = view;
        ms = movement;
    }

    @Override
    public void update() {
        //handle movement
        handleMovement();
        
        //actually move
        pm.update();
    }
    
    private void handleMovement(){
        if(ms.getLeft()){
            pm.moveLeft();
        }
        if(ms.getRight()){
            pm.moveRight();
        }
        
        if(!ms.getJump() && pm.hasJumped()){
            pm.startFalling();
        }

        if(ms.getJump() && pm.isGrounded()){ //if we're trying to jump and we're able to...
            pm.jump();
        }
        
        //handle friction
        if(ms.noDirectionalInput()){
            pm.slowDown();
        }
    }

    @Override
    public void draw(GraphicsContext g) {
        pv.draw(g);
    }
    
    public void restart(int x, int y){
        pm.restart(x, y);
    }
    
    public PositionalObject getCollisionArea(){
        return pm;
    }
    
}
