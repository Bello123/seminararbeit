package com.daniel.semarbeit.model;

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
        
        if(!savePath.isEmpty()) {
            File f = new File(savePath);
            if(f.exists()) {
                deserialize(savePath);
            } else {
                f.mkdirs();
                f.createNewFile();
            }
            
            SAVE_PATH = savePath;
            if(savePath.endsWith(".ds") || savePath.endsWith(".DS")) {
                SAVE_PATH = SAVE_PATH.replace(".ds", ".ns");
                SAVE_PATH = SAVE_PATH.replace(".DS", ".ns");
            }         

            save();
        }     
    }

    private void initCategory(int categoryId) {
        if(!categories.containsKey(categoryId)) {
            categories.put(categoryId, new ArrayList<>());
        }        
    }
    
    public void save() throws IOException {
        if(SAVE_PATH.isEmpty()) SAVE_PATH = Dialogs.createFileDialog("Datei speichern", "*.ns");
        
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
                    pw.println(instrument.getId() + " " + notes.substring(0, notes.length()-1));
                });
            });
        } catch(Exception ex) {
            throw new IOException();
        }
    }

    @Override
    public void deserialize(String path) throws IOException {         
        if(path.isEmpty()) return;
        
        if(path.endsWith(".ns") || path.endsWith(".NS")) {
            deserializeNoteSet(new File(path));
        } else if(path.endsWith(".ds") || path.endsWith(".DS")) {
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

                //create new instrument if neccessary
                if(parts.length == 0) continue;
                int instrumentId = Integer.parseInt(parts[0]);
                
                if(!Instruments.getInstrumentName(instrumentId).equals("Undefined")) {
                    int categoryId = Instruments.getInstrument(instrumentId).getCATEGORY_ID();
                    initCategory(categoryId);
                    
                    if(getInstrument(instrumentId) == null) {
                        categories.get(categoryId).add(new Instrument(instrumentId));
                    }
                }
                
                //add rest note to all instruments
                getInstrument(instrumentId).addNote(-1);

                //add notes to instrument
                if(parts.length == 1) continue;                
                for(String note : parts[1].split(";")) {
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

                if(parts.length != 3) continue;
                
                double height = Double.parseDouble(parts[0]);
                double temperature = Double.parseDouble(parts[1]);              
                double bearing = Double.parseDouble(parts[2]);
                
                /*
                Octave = height every 5m
                Instrument = temperature every 0.5°C
                note = bearing 360°/11
                */
                
                int instrumentId = (int)Math.min(Math.round(1.9834*temperature+41.661), 121);
                int octave = (int)Math.min(Math.round(0.2*height), 10);
                int note = (int)Math.min(Math.round(bearing * 11 / 360), 11);                
                
                if(!Instruments.getInstrumentName(instrumentId).equals("Undefined")) {
                    int categoryId = Instruments.getInstrument(instrumentId).getCATEGORY_ID();
                    initCategory(categoryId);
                    
                    if(getInstrument(instrumentId) == null) {
                        categories.get(categoryId).add(new Instrument(instrumentId));
                        //add rest note to all instruments
                        getInstrument(instrumentId).addNote(-1);
                    }
                }              
                
                getInstrument(instrumentId).addNote(octave *  12 + note);
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
