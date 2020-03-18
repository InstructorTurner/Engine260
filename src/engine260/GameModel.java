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
public class GameModel {
    
    //attributes
    Player player;
    
    //constructor
    public GameModel(Player p){
        player = p;
    }
    
    //methods
    public void update(){
        player.update();
    }
    
}
