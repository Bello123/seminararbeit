package com.daniel.semarbeit.ui;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.scene.shape.Arc;
import javafx.util.Duration;

/**
 *
 * @author Daniel
 */
public class ProgressView {
    
    private Arc arc;
    private FadeTransition arcProgressFadeIn;
    
    private String instrument;
    private ArrayList<String> notes;
    private double percent;
    private double length;

    public ProgressView(Arc arc) {
        this.arc = arc;
        
        arcProgressFadeIn = new FadeTransition(Duration.millis(500), arc);
        arcProgressFadeIn.setFromValue(0);
        arcProgressFadeIn.setToValue(1);
        arcProgressFadeIn.setCycleCount(1);
        arcProgressFadeIn.setAutoReverse(true);   
    }

    public void update(String instrument, ArrayList<String> notes) {
        this.instrument = instrument;
        this.notes = notes;
        
        percent = (double)notes.size()/(double)127;
        length = 360*percent;
    }

    public void draw() {
        Thread t = new Thread(new ProgressAnimation(arc, length));
        t.setDaemon(true);
        t.start();
        arcProgressFadeIn.play();
    }

    public String getInstrument() {
        return instrument;
    }

    public ArrayList<String> getNotes() {
        return notes;
    }

    public double getPercent() {
        return percent*100;
    }

}

class ProgressAnimation implements Runnable {

    private final double ANIMATION_LENGTH = 150;
    private Arc arc;
    private double length;

    public ProgressAnimation(Arc arc, double length) {
        this.arc = arc;
        this.length = length;
    }
    
    @Override
    public void run() {
        try {
            for(int i=0;i<=length;i++) {
                arc.setStartAngle(360-i);
                arc.setLength(i);
                Thread.sleep((long)(ANIMATION_LENGTH/(length-i)));
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ProgressAnimation.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            arc.setStartAngle(360-length);
            arc.setLength(length);
        }
    }
    
}