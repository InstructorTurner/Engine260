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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author Steven Turner
 */
public class MainApplication extends Application{
    
    //gameplay view elements
    BorderPane gamePanel;
    GameManager gm;
    AnimationTimer loop;
    MovementState ms;
    Scene gameScene;
    
    //title screen elements
    BorderPane startPanel;
    Scene startScene;
    
   
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //configure screens
        setupStartView(stage);
        setupGameView(stage);
        
        startStartView(stage);

    }
    
    private void setupStartView(Stage stage){
        //set up Start screen
        startPanel = new BorderPane();
        Label title = new Label("Cube Adventure");
        Button startButton = new Button("start");
        
        startPanel.setCenter(title);
        startPanel.setBottom(startButton);
        
        startPanel.setPrefHeight(400);
        startPanel.setPrefWidth(400);
        
        startButton.setOnAction((e)->{
            startGameView(stage);
        });
        
        startScene = new Scene(startPanel);
    }
    
    private void startStartView(Stage stage){
        stage.setScene(startScene);
        stage.show();
    }
    
    private void setupGameView(Stage stage){
        //create the main container for our game elements
        gamePanel = new BorderPane();
        
        //set up a canvas to act as a level
        Canvas level = new Canvas(400,400);
        gamePanel.setCenter(level);
        GraphicsContext g = level.getGraphicsContext2D();
        
        //set up our loop
        ms = new MovementState();
        PlayerModel pm = new PlayerModel();
        PlayerView pv = new PlayerView(pm);
        PlayerController pc = new PlayerController(pm, pv, ms);
        gm = new GameManager(pc);
        //Create our Levels
        Level level1 = LevelContainer.createLevel1(pc);
        Level level2 = LevelContainer.createLevel2(pc);
        gm.setCurrentLevel(level1);
        gm.addLevel(level2);
        
        loop = new AnimationTimer(){ //animationTimer will call handle() once every 60th of a second once started
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
                gm.draw(g);
            }
        };
        
        //Set up a Pause Button and area for other controls
        HBox menu = new HBox();
        Button pauseButton = new Button("Pause");
        menu.getChildren().add(pauseButton);
        gamePanel.setTop(menu);
        
        //set pause button control
        pauseButton.setOnAction((e) -> {
            gm.togglePause();
        });
        
        gameScene = new Scene(gamePanel);
    }
    
    private void startGameView(Stage stage){

        stage.setScene(gameScene);
        
        //hook up keyboard input
        //Since we're watching the key release event on the scene, 
        //we should be reading input as long as the window is in focus
        gameScene.setOnKeyPressed((e) -> {
            if(e.getCode() == KeyCode.LEFT){
                ms.leftOn();
            }
            if(e.getCode() == KeyCode.RIGHT){
                ms.rightOn();
            }
            if(e.getCode() == KeyCode.Z){
                ms.jumpOn();
            }
        });
        gameScene.setOnKeyReleased((e) -> {
            if(e.getCode() == KeyCode.LEFT){
                ms.leftOff();
            }
            if(e.getCode() == KeyCode.RIGHT){
                ms.rightOff();
            }
            if(e.getCode() == KeyCode.Z){
                ms.jumpOff();
            }
        });
        
        //start the loop
        loop.start();
        
    }
   
    
}
