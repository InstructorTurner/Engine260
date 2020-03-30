/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine260;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Steven Turner
 */
public class MainApplication extends Application{
    
   
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //create the main container for our game elements
        BorderPane rootPanel = new BorderPane();
        
        //set up a canvas to act as a level
        Canvas level = new Canvas(400,400);
        rootPanel.setCenter(level);
        GraphicsContext g = level.getGraphicsContext2D();
        
        //set up our loop
        MovementState ms = new MovementState();
        PlayerModel pm = new PlayerModel();
        PlayerView pv = new PlayerView(pm);
        PlayerController pc = new PlayerController(pm, pv, ms);
        GameManager gm = new GameManager(pc);
        Level level1 = new Level(pc);
        gm.setCurrentLevel(level1);
        
        AnimationTimer loop = new AnimationTimer(){ //animationTimer will call handle() once every 60th of a second once started
            public void handle(long time){
                //for now, we'll be clamping our loop down to the provided
                //60fps from AnimationTimer.
                
                //process input
                //There are possible problems with reading the current state from the
                //MovementState object, but we'll do that for now and deal with
                //it later.  The best solution will likely be to create a copy 
                //of the state and pass that into the model so that if it changes while the model
                //is running it won't affect anything
                
                //update models
                gm.update();
                
                //render
                //we'll move this to its own class later as well
                gm.draw(g);
            }
        };
        
             
        //plug FX stuff together to start the application
        Scene scene = new Scene(rootPanel);
        stage.setScene(scene);
        stage.show();
        
        //hook up keyboard input
        //Since we're watching the key release event on the scene, 
        //we should be reading input as long as the window is in focus
        scene.setOnKeyPressed((e) -> {
            if(e.getCode() == KeyCode.LEFT){
                ms.leftOn();
            }
            if(e.getCode() == KeyCode.RIGHT){
                ms.rightOn();
            }
            if(e.getCode() == KeyCode.SPACE){
                ms.jumpOn();
            }
        });
        scene.setOnKeyReleased((e) -> {
            if(e.getCode() == KeyCode.LEFT){
                ms.leftOff();
            }
            if(e.getCode() == KeyCode.RIGHT){
                ms.rightOff();
            }
            if(e.getCode() == KeyCode.SPACE){
                ms.jumpOff();
            }
        });
        
        //start the loop
        loop.start();
    }
   
    
}
