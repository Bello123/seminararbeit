package com.daniel.semarbeit.user;

import com.daniel.semarbeit.interfaces.Serializeable;
import com.daniel.utils.Mathe;
import com.daniel.utils.Strings;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import static java.util.stream.Collectors.toList;
import javafx.scene.chart.XYChart;


/**
 *
 * @author Daniel
 */
public class NoteSet implements Serializeable {

    public static final int MAX_NOTES = 127;
    
    private HashMap<Integer, ArrayList<Instrument>> categories;

    public NoteSet() {
        categories = new HashMap<>();
    }

    private void initCategory(int categoryId) {
        if(!categories.containsKey(categoryId)) {
            categories.put(categoryId, new ArrayList<>());
        }        
    }
    
    @Override
    public void serialize(String path) throws IOException {
        System.out.println("Serialized");
    }

    @Override
    public void deserialize(String path) throws IOException {
        checkFile(path);

        try (BufferedReader br = new BufferedReader(new FileReader(new File(path)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                
                if(parts.length == 0) continue;
                int categoryId = Integer.parseInt(parts[0]);
                initCategory(categoryId);
                
                if(parts.length == 1) continue;
                String instrument = parts[1];
                if(getInstrument(instrument) == null) {
                    categories.get(categoryId).add(new Instrument(instrument));
                }
                
                if(parts.length == 2) continue;                
                for(String note : parts[2].split(";")) {
                    getInstrument(instrument).addNote(note);
                }
            }
        }
    }
    private void checkFile(String path) throws IOException {
        File f = new File(path);
        
        if(!path.endsWith(".mc")) throw new IOException("Wrong file type");
        if(!f.exists()) throw new IOException("File not found");
        if(f.getTotalSpace() == 0) throw new IOException("File is empty");
    }

    public HashMap<Integer, ArrayList<Instrument>> getCategories() {
        return categories;
    } 
    
    private Instrument getInstrument(String name) {
        for(Integer id : categories.keySet()) {
            if(categories.get(id).stream().map(i -> i.getName()).collect(toList()).contains(name)) {
                return categories.get(id)
                        .stream()
                        .filter(i -> i.getName().equals(name))
                        .findFirst().get();
            }
        }
        return null;
    }
    
    public XYChart.Series getCategoriesChartDataset() {
        XYChart.Series dataset = new XYChart.Series(); 
        for(Integer category : categories.keySet()) {
            double percent = Mathe.percentOf(categories.get(category).size()*MAX_NOTES, categories.get(category).stream()
                        .mapToInt(instr -> instr.getNotes().size())
                        .sum())*100;
            XYChart.Data<String, Number> data = new XYChart.Data(Category.getCategoryName(category) + " (" + Mathe.round(percent, 1) + "%)", percent);
            dataset.getData().add(data);
        }
        
        return dataset;
    }
    
    public XYChart.Series getInstrumentsChartDataset(int category) {
        XYChart.Series dataset = new XYChart.Series(); 
        for(Instrument instrument : categories.get(category).stream().sorted((Instrument o1, Instrument o2) -> {return o1.getName().compareTo(o2.getName());}).collect(toList())) {
            double percent = Mathe.percentOf(MAX_NOTES, instrument.getNotes().size())*100;
            XYChart.Data<String, Number> data = new XYChart.Data(Strings.normalizeString(instrument.getName(), "_") + " (" + Mathe.round(percent, 1) + "%)", percent);
            dataset.getData().add(data);
        }
        
        return dataset;
    }

}
