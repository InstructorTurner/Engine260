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
public class PlayerModel extends PositionalObject implements  Updateable, HorizontalMover, VerticalMover {
    //attributes
    //Movement and Position Attributes
    private int xVelocity;
    private int yVelocity;
    private int jumpTimer;
    private boolean falling;
    private boolean jumping;
    private PhysicsRules physics;
    private int lives;
    
    //constants
    private final int JUMPMAX;

    
    //constructor
    public PlayerModel(){
        //set PositionalObject variables
        width = 10;
        height = 10;
        xPosition = 0;
        yPosition = 0;
        physics = new PhysicsRules(1, 1, 1, 3);
        JUMPMAX = 30; //maximum frames the player can jump
        
        //set initial position
        xVelocity = 0;
        yVelocity = 0;
        jumpTimer = 0;
        falling = false;
        jumping = false;
        
        //set initial lives
        lives = 3;
        
    }
    
    //methods
    @Override
    public void moveLeft(){
        xVelocity -= physics.getAcceleration();
        if(xVelocity < -physics.getMaxSpeed()) xVelocity = -physics.getMaxSpeed();
    }
    @Override
    public void moveRight(){
        xVelocity += physics.getAcceleration();
        if(xVelocity > physics.getMaxSpeed()) xVelocity = physics.getMaxSpeed();
    }
    @Override
    public void moveUp(){
        yVelocity -= physics.getAcceleration();
        if(yVelocity < -physics.getMaxSpeed()) yVelocity = -physics.getMaxSpeed();
    }
    @Override
    public void moveDown(){
        yVelocity += physics.getAcceleration();
        if(yVelocity > physics.getMaxSpeed()) yVelocity = physics.getAcceleration();
    }
    public void slowDown(){
        //we're applying friction here
        
        if(xVelocity < 0){
            //if you were moving left, we slow you down by adding positive numbers
            xVelocity += physics.getFriction();
            //but friction doesn't make you move backwards, so we need to clamp it
            if(xVelocity > 0) xVelocity = 0;
        }else if(xVelocity > 0) {
            //if you're moving right, we slow you down by subtracting friction
            xVelocity -= physics.getFriction();
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
        
        //if nothing is below player, they should start falling
        if(falling){ //check falling movement before jump movement, as jump can put you in a falling state and we don't want to erase jump progress
            moveDown();
        }
        if(jumping){
            moveUp();
            jumpTimer++;
            if(jumpTimer >= JUMPMAX) {
                startFalling();
            }
        }
        
        
        move();
    }
    
    public void land(int newYPosition){
        if(falling){
            falling = false;
            jumping = false;
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
    
    public void jump(){
        jumping = true;
        falling = false;
    }
    public boolean hasJumped(){
        return jumpTimer > 0 && !falling;
    }
    public void startFalling(){
        jumpTimer = 0;
        falling = true;
        jumping = false;
    }
    public boolean isGrounded(){
        return jumping == false && jumpTimer < JUMPMAX && !falling;
    }
    
    //manage lives
    public void loseLife(){
        lives--;
    }
    public int getLives(){
        return lives;
    }
    
    //shoot bullets
    public Bullet shoot(){
        return new Bullet(this.xPosition, this.yPosition);
    }

    
}
