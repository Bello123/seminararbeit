package com.daniel.semarbeit.user;

import java.util.ArrayList;

/**
 *
 * @author Daniel
 */
public class Instrument {
    
    private String name;
    private ArrayList<String> notes;

    public Instrument(String name) {
        this.name = name;
        notes = new ArrayList<>();
    }

    public void addNote(String note) {
        if(!notes.contains(note)) {
            notes.add(note);
        }
    }
    
    public String getName() {
        return name;
    }

    public ArrayList<String> getNotes() {
        return notes;
    }
    
}
