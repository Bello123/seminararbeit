package com.daniel.semarbeit.user;

import com.daniel.semarbeit.interfaces.Serializeable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.chart.XYChart;

/**
 *
 * @author Daniel
 */
public class NoteSet implements Serializeable {

    public static final int MAX_NOTES = 127;
    
    private HashMap<String, ArrayList<String>> sounds;

    public NoteSet() {
        sounds = new HashMap<>();
    }

    private void initInstrument(String instrument) {
        if(!sounds.containsKey(instrument)) {
            sounds.put(instrument, new ArrayList<>());
        }        
    }
    
    @Override
    public void serialize(String path) throws Exception {
        System.out.println("Serialized");
    }

    @Override
    public void deserialize(String path) throws Exception {
        checkFile(path);

        try (BufferedReader br = new BufferedReader(new FileReader(new File(path)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                
                if(parts.length == 0) continue;
                String instrument = parts[0];
                initInstrument(instrument);
                
                if(parts.length == 1) continue;                
                for(String note : parts[1].split(";")) {
                    if(!sounds.get(instrument).contains(note)) {
                        sounds.get(instrument).add(note);
                    }                    
                }
            }
        }
    }
    private void checkFile(String path) throws Exception {
        File f = new File(path);
        
        if(!path.endsWith(".mc")) throw new Exception("Wrong file type");
        if(!f.exists()) throw new Exception("File not found");
        if(f.getTotalSpace() == 0) throw new Exception("File is empty");
    }

    public HashMap<String, ArrayList<String>> getSounds() {
        return sounds;
    } 
    
    public XYChart.Series getChartDataset() {
        XYChart.Series dataset = new XYChart.Series(); 
        dataset.getData().add(new XYChart.Data("Max", MAX_NOTES));
        for(String instrument : sounds.keySet()) {
            dataset.getData().add(new XYChart.Data(instrument, sounds.get(instrument).size()));
        }
        
        return dataset;
    }
    
}
