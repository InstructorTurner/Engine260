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
public abstract class PositionalObject {
    //attributes
    protected int xPosition;
    protected int yPosition;
    protected int width;
    protected int height;
    
    //constructor

    
    //methods
    public int getXPosition(){
        return xPosition;
    }
    public int getYPosition(){
        return yPosition;
    }
    public void setXPosition(int x){
        xPosition = x;
    }
    public void setYPosition(int y){
        yPosition = y;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public void setWidth(int w){
        width = w;
    }
    public void setHeight(int h){
        height = h;
    }
    public int getLeftSide(){
        return xPosition;
    }
    public int getRightSide(){
        return xPosition + width;
    }
    public int getTopSide(){
        return yPosition;
    }
    public int getBottomSide(){
        return yPosition + height;
    }
}
