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
public class LevelContainer {
    
    public static Level createLevel1(PlayerController pc){
        Level level1 = new Level(){
            @Override
            public void initialize(){
                createGoal(200, 190);
                setStart(0,180);
                addPlatform(0,200,80,10);
                addPlatformWithEnemy(120, 200, 100, 10);
            }
        };
        level1.setPlayerController(pc);
        level1.initialize();
        return level1;
    }
    
    public static Level createLevel2(PlayerController pc){
        Level level2 = new Level(){
            @Override
            public void initialize(){
                createGoal(300, 300);
                setStart(0, 300);
                addPlatform(0, 320, 50, 10);
                addPlatformWithEnemy(100, 350, 50, 10);
                addPlatformWithEnemy(250, 310, 150, 10);
            }
        };
        level2.setPlayerController(pc);
        level2.initialize();
        return level2;
    }
}
