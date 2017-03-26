package com.daniel.semarbeit.ui.elements;

import com.daniel.semarbeit.util.Transitions;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 *
 * @author Daniel
 */
public class Track extends HBox {

    private VBox parent;
    private Canvas noteTrack;
    private GraphicsContext noteTrackGC;
    private int trackId;
    private boolean muted, solo;
    private ArrayList<TrackItem> items;

    public Track(VBox parent) {
        super();
        this.parent = parent; 
        muted = false;
        solo = false;
        items = new ArrayList<>();
        setAlignment(Pos.CENTER_LEFT);
        setStyle("-fx-background-color: grey;");
        initControls();
        initNoteTrack();
        Transitions.playFadeTransition(this, 300, 0, 1);
    }   
    
    private void initControls() {
        VBox controls = new VBox(3);
        controls.setAlignment(Pos.CENTER_LEFT);
        controls.setPadding(new Insets(0, 5, 0, 5));
        Button btnMute = new Button("M");
        btnMute.setPrefSize(35, 35);
        btnMute.setOnAction(event -> {
            if(muted) {
                muted = false;
                btnMute.setBorder(Border.EMPTY);
                System.out.println("Unmuted track " + trackId);   
            } else {
                muted = true;
                btnMute.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.THIN)));
                System.out.println("Muted track " + trackId);   
            }
        });
        Button btnSolo = new Button("S");
        btnSolo.setPrefSize(35, 35);
        btnSolo.setOnAction(event -> {
            if(solo) {
                solo = false;
                btnSolo.setBorder(Border.EMPTY);
                System.out.println("Disabled solo playing track " + trackId); 
            } else {
                solo = true;
                btnSolo.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.THIN)));
                System.out.println("Solo playing track " + trackId); 
            }
            
        });
        Button btnDelete = new Button("X");
        btnDelete.setPrefSize(35, 35);
        btnDelete.setOnAction(event -> {
            parent.getChildren().remove(this);            
        });
        controls.getChildren().addAll(btnMute, btnSolo, btnDelete);
        getChildren().add(controls);
    }
    
    private void initNoteTrack() {
        noteTrack = new Canvas(5000, 125);
        noteTrackGC = noteTrack.getGraphicsContext2D();
        getChildren().add(noteTrack);
        drawNoteTrackBackground(Color.LIGHTGREY);
        
        noteTrack.setOnDragOver((DragEvent event) -> {
            if (event.getGestureSource() != noteTrack &&
                event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            
            event.consume();
        });
        noteTrack.setOnDragEntered((DragEvent event) -> {
            if (event.getGestureSource() != noteTrack && event.getDragboard().hasString()) {
                drawNoteTrackBorder(Color.LIGHTGREEN);
            }

            event.consume();
        });
        noteTrack.setOnDragExited((DragEvent event) -> {
            drawNoteTrackBorder(Color.LIGHTGREY);
            event.consume();
        });
        noteTrack.setOnDragDropped((DragEvent event) -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if(db.hasString()) {
                String[] elements = db.getString().split(" ");
                if(elements.length == 3) {
                    items.add(new TrackItem(elements[1], elements[2]));
                    repaint();
                    success = true;
                } else {
                    success = false;
                }                
            }
            event.setDropCompleted(success);

            event.consume();
        });
    }
    
    public void repaint() {   
        //x= 3 => spacing
        double x = 3;
        
        noteTrackGC.clearRect(0, 0, noteTrack.getWidth(), noteTrack.getHeight());
        drawNoteTrackBackground(Color.LIGHTGREY);
        for(int i=0;i<items.size();i++) {
            items.get(i).draw(noteTrackGC, x, noteTrack.getHeight()/2-items.get(i).getHeight()/2);
            x += items.get(i).getWidth();
        }
     }

    private void drawNoteTrackBackground(Paint color) {
        noteTrackGC.setFill(color);
        noteTrackGC.fillRect(0, 0, noteTrack.getWidth(), noteTrack.getHeight());
    }
    private void drawNoteTrackBorder(Paint color) {
        noteTrackGC.setStroke(color);
        noteTrackGC.setLineWidth(4);
        noteTrackGC.strokeRect(0, 0, noteTrack.getWidth(), noteTrack.getHeight());
    }
     
}
