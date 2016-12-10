package com.daniel.semarbeit.ui.elements;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Daniel
 */
public class TrackItem {

    private double width, height;
    private String note;
    private String instrument;

    public TrackItem(String instrument, String note) {
        this.note = note;
        this.instrument = instrument;
        this.width = 180;
        this.height = 100;
    }
    
    public void draw(GraphicsContext gc, double posX, double posY) {
        gc.setFill(Color.BISQUE);
        gc.fillRect(posX, posY, width, height);
        gc.setStroke(Color.BURLYWOOD);
        gc.setLineWidth(1.5);
        gc.strokeRect(posX, posY, width, height);
        gc.setFill(Color.BLACK);
        gc.fillText(instrument, posX+width/2, posY+height/2-10);
        gc.fillText(note, posX+width/2, posY+height/2);
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public String getNote() {
        return note;
    }

    public String getInstrument() {
        return instrument;
    }
    
}
