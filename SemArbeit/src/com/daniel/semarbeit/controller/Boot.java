package com.daniel.semarbeit.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
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
        Logger.getGlobal().setLevel(Level.SEVERE);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/daniel/semarbeit/ui/FXMLDocument.fxml"));
        Parent root = loader.load();      
        Scene scene = new Scene(root);     
        stage.setOnCloseRequest(event -> {
            ((FXMLDocumentController)loader.getController()).closeAndSave();
        });
        stage.setScene(scene);
        stage.show();      
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);       
    }
    
}
