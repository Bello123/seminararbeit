package com.daniel.semarbeit.ui;

import com.daniel.semarbeit.ui.elements.Track;
import com.daniel.semarbeit.user.Category;
import com.daniel.semarbeit.user.NoteSet;
import com.daniel.semarbeit.util.Dialogs;
import com.daniel.semarbeit.util.Strings;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    private Button btnRefresh, btnAddTrack;
    @FXML
    private VBox vbxTracks;
    
    private NoteSet noteSet;
    
    @FXML
    public void btnRefreshAction(ActionEvent event) {
        initNoteSet();
    }
    
    @FXML
    public void btnAddTrackAction(ActionEvent event) {
        vbxTracks.getChildren().add(new Track(vbxTracks));
    }
    
    @FXML
    public void btnRemoveTrackAction(ActionEvent event) {
        initNoteSet();
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
                    TreeItem<String> noteItem = new TreeItem<>(note);
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
    
    private void initNoteSet() {
        noteSet = new NoteSet(); 
        try {
            noteSet.deserialize("I:\\School\\Sek II\\Seminararbeit\\Code\\seminararbeit\\SemArbeit\\src\\com\\daniel\\semarbeit\\notes\\saved_notes.mc"); 
            update();
        } catch (IOException ex) {
            Dialogs.alert("Alert", "Something went wrong", "Die gespeicherten Noten konnten nicht eingelesen werden");
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    private void initNoteTree() {
        trvNotes.setCellFactory((TreeView<String> stringTreeView) -> {
            TreeCell<String> treeCell = new TreeCell<String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null) {
                        setText(item);
                    }
                    if(noteSet.containsNote(item)) {
                        this.setOnDragDetected((MouseEvent event) -> {
                            Dragboard db = this.startDragAndDrop(TransferMode.ANY);

                            ClipboardContent content = new ClipboardContent();
                            String category = Strings.serializeString(this.getTreeItem().getParent().getParent().getValue());
                            String instrument = Strings.serializeString(this.getTreeItem().getParent().getValue());
                            content.putString(category + " " + instrument + " " + item);
                            db.setContent(content);

                            event.consume();
                        }); 
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
        initNoteSet();
        initNoteTree();
    }    
    
}
