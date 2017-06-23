package com.daniel.semarbeit.ui.elements;

import com.daniel.semarbeit.user.Instruments;
import com.daniel.semarbeit.user.Notes;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 *
 * @author Daniel
 */
public class TrackItem extends Canvas {

    private final GraphicsContext gc;
    private int note;
    private int instrument;
    private double length;

    public TrackItem(HBox parent, int instrument, int note, double length) {
        this.note = note;
        this.instrument = instrument;
        this.length = length;
        setWidth(180*length);
        setHeight(100);
        gc = getGraphicsContext2D();
        draw(0, 0);
        
        setOnDragOver(event -> {
            drawHighlighter(getDropSide(event.getX()));
        });
        setOnDragExited(event -> {
            draw(0, 0);
        });
        setOnDragDropped((DragEvent event) -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if(db.hasString()) {
                String[] elements = db.getString().split(" ");
                if(elements.length == 4) {
                    parent.getChildren().add(parent.getChildren().indexOf(this)+getDropSide(event.getX()), new TrackItem(parent, Integer.parseInt(elements[1]), Integer.parseInt(elements[2]), Double.parseDouble(elements[3])));
                    success = true;
                } else {
                    success = false;
                }                
            }
            event.setDropCompleted(success);

            event.consume();
        });
    }
    
    public void draw(double posX, double posY) {
        gc.setFill(Color.BISQUE);
        gc.fillRect(posX, posY, getWidth(), getHeight());
        gc.setStroke(Color.BURLYWOOD);
        gc.setLineWidth(1.5);
        gc.strokeRect(posX, posY, getWidth(), getHeight());
        gc.setFill(Color.BLACK);
        gc.fillText(Instruments.getInstrumentName(instrument), posX+getWidth()/2, posY+getHeight()/2-10);
        gc.fillText(Notes.getNoteName(note), posX+getWidth()/2, posY+getHeight()/2);
    }
    
    private int getDropSide(double mouseX) {
        mouseX += getLayoutX();
        System.out.println(mouseX + " " + getLayoutX());
        if(mouseX > getLayoutX() && mouseX < getLayoutX() + getWidth()/2) {
            return 0;
        } else if(mouseX > getLayoutX() + getWidth()/2 && mouseX < getLayoutX() + getWidth()) {
            return 1;
        } else {
            return -1;
        }
    }
    public void drawHighlighter(int side) {
        draw(0, 0);
        gc.setFill(Color.LIGHTGREEN);
        
        switch (side) {
            case 0:
                gc.fillRect(0, 0, 3, getHeight());
                break;
            case 1:
                gc.fillRect(getWidth()-3, 0, 3, getHeight());
                break;
        }
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
