package com.daniel.semarbeit.user;

/**
 *
 * @author Daniel
 */
public class Note {
    
    private int id;

    public Note(int id) {
        this.id = id;
    }

    public String getName() {
        return Notes.getNoteName(id);
    }

    public int getId() {
        return id;
    }
    
}
