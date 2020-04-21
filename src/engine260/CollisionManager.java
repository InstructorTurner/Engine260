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
public class CollisionManager {


    public static boolean isColliding(PositionalObject primary, PositionalObject secondary){
    
        return (
                    primary.getRightSide() >= secondary.getLeftSide()
                    && primary.getLeftSide() <= secondary.getRightSide()
                    && primary.getBottomSide() >= secondary.getTopSide()
                    && primary.getTopSide() <= secondary.getBottomSide()
                );
    }
    
}
