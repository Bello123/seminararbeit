package com.daniel.semarbeit.ui.elements;

import com.daniel.semarbeit.util.Transitions;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 * @author Daniel
 */
public class Track extends HBox {
    
    private VBox parent;
    private int trackId;
    private boolean muted, solo;

    public Track(VBox parent) {
        super();
        this.parent = parent; 
        muted = false;
        solo = false;
        setPrefSize(5000, 165);
        setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
        setAlignment(Pos.CENTER_LEFT);
        setStyle("-fx-background-color: grey;");
        initControls();
        initNoteTrack();
        Transitions.playFadeTransition(this, 300, 0, 1);
    }   
    
    private void initControls() {
        final int BUTTON_SIZE = 36;
        
        VBox controls = new VBox(3);
        controls.setAlignment(Pos.CENTER_LEFT);
        controls.setPadding(new Insets(0, 5, 0, 5));                
        
        Button btnMute = new Button("M");
        btnMute.setTooltip(new Tooltip("Diese Spur stummstellen"));
        btnMute.setPrefSize(BUTTON_SIZE, BUTTON_SIZE);
        btnMute.setOnAction(event -> {
            if(muted) {
                muted = false;
                btnMute.setBorder(Border.EMPTY); 
            } else {
                muted = true;
                btnMute.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.MEDIUM)));
            }
        });
        Button btnSolo = new Button("S");
        btnSolo.setTooltip(new Tooltip("Nur diese Spur wird gespielt"));
        btnSolo.setPrefSize(BUTTON_SIZE, BUTTON_SIZE);
        btnSolo.setOnAction(event -> {
            if(solo) {
                solo = false;
                btnSolo.setBorder(Border.EMPTY); 
            } else {
                solo = true;
                btnSolo.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.MEDIUM)));
            }
            
        });
        Button btnDelete = new Button("X");
        btnDelete.setTooltip(new Tooltip("Spur löschen"));
        btnDelete.setPrefSize(BUTTON_SIZE, BUTTON_SIZE);
        btnDelete.setOnAction(event -> {
            parent.getChildren().remove(this);            
        });
        controls.getChildren().addAll(btnMute, btnSolo, btnDelete);
        getChildren().add(controls);
    }
    
    private void initNoteTrack() {        
        setOnDragOver((DragEvent event) -> {
            if (event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }            
            event.consume();
        });
        setOnDragDropped((DragEvent event) -> {
            if(getChildren().size() > 2) return;
            
            Dragboard db = event.getDragboard();
            boolean success = false;
            if(db.hasString()) {
                String[] elements = db.getString().split(" ");
                if(elements.length == 4) {
                    getChildren().add(1, new TrackItem(this, Integer.parseInt(elements[1]), Integer.parseInt(elements[2]), elements[3]));
                    success = true;
                } else {
                    success = false;
                }                
            }
            event.setDropCompleted(success);

            event.consume();
        });
    }

    public int getTrackId() {
        return trackId;
    }

    public boolean isMuted() {
        return muted;
    }

    public boolean isSolo() {
        return solo;
    }
     
    @Override
    public String toString() {
        String sound = "";
        if(getChildren().size() <= 1 || muted) return "";
        
        sound = getChildren().subList(1, getChildren().size()).stream()
                .map((item) -> {
                    TrackItem ti = (TrackItem)item;
                    if(ti.getNote() == -1) {
                        return "R ";
                    } else {
                        return "I" + ti.getInstrument()
                        + " " 
                        + ti.getNote()
                        + ti.getLength() 
                        + " ";
                    }                        
                })
                .reduce(sound, String::concat);
        
        return sound;
    }
    
}
