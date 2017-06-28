package com.daniel.semarbeit.util;

import java.io.File;
import java.util.Arrays;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 *
 * @author Daniel
 */
public class Dialogs {
    
    public static String chooseFileDialog(String title, String...extensions) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        fileChooser.getExtensionFilters().add(new ExtensionFilter(Arrays.toString(extensions), extensions));
        
        File f = fileChooser.showOpenDialog(null);
        if(f == null) {
            return "";
        } else {
            return f.getAbsolutePath();
        }        
    }
    
    public static String createFileDialog(String title, String...extensions) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        fileChooser.getExtensionFilters().add(new ExtensionFilter(Arrays.toString(extensions), extensions));
        
        File f = fileChooser.showSaveDialog(null);
        if(f == null) {
            return "";
        } else {
            return f.getAbsolutePath();
        }  
    }
    
    public static void alert(String title, String message, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.setContentText(content);
        alert.show();
    }
    
}
