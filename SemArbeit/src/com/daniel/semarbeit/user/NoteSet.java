package com.daniel.semarbeit.user;

import com.daniel.semarbeit.interfaces.Serializeable;
import com.daniel.semarbeit.util.Dialogs;
import com.daniel.semarbeit.util.Mathe;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import static java.util.stream.Collectors.toList;
import javafx.scene.chart.XYChart;


/**
 *
 * @author Daniel
 */
public class NoteSet implements Serializeable {

    
    public static final int MAX_NOTES = 128;    
    private static String SAVE_PATH;
    
    private HashMap<Integer, ArrayList<Instrument>> categories;

    public NoteSet(String savePath) throws IOException {
        categories = new HashMap<>();
        
        File f = new File(savePath);
        if(f.exists()) {
            deserialize(savePath);
        } else {
            f.mkdirs();
            f.createNewFile();
        }
        SAVE_PATH = savePath;
        
        save();
    }

    private void initCategory(int categoryId) {
        if(!categories.containsKey(categoryId)) {
            categories.put(categoryId, new ArrayList<>());
        }        
    }
    
    public void save() throws IOException {
        File f = new File(SAVE_PATH);
        f.createNewFile();
        serialize(f.getAbsolutePath());
    }
    @Override
    public void serialize(String path) throws IOException {     
        try(PrintWriter pw = new PrintWriter(new File(path))) {
            categories.keySet().stream().forEach((category) -> {
            categories.get(category).stream().forEach((instrument) -> {
                    String notes = "";
                    notes = instrument.getNotes().stream().map(n -> n.getId() + ";").reduce(notes, String::concat);
                    pw.println(category + " " + instrument.getId() + " " + notes.substring(0, notes.length()-1));
                });
            });
        } catch(Exception ex) {
            throw new IOException();
        }
    }

    @Override
    public void deserialize(String path) throws IOException {               
        if(path.endsWith(".ns")) {
            deserializeNoteSet(new File(path));
        } else if(path.endsWith(".ds")) {
            deserializeDataSet(new File(path));
        } else {
            throw new IOException("No method to deserialize this data type");
        }
    }
    private void deserializeNoteSet(File f) throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");

                //create new category if neccessary
                if(parts.length == 0) continue;
                int categoryId = Integer.parseInt(parts[0]);
                if(!Categories.getCategoryName(categoryId).equals("Undefined")) {
                    initCategory(categoryId);
                } else {
                    continue;
                }                

                //create new instrument if neccessary
                if(parts.length == 1) continue;
                int instrumentId = Integer.parseInt(parts[1]);
                if(getInstrument(instrumentId) == null && !Instruments.getInstrumentName(instrumentId).equals("Undefined")) {
                    categories.get(categoryId).add(new Instrument(instrumentId));
                }

                //add rest note to all instruments
                getInstrument(instrumentId).addNote(-1);

                //add notes to instrument
                if(parts.length == 2) continue;                
                for(String note : parts[2].split(";")) {
                    getInstrument(instrumentId).addNote(Integer.parseInt(note));
                }
            }
        } catch(IOException | NumberFormatException ex) {
            throw new IOException();
        } 
    }
    private void deserializeDataSet(File f) throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");

                if(parts.length == 0) continue;
                double height = Math.round(0.047619*Double.parseDouble(parts[0])+0.47619);

                if(parts.length == 1) continue;
                double temperature = Math.round(0.047619*Double.parseDouble(parts[1])+0.47619);

                if(parts.length == 2) continue;                
                double bearing = Math.round(0.047619*Double.parseDouble(parts[2])+0.47619);
                
                if(parts.length == 3) continue;                
                double speed = Math.round(0.047619*Double.parseDouble(parts[3])+0.47619);
            }
        } catch(IOException | NumberFormatException ex) {
            throw new IOException();
        } 
    }

    public HashMap<Integer, ArrayList<Instrument>> getCategories() {
        return categories;
    } 
    
    /**
     * Looks for an instrument that matches the given name in categories
     * @param name
     * @return the first instrument found or null
     */
    private Instrument getInstrument(int instrumentId) {
        for(Integer id : categories.keySet()) {
            if(categories.get(id).stream().map(i -> i.getId()).collect(toList()).contains(instrumentId)) {
                return categories.get(id)
                        .stream()
                        .filter(i -> i.getId() == instrumentId)
                        .findFirst()
                        .get();
            }
        }
        return null;
    }
    
    public XYChart.Series getCategoriesChartDataset() {
        XYChart.Series dataset = new XYChart.Series(); 
        categories.keySet().stream().map((category) -> {
            double percent = Mathe.percentOf(categories.get(category).size()*MAX_NOTES, categories.get(category).stream()
                    .mapToInt(instr -> instr.getNotes().size())
                    .sum())*100;
            XYChart.Data<String, Number> data = new XYChart.Data(Categories.getCategoryName(category) + " (" + Mathe.truncateToString(percent, 1) + "%)", percent);
            return data;
        }).forEach((data) -> {
            dataset.getData().add(data);
        });
        
        return dataset;
    }    
    public XYChart.Series getInstrumentsChartDataset(int category) {
        XYChart.Series dataset = new XYChart.Series();
        if(categories.containsKey(category)) {
            for(Instrument instrument : categories.get(category).stream().sorted((Instrument o1, Instrument o2) -> {return o1.getName().compareTo(o2.getName());}).collect(toList())) {
                double percent = Mathe.percentOf(MAX_NOTES, instrument.getNotes().size())*100;
                XYChart.Data<String, Number> data = new XYChart.Data(instrument.getName() + " (" + Mathe.truncateToString(percent, 1) + "%)", percent);
                dataset.getData().add(data);
            }
        }        
        return dataset;
    }

    /**
     * Looks for the given note in all instruemnts
     * @param note
     * @return 
     */
    public boolean containsNote(String note) {
        return categories.keySet().stream()
                .anyMatch((c) -> (categories.get(c)
                        .stream()
                        .anyMatch((i) -> (i.hasNote(note)))
                        )
                );
    }

    public static String getSAVE_PATH() {
        if(SAVE_PATH == null) {
            SAVE_PATH = Dialogs.chooseFileDialog("NoteSet Datei auswählen", "*.ns", "*.ds");
        } 
        return SAVE_PATH;
    }
    
}
