package com.daniel.semarbeit.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Daniel
 */
public class Boot extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {        
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));        
        Scene scene = new Scene(root);     
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Player p = new Player();
        //p.play("T120 V0 I[Piano] G5q G5q V9 [Hand_Clap]q Rq T120 V0 I[Piano] G5q G5q V9 [Hand_Clap]q Rq T120 V0 I[Piano] G5q G5q V9 [Hand_Clap]q Rq T120 V0 I[Piano] G5q G5q V9 [Hand_Clap]q Rq");
        launch(args);
        
        
    }
    
}
