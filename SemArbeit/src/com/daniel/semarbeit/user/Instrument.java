package com.daniel.semarbeit.user;

import java.util.ArrayList;

/**
 *
 * @author Daniel
 */
public class Instrument {
    
    private int id;
    private ArrayList<Note> notes;

    public Instrument(int id) {
        this.id = id;
        notes = new ArrayList<>();
    }

    public void addNote(int note) {
        if(!hasNote(note)) {
            notes.add(new Note(note));
        }
    }
    
    public boolean hasNote(int note) {
        return notes.stream()
                .mapToInt(n -> n.getId())
                .filter(nid -> nid == note)
                .count() >= 1;
    }
    public boolean hasNote(String note) {       
        if(note == null) return false;
        
        if(Character.isDigit(note.charAt(note.length()-1)) || note.equals("R")) {       
            return notes.stream()
                .mapToInt(n -> n.getId())
                .filter(nid -> {                    
                    try {
                        return nid == Notes.valueOf(note).getID();
                    } catch(Exception ex) {
                        return false;
                    }
                })
                .count() >= 1;            
        } else {
            return false;
        }        
    }
    
    public String getName() {
        return Instruments.getInstrumentName(id);
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public int getId() {
        return id;
    }
    
}
