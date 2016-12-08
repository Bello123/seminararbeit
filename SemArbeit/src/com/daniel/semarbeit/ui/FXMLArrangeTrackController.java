package com.daniel.semarbeit.ui;

import com.daniel.semarbeit.user.Category;
import com.daniel.semarbeit.user.NoteSet;
import com.daniel.utils.Dialogs;
import com.daniel.utils.Strings;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class FXMLArrangeTrackController implements Initializable {

    @FXML
    private TreeView<String> trvNotes;
    
    @FXML
    private Button btnRefresh;
    
    private NoteSet noteSet;
    
    @FXML
    public void btnRefreshAction(ActionEvent event) {
        init();
    }
    
    private void update() {
        loadNoteView();
    }
    
    private void loadNoteView() {
        TreeItem<String> root = new TreeItem<>("Categories");
        for(Integer categoryId : noteSet.getCategories().keySet()) {
            TreeItem<String> categories = new TreeItem<>(Category.getCategoryName(categoryId));
            noteSet.getCategories().get(categoryId).stream().map((instrument) -> {
                TreeItem<String> instruments = new TreeItem<>(Strings.normalizeString(instrument.getName(), "_"));
                instrument.getNotes().stream().forEach((note) -> {
                    instruments.getChildren().add(new TreeItem<>(note));
                });
                return instruments;
            }).forEach((TreeItem<String> instruments) -> {
                categories.getChildren().add(instruments);
            });
            root.getChildren().add(categories);
        }
        
        trvNotes.setRoot(root);
        trvNotes.getRoot().expandedProperty().set(true);
    }
    
    private void init() {
        noteSet = new NoteSet(); 
        try {
            noteSet.deserialize("I:\\Informatik\\semArbeit\\SemArbeit\\src\\com\\daniel\\semarbeit\\notes\\saved_notes.mc"); 
            update();
        } catch (IOException ex) {
            Dialogs.alert("Alert", "Something went wrong", "Die gespeicherten Noten konnten nicht eingelesen werden");
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        init();
    }    
    
}
