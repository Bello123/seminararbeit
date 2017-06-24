package com.daniel.semarbeit.ui.elements;

import com.daniel.semarbeit.user.Instruments;
import com.daniel.semarbeit.user.Notes;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author Daniel
 */
public class TrackItem extends Canvas {

    private final int LEFT_SIDE = 0, RIGHT_SIDE = 1;
    
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
        draw();
        
        setOnDragOver(event -> {
            drawHighlighter(getDropSide(event.getX()));
        });
        setOnDragExited(event -> {
            draw();
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
    
    public void draw() {
        gc.setFill(Color.BISQUE);
        gc.fillRect(0, 0, getWidth(), getHeight());
        
        gc.setStroke(Color.BURLYWOOD);
        gc.setLineWidth(1.5);
        gc.strokeRect(0, 0, getWidth(), getHeight());
        
        gc.setFill(Color.BLACK);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.fillText(Instruments.getInstrumentName(instrument), getWidth()/2, getHeight()/2-10);
        gc.fillText(Notes.getNoteName(note), getWidth()/2, getHeight()/2+10);
    }
    
    private int getDropSide(double mouseX) {
        mouseX += getLayoutX();

        if(mouseX > getLayoutX() && mouseX < getLayoutX() + getWidth()/2) {
            return LEFT_SIDE;
        } else if(mouseX > getLayoutX() + getWidth()/2 && mouseX < getLayoutX() + getWidth()) {
            return RIGHT_SIDE;
        } else {
            return -1;
        }
    }
    public void drawHighlighter(int side) {
        final double HIGHLIGHT_SIZE = 3;
        
        draw();
        gc.setFill(Color.LIGHTGREEN);
        
        switch (side) {
            case LEFT_SIDE:
                gc.fillRect(0, 0, HIGHLIGHT_SIZE, getHeight());
                break;
            case RIGHT_SIDE:
                gc.fillRect(getWidth()-HIGHLIGHT_SIZE, 0, HIGHLIGHT_SIZE, getHeight());
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
