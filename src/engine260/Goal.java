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
public class Goal extends PositionalObject{
    
    public Goal(int x, int y, int h, int w){
        this.xPosition = x;
        this.yPosition = y;
        this.width = w;
        this.height = h;
    }
}
