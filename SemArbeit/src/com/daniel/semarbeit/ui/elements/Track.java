package com.daniel.semarbeit.ui.elements;

import com.daniel.semarbeit.user.Instruments;
import com.daniel.semarbeit.user.Notes;
import com.daniel.semarbeit.util.Strings;
import com.daniel.semarbeit.util.Transitions;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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
        setPrefSize(5000, 125);
        setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
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
        final int BUTTON_SIZE = 35;
        
        btnMute.setPrefSize(BUTTON_SIZE, BUTTON_SIZE);
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
        btnSolo.setPrefSize(BUTTON_SIZE, BUTTON_SIZE);
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
                    getChildren().add(1, new TrackItem(this, Integer.parseInt(elements[1]), Integer.parseInt(elements[2]), Double.parseDouble(elements[3])));
                    success = true;
                } else {
                    success = false;
                }                
            }
            event.setDropCompleted(success);

            event.consume();
        });
    }
     
    @Override
    public String toString() {
        String sound = "";
        if(getChildren().size() <= 1) return "";
        
        sound = getChildren().subList(1, getChildren().size()).stream()
                .map((item) -> {
                    TrackItem ti = (TrackItem)item;
                    if(ti.getNote() == -1) {
                        return "R ";
                    } else {
                        return "[" + Strings.serializeString(Instruments.getInstrumentName(ti.getInstrument())) 
                        + "][" 
                        + Strings.serializeString(Notes.getNoteName(ti.getNote())) 
                        + "]/" 
                        + ti.getLength() 
                        + " ";
                    }                        
                })
                .reduce(sound, String::concat);
        
        return sound;
    }
    
}
