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
public class PhysicsRules {
    //attributes
    private int friction;
    private int gravity;
    private int acceleration;
    private int maxSpeed;
    
    //constructor
    public PhysicsRules(int f, int g, int a, int ms){
        friction = f;
        gravity = g;
        acceleration = a;
        maxSpeed = ms;
    }
    
    
    //methods
    public int getFriction(){
        return friction;
    }
    public int getGravity(){
        return gravity;
    }
    public int getAcceleration(){
        return acceleration;
    }
    public int getMaxSpeed(){
        return maxSpeed;
    }
}
