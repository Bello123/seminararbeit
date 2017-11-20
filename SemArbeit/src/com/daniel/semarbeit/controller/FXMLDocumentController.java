package com.daniel.semarbeit.controller;

import com.daniel.semarbeit.model.Categories;
import com.daniel.semarbeit.model.NoteSet;
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
        crtCategories.layout();
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
        updateInstrumentChart(1);
        updateCategoryChart();
        updateCategoryButtons();
    }
    
    @FXML
    public void btnLoadNoteSetAction(ActionEvent event) {
        try {           
            String path = Dialogs.chooseFileDialog("NoteSet Datei auswählen", "*.ns", "*.ds");   
            if(noteSet == null) {
                noteSet = new NoteSet(path);
            } else {
                noteSet.deserialize(path);
                noteSet.save();
            }            
            update();
        } catch (IOException ex) {
            Logger.getGlobal().log(Level.SEVERE, null, ex);
            Dialogs.alert("Alert", "Something went wrong", "Die Datei konnte nicht eingelesen werden");
        } catch(Exception ex) {
            Logger.getGlobal().log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void btnArrangeTrackAction(ActionEvent event) {
        try {           
            FXMLLoader loader = new FXMLLoader (getClass().getResource("/com/daniel/semarbeit/ui/FXMLArrangeTrack.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Arrange Track");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception ex) {
            Logger.getGlobal().log(Level.SEVERE, null, ex);
            Dialogs.alert("Alert", "Something went wrong", "Das Fenster konnte nicht geladen werden.");
        }
    }    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        try {
            crtCategories.getXAxis().setAnimated(false);
            crtInstruments.getXAxis().setAnimated(false);
            noteSet = new NoteSet(NoteSet.getSAVE_PATH());          
            update();
        } catch (IOException ex) {
            Logger.getGlobal().log(Level.SEVERE, null, ex);
            Dialogs.alert("Alert", "Something went wrong", "Die gespeicherten Noten konnten nicht eingelesen werden");
        } catch(Exception ex) {
            Logger.getGlobal().log(Level.SEVERE, null, ex);
        }
    }    
   
    public void closeAndSave() {
        try {
            Dialogs.alert("Speichern", "NoteSet wird gespeichert", "Bitte warten");
            if(noteSet != null) noteSet.save();
        } catch (Exception ex) {
            Logger.getGlobal().log(Level.SEVERE, null, ex);
        } finally {
            System.exit(0);
        }
    }
    
}
