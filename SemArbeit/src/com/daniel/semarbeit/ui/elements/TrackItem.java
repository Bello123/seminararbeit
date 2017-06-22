package com.daniel.semarbeit.ui.elements;

import com.daniel.semarbeit.user.Instruments;
import com.daniel.semarbeit.user.Notes;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Daniel
 */
public class TrackItem {

    private double width, height;
    private int note;
    private int instrument;
    private double length;

    public TrackItem(int instrument, int note, double length) {
        this.note = note;
        this.instrument = instrument;
        this.length = length;
        this.width = 180*length;
        this.height = 100;
    }
    
    public void draw(GraphicsContext gc, double posX, double posY) {
        gc.setFill(Color.BISQUE);
        gc.fillRect(posX, posY, width, height);
        gc.setStroke(Color.BURLYWOOD);
        gc.setLineWidth(1.5);
        gc.strokeRect(posX, posY, width, height);
        gc.setFill(Color.BLACK);
        gc.fillText(Instruments.getInstrumentName(instrument), posX+width/2, posY+height/2-10);
        gc.fillText(Notes.getNoteName(note), posX+width/2, posY+height/2);
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public int getNote() {
        return note;
    }

    public int getInstrument() {
        return instrument;
    }

    public double getLength() {
        return length;
    }
    
}
