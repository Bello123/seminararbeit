package com.daniel.semarbeit.ui;

import com.daniel.semarbeit.ui.elements.Track;
import com.daniel.semarbeit.user.Categories;
import com.daniel.semarbeit.user.Instruments;
import com.daniel.semarbeit.user.NoteSet;
import com.daniel.semarbeit.user.Notes;
import com.daniel.semarbeit.util.Dialogs;
import com.daniel.semarbeit.util.Strings;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class FXMLArrangeTrackController implements Initializable {

    @FXML
    private TreeView<String> trvNotes;
    @FXML
    private Button btnRefresh, btnAddTrack, btnPlay;
    @FXML
    private VBox vbxTracks;
    @FXML
    private Spinner<Double> spnLength;
    
    private NoteSet noteSet;
    
    @FXML
    public void btnRefreshAction(ActionEvent event) throws IOException {
        initNoteSet();
    }
    
    @FXML
    public void btnAddTrackAction(ActionEvent event) {
        vbxTracks.getChildren().add(new Track(vbxTracks));
    }
    
    @FXML
    public void btnPlayAction(ActionEvent event) {
        
    }
    
    private void update() {
        loadNoteView();
    }
    
    private void loadNoteView() {
        TreeItem<String> root = new TreeItem<>("Categories");
        for(Integer categoryId : noteSet.getCategories().keySet()) {
            TreeItem<String> categories = new TreeItem<>(Categories.getCategoryName(categoryId));
            
            noteSet.getCategories().get(categoryId).stream().map(instrument -> {
                TreeItem<String> instruments = new TreeItem<>(Strings.normalizeString(instrument.getName(), "_"));
                instrument.getNotes().stream().forEach(note -> {
                    TreeItem<String> noteItem = new TreeItem<>(note.getName());
                    instruments.getChildren().add(noteItem);
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
    
    private void initNoteSet() throws IOException {
        noteSet = new NoteSet(); 
        update();
    }
    private void initNoteTree() throws Exception {
        trvNotes.setCellFactory((TreeView<String> stringTreeView) -> {
            TreeCell<String> treeCell = new TreeCell<String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null) {
                        setText(item);
                        if(noteSet.containsNote(Strings.serializeString(item).toUpperCase())) {
                            this.setOnDragDetected((MouseEvent event) -> {
                                Dragboard db = this.startDragAndDrop(TransferMode.ANY);

                                ClipboardContent content = new ClipboardContent();
                                String category = Strings.serializeString(this.getTreeItem().getParent().getParent().getValue().toUpperCase());
                                String instrument = Strings.serializeString(this.getTreeItem().getParent().getValue().toUpperCase());
                                String note = Strings.serializeString(item.toUpperCase());
                                content.putString(Categories.valueOf(category).getID() + " " + Instruments.valueOf(instrument).getID() + " " + Notes.valueOf(note).getID() + " " + spnLength.getValue());
                                db.setContent(content);

                                event.consume();
                            }); 
                        }
                    } else {
                        setItem(null);
                        setGraphic(null);
                    }                    
                }
            };
            
            return treeCell;
        });
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            initNoteSet();
            initNoteTree();
            
            SpinnerValueFactory<Double> valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.1, 10, 1);
            spnLength.setValueFactory(valueFactory);
        }catch (IOException ex) {
            Logger.getLogger(FXMLArrangeTrackController.class.getName()).log(Level.SEVERE, null, ex);
            Dialogs.alert("Alert", "Something went wrong", "Die gespeicherten Noten konnten nicht eingelesen werden");
        } catch (Exception ex) {
            Logger.getLogger(FXMLArrangeTrackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
