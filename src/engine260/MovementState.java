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
public class MovementState {
    //attributes
    private boolean left;
    private boolean right;
    private boolean jump;
    //constructor
    public MovementState(){
        left = false;
        right = false;
        jump = false;
    }
    //methods
    public void leftOn(){
        left = true;
    }
    public void leftOff(){
        left = false;
    }
    public void rightOn(){
        right = true;
    }
    public void rightOff(){
        right = false;
    }
    public void jumpOn(){
        jump = true;
    }
    public void jumpOff(){
        jump = false;
    }
    
    public boolean getLeft(){return left;}
    public boolean getRight(){return right;}
    public boolean getJump(){return jump;}
    
    public boolean noDirectionalInput(){
        return !getLeft() && !getRight();
    }
    

}
