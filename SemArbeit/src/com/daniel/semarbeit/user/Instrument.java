package com.daniel.semarbeit.user;

import java.util.ArrayList;

/**
 *
 * @author Daniel
 */
public class Instrument {
    
    private int id;
    private ArrayList<String> notes;

    public Instrument(int id) {
        this.id = id;
        notes = new ArrayList<>();
    }

    public void addNote(String note) {
        if(!notes.contains(note)) {
            notes.add(note.toUpperCase());
        }
    }
    
    public String getName() {
        return Instruments.getInstrumentName(id);
    }

    public ArrayList<String> getNotes() {
        return notes;
    }

    public int getId() {
        return id;
    }
    
}
