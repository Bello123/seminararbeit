package com.daniel.semarbeit.ui.elements;

import com.daniel.semarbeit.user.Notes;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author Daniel
 */
public class TrackItem extends Canvas {

    private final int LEFT_SIDE = 0, RIGHT_SIDE = 1, CENTER = 2;

    private int note;
    private int instrument;
    private String length;

    public TrackItem(HBox parent, int instrument, int note, String length) {
        this.note = note;
        this.instrument = instrument;
        this.length = length;
        setWidth(40);
        setHeight(150);
        draw();       
        initDragEvents(parent);
    }
    private void initDragEvents(HBox parent) {
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
                    if(getDropSide(event.getX()) == CENTER) {
                        instrument = Integer.parseInt(elements[1]);
                        note = Integer.parseInt(elements[2]);
                        length = elements[3];
                        draw();
                    } else {
                        parent.getChildren().add(parent.getChildren().indexOf(this)+getDropSide(event.getX()), new TrackItem(parent, Integer.parseInt(elements[1]), Integer.parseInt(elements[2]), elements[3]));                        
                    } 
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
        final GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, getWidth(), getHeight());
        
        gc.drawImage(new Image(getClass().getResource("/com/daniel/semarbeit/ui/assets/notenblatt.png").toExternalForm()), 0, 0); 
        if(note != Notes.R.getID()) {
            gc.drawImage(new Image(getClass().getResource("/com/daniel/semarbeit/ui/assets/note_" + length + ".png").toExternalForm()), 0, getNoteY(note));
        }
        
        gc.setFill(Color.BLACK);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.setFont(Font.font(18));
        gc.fillText(String.valueOf((int)Math.floor(note/12)), getWidth()/2, 9);
        
        if(isSharpNote(note)) gc.fillText("#", getWidth()/2, 27);
    }  
    private boolean isSharpNote(int note) {
        return (note-1) % 12 == 0 || (note-3) % 12 == 0 || (note-6) % 12 == 0 || (note-8) % 12 == 0 || (note-10) % 12 == 0;
    }
    private double getNoteY(int note) {
        if((note-0) % 12 == 0) { //C
            return 75;
        } else if((note-1) % 12 == 0) { //C#
            return 75;
        } else if((note-2) % 12 == 0) { //D
            return 67;
        } else if((note-3) % 12 == 0) { //D#
            return 67;
        } else if((note-4) % 12 == 0) { //E
            return 59;
        } else if((note-5) % 12 == 0) { //F
            return 51;
        } else if((note-6) % 12 == 0) { //F#
            return 51;
        } else if((note-7) % 12 == 0) { //G
            return 44;
        } else if((note-8) % 12 == 0) { //G#
            return 44;
        } else if((note-9) % 12 == 0) { //A
            return 35;
        } else if((note-10) % 12 == 0) { //A#
            return 35;
        } else if((note-11) % 12 == 0) { //B
            return 27;
        } else {
            return 0;
        }           
    }
    
    private void drawHighlighter(int side) {
        final GraphicsContext gc = getGraphicsContext2D();
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
            case CENTER:
                gc.setStroke(Color.LIGHTGREEN);
                gc.setLineWidth(HIGHLIGHT_SIZE);
                gc.strokeRect(0, 0, getWidth(), getHeight());
                break;
        }
    }
    private int getDropSide(double mouseX) {
        mouseX += getLayoutX();

        if(mouseX > getLayoutX() && mouseX < getLayoutX() + getWidth()/3) {
            return LEFT_SIDE;
        } else if(mouseX > getLayoutX() + getWidth()/3*2 && mouseX < getLayoutX() + getWidth()) {
            return RIGHT_SIDE;
        } else if(mouseX > getLayoutX() + getWidth()/3 && mouseX < getLayoutX() + getWidth()/3*2) {
            return CENTER;
        } else {
            return -1;
        }
    }

    public int getNote() {
        return note;
    }

    public int getInstrument() {
        return instrument;
    }

    public String getLength() {
        return length;
    }

}
