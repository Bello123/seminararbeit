package com.daniel.semarbeit.util;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 *
 * @author Daniel
 */
public class Transitions {
    
    public static void playFadeTransition(Node n, double duration, double from, double to) {
        FadeTransition ft = new FadeTransition(Duration.millis(duration), n);
        ft.setFromValue(from);
        ft.setToValue(to);
        
        ft.play();
    }
    
}
