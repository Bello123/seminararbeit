package com.daniel.semarbeit.model;

import com.daniel.semarbeit.util.Strings;
import java.util.stream.Stream;

/**
 *
 * @author Daniel
 */
public enum Categories {
    
    PIANO(1),
    CHROMATIC_PERCUSSION(2),
    ORGAN(3),
    GUITAR(4),
    BASS(5),
    STRINGS(6),
    ENSEMBLE(7),
    BRASS(8),
    REED(9),
    PIPE(10),
    SYNTH_LEAD(11),
    SYNTH_PAD(12),
    SYNTH_EFFECTS(13),
    ETHNIC(14),
    PERCUSSIVE(15),
    SOUND_EFFECTS(16);

    private final int ID;
    private final String NAME;

    private Categories(int ID) {
        this.ID = ID;
        this.NAME = Strings.normalizeString(name(), "_");
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
