package com.daniel.semarbeit.util;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author Daniel
 */
public class UIs {
    
    public static void centerStage(Stage s) {
        Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
        
        s.setY(visualBounds.getHeight()/2-s.getHeight()/2);
        s.setX(visualBounds.getWidth()/2-s.getWidth()/2);
    }
    
}
