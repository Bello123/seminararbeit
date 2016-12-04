package user;

import interfaces.Serializeable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Daniel
 */
public class NoteSet implements Serializeable {

    private HashMap<String, ArrayList<String>> sounds;

    public NoteSet() {
        sounds = new HashMap<>();
    }

    private void initInstrument(String instrument) {
        sounds.put(instrument, new ArrayList<>());
    }
    
    @Override
    public void serialize(String path) throws Exception {
        
    }

    //C:\\Users\\Daniel\\Desktop\\seminararbeit_notes.mc
    @Override
    public void deserialize(String path) throws Exception {
        checkFile(path);

        try (BufferedReader br = new BufferedReader(new FileReader(new File(path)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                String instrument = parts[0];
                initInstrument(instrument);
                for(String note : parts[1].split(";")) {
                    sounds.get(instrument).add(note);
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
    
}
