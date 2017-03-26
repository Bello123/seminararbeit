package com.daniel.semarbeit.user;

import java.util.stream.Stream;

/**
 *
 * @author Daniel
 */
public enum Categories {
    
    PIANO(1, "Piano"),
    CHROMATIC_PERCUSSION(2, "Chromatic Percussion"),
    ORGAN(3, "Organ"),
    GUITAR(4, "Guitar"),
    BASS(5, "Bass"),
    STRINGS(6, "Strings"),
    ENSEMBLE(7, "Ensemble"),
    BRASS(8, "Brass"),
    REED(9, "Reed"),
    PIPE(10, "Pipe"),
    SYNTH_LEAD(11, "Synth Lead"),
    SYNTH_PAD(12, "Synth Pad"),
    SYNTH_EFFECTS(13, "Synth Effects"),
    ETHNIC(14, "Ethnic"),
    PERCUSSIVE(15, "Percussive"),
    SOUND_EFFECTS(16, "Sound Effects");
    
    private final int ID;
    private final String NAME;

    private Categories(int ID, String NAME) {
        this.ID = ID;
        this.NAME = NAME;
    }

    public int getID() {
        return ID;
    }

    public String getNAME() {
        return NAME;
    }
    
    public static String getCategoryName(int id) {
        return Stream.of(Categories.values()).filter(c -> c.getID() == id).map(c -> c.getNAME()).findFirst().orElse("Undefined");
    }
    
}
