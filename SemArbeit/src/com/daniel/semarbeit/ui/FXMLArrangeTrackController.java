package com.daniel.semarbeit.ui;

import com.daniel.semarbeit.interfaces.Serializeable;
import com.daniel.semarbeit.ui.elements.Track;
import com.daniel.semarbeit.ui.elements.TrackItem;
import com.daniel.semarbeit.user.Categories;
import com.daniel.semarbeit.user.Instruments;
import com.daniel.semarbeit.user.NoteSet;
import com.daniel.semarbeit.user.Notes;
import com.daniel.semarbeit.util.Dialogs;
import com.daniel.semarbeit.util.Strings;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
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
import org.jfugue.player.Player;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class FXMLArrangeTrackController implements Initializable, Serializeable {

    @FXML
    private TreeView<String> trvNotes;
    @FXML
    private Button btnRefresh, btnAddTrack, btnPlay;
    @FXML
    private VBox vbxTracks;
    @FXML
    private Spinner<String> spnLength;
    
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
        new Thread(() -> {
            String music = "";
            for(int i=0;i<vbxTracks.getChildren().size();i++) {
                Track t = (Track)vbxTracks.getChildren().get(i);
                if(t.isSolo()) {
                    music = t.toString();
                    break;
                }

                music += "L" + i + " ";
                music += t.toString();
            }

            Player player = new Player();
            player.play(music);
        }).start();        
    }
    
    @FXML
    public void btnSaveAction(ActionEvent event) {
        try {
            serialize(Dialogs.createFileDialog("Datei auswählen", "*.t"));
        } catch (Exception ex) {
            Logger.getLogger(FXMLArrangeTrackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void btnLoadAction(ActionEvent event) {
        try {
            deserialize(Dialogs.chooseFileDialog("Datei auswählen", "*.t"));
        } catch (Exception ex) {            
            Logger.getLogger(FXMLArrangeTrackController.class.getName()).log(Level.SEVERE, null, ex);
            Dialogs.alert("Alert", "Something went wrong", "Die Datei konnte nicht eingelesen werden");
        }
    }
    
    private void update() {
        loadNoteView();
    }
    
    private void loadNoteView() {
        TreeItem<String> root = new TreeItem<>("Kategorien");
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
        noteSet = new NoteSet(NoteSet.getSAVE_PATH()); 
        update();
    }
    private void initNoteTree() throws Exception {
        trvNotes.setCellFactory((TreeView<String> stringTreeView) -> {
            TreeCell<String> treeCell = new TreeCell<String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if(empty || item == null) {
                        setItem(null);
                        setGraphic(null);
                        setText(null);
                    } else {
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
            
            SpinnerValueFactory.ListSpinnerValueFactory<String> dblFactory = 
                    new SpinnerValueFactory.ListSpinnerValueFactory<>(FXCollections.observableArrayList("w", "h", "q", "i", "s", "t"));
            dblFactory.setValue("w");
            spnLength.setValueFactory(dblFactory);
        }catch (IOException ex) {
            Logger.getGlobal().log(Level.SEVERE, null, ex);
            Dialogs.alert("Alert", "Something went wrong", "Die gespeicherten Noten konnten nicht eingelesen werden");
        } catch (Exception ex) {
            Logger.getGlobal().log(Level.SEVERE, null, ex);
        }
    }    

    @Override
    public void serialize(String path) throws Exception {
        File f = new File(path);
        f.createNewFile();

        try(PrintWriter pw = new PrintWriter(new File(path))) {
            vbxTracks.getChildren().forEach(t -> {
                pw.println(t.toString());
            });
        } catch(Exception ex) {
            throw new IOException();
        }
    }

    @Override
    public void deserialize(String path) throws Exception {
        vbxTracks.getChildren().clear();
        
        try(BufferedReader br = new BufferedReader(new FileReader(new File(path)))) {
            String line;
            while ((line = br.readLine()) != null) {                
                Track t = new Track(vbxTracks);
                
                String[] parts = line.split("I");
                for(int i=0;i<parts.length;i++) {
                    if(parts[i].isEmpty()) continue;
                    
                    String[] p = parts[i].trim().split(" ");
                    if(p.length != 2) continue;
                    
                    int instrument = Integer.parseInt(p[0]);
                    if(Instruments.getInstrumentName(instrument).equals("Undefined")) throw new Exception("Unknown instrument");
                    
                    String[] noteAndLength = p[1].split("(?<=\\d)(?=\\D)");
                    int note = Integer.parseInt(noteAndLength[0]);
                    if(Notes.getNoteName(note).equals("Undefined")) throw new Exception("Unknown note");
                    
                    String length = noteAndLength[1];
                    if(!length.matches("[whqist]")) throw new Exception("Unknown length");
                    
                    t.getChildren().add(new TrackItem(t, instrument, note, length));
                }
                
                vbxTracks.getChildren().add(t);
            }
        } catch(IOException | NumberFormatException ex) {
            throw new IOException();
        } catch(Exception ex) {
            vbxTracks.getChildren().clear();
            
            throw ex;
        }
    }
    
}
