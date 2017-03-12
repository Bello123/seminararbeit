package com.daniel.semarbeit.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;

/**
 *
 * @author Daniel
 */
public class Dialogs {
    
    public static String chooseFileDialog(String title) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        return fileChooser.showOpenDialog(null).getAbsolutePath();
    }
    
    public static void alert(String title, String message, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.setContentText(content);
        alert.show();
    }
    
}
