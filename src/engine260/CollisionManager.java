/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine260;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Steven Turner
 */
public class CollisionManager {
    //attributes
    List<CollisionAction> actions;
    
    //constructor
    public CollisionManager(){
        actions = new ArrayList<>();
    }
    
    //methods
    public void addCollisionListener(CollisionAction action){
        actions.add(action);
    }
    public void removeCollisionListener(CollisionAction action){
        actions.remove(action);
    }
    public boolean isColliding(PositionalObject primary, PositionalObject secondary){
    
        return (
                    primary.getRightSide() >= secondary.getLeftSide()
                    && primary.getLeftSide() <= secondary.getRightSide()
                    && primary.getBottomSide() >= secondary.getTopSide()
                    && primary.getTopSide() <= primary.getBottomSide()
                );
    }
    
    public void handleCollisions(PositionalObject primary, List<PositionalObject> collisionList){
        for(PositionalObject other : collisionList){
            if(isColliding(primary, other)){
                for(CollisionAction action : actions){
                    action.handle(primary, other);
                }
            }
        }
    }
}
