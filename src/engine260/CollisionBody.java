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
public class CollisionBody extends PositionalObject{
    
    public CollisionBody(int width, int height, int x, int y){
        this.width = width;
        this.height = height;
        this.xPosition = x;
        this.yPosition = y;
    }
    
}
