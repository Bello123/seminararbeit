package com.daniel.semarbeit.ui;

import com.daniel.semarbeit.user.Categories;
import com.daniel.semarbeit.user.NoteSet;
import com.daniel.semarbeit.util.Dialogs;
import com.daniel.semarbeit.util.Transitions;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 *
 * @author Daniel
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Button btnLoadNoteSet, btnArrangeTrack;
    @FXML
    private BarChart crtInstruments;
    @FXML
    private BarChart crtCategories;
    @FXML
    private FlowPane flpCategoryButtons;
    
    private NoteSet noteSet;

    private void updateInstrumentChart(int selectedCategory) {
        crtInstruments.getData().clear();
        crtInstruments.getData().add(noteSet.getInstrumentsChartDataset(selectedCategory));
        Transitions.playFadeTransition(crtInstruments, 400, 0, 1);
    }
    
    private void updateCategoryChart() {
        crtCategories.getData().clear();
        crtCategories.getData().add(noteSet.getCategoriesChartDataset());
        Transitions.playFadeTransition(crtInstruments, 400, 0, 1);
    }
    
    private void updateCategoryButtons() {
        flpCategoryButtons.getChildren().clear();
        noteSet.getCategories().keySet().stream().map((i) -> {
            Button btn = new Button(Categories.getCategoryName(i));
            btn.setPrefHeight(40);
            btn.setPrefWidth((flpCategoryButtons.getPrefWidth()-noteSet.getCategories().keySet().size()*3)/10);
            btn.setOnAction(a -> updateInstrumentChart(i));
            return btn;
        }).forEach((btn) -> {
            flpCategoryButtons.getChildren().add(btn);
        });
    }
    
    private void update() {
        updateCategoryChart();
        updateInstrumentChart(1);
        updateCategoryButtons();
    }
    
    @FXML
    public void btnLoadNoteSetAction(ActionEvent event) {
        try {           
            String path = Dialogs.chooseFileDialog("NoteSet Datei auswählen");            
            noteSet.deserialize(path);
            update();
        } catch (IOException ex) {
            Logger.getLogger(FXMLArrangeTrackController.class.getName()).log(Level.SEVERE, null, ex);
            Dialogs.alert("Alert", "Something went wrong", "Die Datei konnte nicht eingelesen werden");
        } catch(Exception ex) {
            Logger.getLogger(FXMLArrangeTrackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void btnArrangeTrackAction(ActionEvent event) {
        try {           
            FXMLLoader loader = new FXMLLoader (getClass().getResource("FXMLArrangeTrack.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Arrange Track");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception ex) {
            Logger.getLogger(FXMLArrangeTrackController.class.getName()).log(Level.SEVERE, null, ex);
            Dialogs.alert("Alert", "Something went wrong", "Das Fenster konnte nicht geladen werden.");
        }
    }    
    
    @FXML
    public void windowClosingAction(ActionEvent event) {
        try {
            noteSet.serialize("I:\\School\\Sek II\\Seminararbeit\\Code\\seminararbeit\\SemArbeit\\src\\com\\daniel\\semarbeit\\notes\\saved_notes.mc");
        } catch (Exception ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        try {
            noteSet = new NoteSet();  
            update();
        } catch (IOException ex) {
            Logger.getLogger(FXMLArrangeTrackController.class.getName()).log(Level.SEVERE, null, ex);
            Dialogs.alert("Alert", "Something went wrong", "Die gespeicherten Noten konnten nicht eingelesen werden");
        } catch(Exception ex) {
            Logger.getLogger(FXMLArrangeTrackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
