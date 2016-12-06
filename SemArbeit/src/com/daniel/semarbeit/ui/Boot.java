package com.daniel.semarbeit.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.daniel.semarbeit.user.NoteSet;

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
        
        NoteSet ns = new NoteSet();
        ns.deserialize("C:\\Users\\Daniel\\Desktop\\seminararbeit_notes.mc");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
