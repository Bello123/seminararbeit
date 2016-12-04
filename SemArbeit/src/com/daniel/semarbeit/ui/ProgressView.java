package com.daniel.semarbeit.ui;

import java.util.ArrayList;
import javafx.scene.shape.Arc;

/**
 *
 * @author Daniel
 */
public class ProgressView {
    
    private Arc arc;
    
    private String instrument;
    private ArrayList<String> notes;
    private double percent;
    private double length;
    private double startAngle;
    
    
    public ProgressView(Arc arc) {
        this.arc = arc;
    }

    public void update(String instrument, ArrayList<String> notes) {
        this.instrument = instrument;
        this.notes = notes;
        
        percent = (double)notes.size()/(double)127;
        length = 360*percent;
    }

    public void draw() {
        arc.setStartAngle(360-length);
        arc.setLength(length);
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
