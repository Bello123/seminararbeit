package com.daniel.semarbeit.ui;

import com.daniel.utils.Dialogs;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import com.daniel.semarbeit.user.NoteSet;
import com.daniel.utils.Transitions;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.chart.BarChart;

/**
 *
 * @author Daniel
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Button btnLoadNoteSet;
    @FXML
    private BarChart crtInstruments;
            
    private NoteSet noteSet;

    private void updateChart() {
        crtInstruments.getData().clear();
        crtInstruments.getData().add(noteSet.getChartDataset());
        Transitions.playFadeTransition(crtInstruments, 700, 0, 1);
    }
    
    @FXML
    public void btnLoadNoteSetAction(ActionEvent event) {
        try {           
            String path = Dialogs.chooseFileDialog("NoteSet Datei auswählen");            
            noteSet.deserialize(path);
            updateChart();
        } catch (Exception ex) {
            Dialogs.alert("Alert", "Something went wrong", "Die Datei konnte nicht eingelesen werden");
        }
    }
    
    @FXML
    public void windowClosingAction(ActionEvent event) {
        try {
            noteSet.serialize("I:\\Informatik\\semArbeit\\SemArbeit\\src\\com\\daniel\\semarbeit\\notes\\saved_notes.mc");
        } catch (Exception ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        noteSet = new NoteSet(); 
        try {
            noteSet.deserialize("I:\\Informatik\\semArbeit\\SemArbeit\\src\\com\\daniel\\semarbeit\\notes\\saved_notes.mc");       
            updateChart();
        } catch (Exception ex) {
            Dialogs.alert("Alert", "Something went wrong", "Die gespeicherten Noten konnten nicht eingelesen werden");
        } 
    }    
    
}
