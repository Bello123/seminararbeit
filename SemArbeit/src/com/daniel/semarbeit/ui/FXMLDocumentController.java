package com.daniel.semarbeit.ui;

import com.daniel.utils.Dialogs;
import com.daniel.utils.Strings;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import static java.util.stream.Collectors.toList;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Arc;
import javafx.util.Duration;
import user.NoteSet;

/**
 *
 * @author Daniel
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Button btnLoadNoteSet;
    @FXML
    private ListView<String> lstInstruments;
    @FXML
    private Arc arcProgress;
    private FadeTransition arcProgressFadeIn;
    @FXML
    private Label lblProgress;
            
    private NoteSet noteSet;
    private ProgressView pgView;
    
    @FXML
    public void btnLoadNoteSetAction(ActionEvent event) {
        try {
            noteSet = new NoteSet();            
            String path = Dialogs.chooseFileDialog("NoteSet Datei auswählen");            
            noteSet.deserialize(path);
            lstInstruments.getItems().addAll(noteSet.getSounds().keySet().stream().map(s -> Strings.normalizeString(s, "_")).collect(toList()));
        } catch (Exception ex) {
            Dialogs.alert("Alert", "Something went wrong", "Die Datei konnte nicht eingelesen werden");
        }
    }
    
    @FXML
    public void lstInstrumentsSelectedAction(MouseEvent event) {
        String instrument = lstInstruments.getSelectionModel().getSelectedItem().toUpperCase();
        ArrayList<String> notes = noteSet.getSounds().get(instrument);
        pgView.update(instrument, notes);
        update();
    }
    
    private void update() {
        pgView.draw();
        lblProgress.setText((int)pgView.getPercent() + "%");
        arcProgressFadeIn.play();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pgView = new ProgressView(arcProgress);
        
        arcProgressFadeIn = new FadeTransition(Duration.millis(500), arcProgress);
        arcProgressFadeIn.setFromValue(0);
        arcProgressFadeIn.setToValue(1);
        arcProgressFadeIn.setCycleCount(1);
        arcProgressFadeIn.setAutoReverse(true);   
    }    
    
}
