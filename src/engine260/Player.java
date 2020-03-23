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
public class Player {
    //attributes
    //Movement and Position Attributes
    private int xPosition;
    private int yPosition;
    private int xVelocity;
    private int yVelocity;
    private int jumpTimer;
    private boolean falling;
    
    //constants
    private final int JUMPMAX;
    private final int ACCELERATION;
    private final int FRICTION;
    public final int WIDTH;
    public final int HEIGHT;
    private final int MAXSPEED;
    private final int GRAVITY;
    
    //Control Attributes
    private MovementState ms;
    
    //constructor
    public Player(MovementState state){
        //set constants
        MAXSPEED = 5;
        WIDTH = 10;
        HEIGHT = 10;
        ACCELERATION = 1;
        FRICTION = 1;
        GRAVITY = 1;
        JUMPMAX = 30; //maximum frames the player can jump
        
        //set initial position
        xPosition = 50;
        yPosition = 150;
        xVelocity = 0;
        yVelocity = 0;
        jumpTimer = 0;
        falling = false;
        
        //hook up controls from keyboard
        ms = state;
    }
    
    //methods
    public void moveLeft(){
        xVelocity -= ACCELERATION;
        if(xVelocity < -MAXSPEED) xVelocity = -MAXSPEED;
    }
    public void moveRight(){
        xVelocity += ACCELERATION;
        if(xVelocity > MAXSPEED) xVelocity = MAXSPEED;
    }
    public void moveUp(){
        yVelocity -= ACCELERATION;
        if(yVelocity < -MAXSPEED) yVelocity = -MAXSPEED;
    }
    public void moveDown(){
        yVelocity += ACCELERATION;
        if(yVelocity > MAXSPEED) yVelocity = MAXSPEED;
    }
    public void slowDown(){
        //we're applying friction here
        
        if(xVelocity < 0){
            //if you were moving left, we slow you down by adding positive numbers
            xVelocity += FRICTION;
            //but friction doesn't make you move backwards, so we need to clamp it
            if(xVelocity > 0) xVelocity = 0;
        }else if(xVelocity > 0) {
            //if you're moving right, we slow you down by subtracting friction
            xVelocity -= FRICTION;
            //once again, can't have friction move you the opposite direction
            if(xVelocity < 0) xVelocity = 0;
        }
        //we used two checks here because if the velocity is right at 0, we don't need to do anything.
    }

    
    public void move(){
        xPosition += xVelocity;
        yPosition += yVelocity;
    }
    
    public void update(){
        //We'll read the MovementState here and perform the required actions
        //We'll probably want to move Control specific code to a new class
        //in the future to make our code more reusable
        
        if(ms.getLeft()){
            moveLeft();
        }
        if(ms.getRight()){
            moveRight();
        }
        
        if(!ms.getJump() && jumpTimer > 0 && !falling){
            jumpTimer = 0;
            falling = true;
        }
        
        if(falling){ //check falling movement before jump movement, as jump can put you in a falling state and we don't want to erase jump progress
            moveDown();
        }
        
        if(ms.getJump() && jumpTimer < JUMPMAX && !falling){ //if we're trying to jump and we're able to...
            moveUp();
            jumpTimer++;
            if(jumpTimer >= JUMPMAX) {
                falling = true;
                jumpTimer = 0;
            }
        }
        
        //handle friction
        if(ms.noDirectionalInput()){
            slowDown();
        }
        
        move();
    }
    
    public int getXPosition(){
        return xPosition;
    }
    public int getYPosition(){
        return yPosition;
    }
    
    public void land(int newYPosition){
        if(falling){
            falling = false;
            jumpTimer = 0;
            yVelocity = 0;
            yPosition = newYPosition;
        }
    }
    
    public void restart(int x, int y){
        xPosition = x;
        yPosition = y;
        xVelocity = 0;
        yVelocity = 0;
        falling = false;
        jumpTimer = 0;
    }
}
