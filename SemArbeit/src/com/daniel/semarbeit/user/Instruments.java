package com.daniel.semarbeit.user;

import com.daniel.semarbeit.util.Strings;
import java.util.stream.Stream;

/**
 *
 * @author Daniel
 */
public enum Instruments {
    
    PIANO(Categories.PIANO.getID(), 1),
    BRIGHT_ACOUSTIC(Categories.PIANO.getID(), 2),
    ELECTRIC_GRAND(Categories.PIANO.getID(), 3),
    HONKEY_TONK(Categories.PIANO.getID(), 4),
    ELECTRIC_PIANO(Categories.PIANO.getID(), 5),
    CLAVINET(Categories.PIANO.getID(), 6),
    CELESTA(Categories.CHROMATIC_PERCUSSION.getID(), 7),
    GLOCKENSPIEL(Categories.CHROMATIC_PERCUSSION.getID(), 8),
    MUSIK_BOX(Categories.CHROMATIC_PERCUSSION.getID(), 9),
    VIBRAPHONE(Categories.CHROMATIC_PERCUSSION.getID(), 10),
    MARIMBA(Categories.CHROMATIC_PERCUSSION.getID(), 11),
    XYLOPHONE(Categories.CHROMATIC_PERCUSSION.getID(), 12),
    TUBULAR_BELLS(Categories.CHROMATIC_PERCUSSION.getID(), 13),
    DULCIMER(Categories.CHROMATIC_PERCUSSION.getID(), 14),
    DRAWBAR_ORGAN(Categories.ORGAN.getID(), 15),
    PERCUSSIVE_ORGAN(Categories.ORGAN.getID(), 16),
    ROCK_ORGAN(Categories.ORGAN.getID(), 17),
    CHURCH_ORGAN(Categories.ORGAN.getID(), 18),
    REED_ORGAN(Categories.ORGAN.getID(), 19),
    ACCORDIAN(Categories.ORGAN.getID(), 20),
    HARMONICA(Categories.ORGAN.getID(), 21),
    TANGO_ACCORDIAN(Categories.ORGAN.getID(), 22),
    GUITAR(Categories.GUITAR.getID(), 23),
    STEEL_STRING_GUITAR(Categories.GUITAR.getID(), 24),
    ELECTRIC_JAZZ_GUITAR(Categories.GUITAR.getID(), 25),
    ELECTRIC_CLEAN_GUITAR(Categories.GUITAR.getID(), 26),
    ELECTRIC_MUTED_GUITAR(Categories.GUITAR.getID(), 27),
    OVERDRIVEN_GUITAR(Categories.GUITAR.getID(), 28),
    DISTORTION_GUITAR(Categories.GUITAR.getID(), 29),
    GUITAR_HARMONICS(Categories.GUITAR.getID(), 30),
    ACOUSTIC_BASS(Categories.BASS.getID(), 31),
    ELECTRIC_BASS_FINGER(Categories.BASS.getID(), 32),
    ELECTRIC_BASS_PICK(Categories.BASS.getID(), 33),
    FRETLESS_BASS(Categories.BASS.getID(), 34),
    SLAP_BASS_1(Categories.BASS.getID(), 35),
    SLAP_BASS_2(Categories.BASS.getID(), 36),
    SYNTH_BASS_1(Categories.BASS.getID(), 37),
    SYNTH_BASS_2(Categories.BASS.getID(), 38),
    VIOLIN(Categories.STRINGS.getID(), 39),
    VIOLA(Categories.STRINGS.getID(), 40),
    CELLO(Categories.STRINGS.getID(), 41),
    CONTRABASS(Categories.STRINGS.getID(), 42),
    TREMOLO_STRINGS(Categories.STRINGS.getID(), 43),
    PIZZICATO_STRINGS(Categories.STRINGS.getID(), 44),
    ORCHESTRAL_STRINGS(Categories.STRINGS.getID(), 45),
    TIMPANI(Categories.STRINGS.getID(), 46),
    STRING_ENSEMBLE_1(Categories.ENSEMBLE.getID(), 47),
    STRING_ENSEMBLE_2(Categories.ENSEMBLE.getID(), 48),
    SYNTH_STRINGS_1(Categories.ENSEMBLE.getID(), 49),
    SYNTH_STRINGS_2(Categories.ENSEMBLE.getID(), 50),
    CHOIR_AAHS(Categories.ENSEMBLE.getID(), 51),
    VOICE_OOHS(Categories.ENSEMBLE.getID(), 52),
    SYNTH_VOICE(Categories.ENSEMBLE.getID(), 53),
    ORCHESTRA_HIT(Categories.ENSEMBLE.getID(), 54),
    TRUMPET(Categories.BRASS.getID(), 55),
    TROMBONE(Categories.BRASS.getID(), 56),
    TUBA(Categories.BRASS.getID(), 57),
    MUTED_TRUMPET(Categories.BRASS.getID(), 58),
    FRENCH_HORN(Categories.BRASS.getID(), 59),
    BRASS_SECTION(Categories.BRASS.getID(), 60),
    SOPRANO_SAX(Categories.REED.getID(), 61),
    ALTO_SAX(Categories.REED.getID(), 62),
    TENOR_SAX(Categories.REED.getID(), 63),
    BARITONE_SAX(Categories.REED.getID(), 64),
    OBOE(Categories.REED.getID(), 65),
    ENGLISH_HORN(Categories.REED.getID(), 66),
    BASSOON(Categories.REED.getID(), 67),
    CLARINET(Categories.REED.getID(), 68),
    PICCOLO(Categories.PIPE.getID(), 69),
    FLUTE(Categories.PIPE.getID(), 70),
    RECORDER(Categories.PIPE.getID(), 71),
    PAN_FLUTE(Categories.PIPE.getID(), 72),
    BLOWN_BOTTLE(Categories.PIPE.getID(), 73),
    SKAKUHACHI(Categories.PIPE.getID(), 74),
    WHISTLE(Categories.PIPE.getID(), 75),
    OCARINA(Categories.PIPE.getID(), 76),
    SQUARE(Categories.SYNTH_LEAD.getID(), 77),
    SAWTOOTH(Categories.SYNTH_LEAD.getID(), 78),
    CALLIOPE(Categories.SYNTH_LEAD.getID(), 79),
    CHIFF(Categories.SYNTH_LEAD.getID(), 80),
    CHARANG(Categories.SYNTH_LEAD.getID(), 81),
    VOICE(Categories.SYNTH_LEAD.getID(), 82),
    FIFTHS(Categories.SYNTH_LEAD.getID(), 83),
    NEW_AGE(Categories.SYNTH_PAD.getID(), 84),
    WARM(Categories.SYNTH_PAD.getID(), 85),
    CHOIR(Categories.SYNTH_PAD.getID(), 86),
    BOWED(Categories.SYNTH_PAD.getID(), 87),
    METALLIC(Categories.SYNTH_PAD.getID(), 88),
    HALO(Categories.SYNTH_PAD.getID(), 89),
    SWEEP(Categories.SYNTH_PAD.getID(), 90),
    RAIN(Categories.SYNTH_EFFECTS.getID(), 91),
    SOUNDTRACK(Categories.SYNTH_EFFECTS.getID(), 92),
    CRYSTAL(Categories.SYNTH_EFFECTS.getID(), 93),
    ATMOSPHERE(Categories.SYNTH_EFFECTS.getID(), 94),
    BRIGHTNESS(Categories.SYNTH_EFFECTS.getID(), 95),
    GOBLINS(Categories.SYNTH_EFFECTS.getID(), 96),
    ECHOES(Categories.SYNTH_EFFECTS.getID(), 97),
    SITAR(Categories.ETHNIC.getID(), 98),
    BANJO(Categories.ETHNIC.getID(), 99),
    SHAMISEN(Categories.ETHNIC.getID(), 100),
    KOTO(Categories.ETHNIC.getID(), 101),
    KALIMBA(Categories.ETHNIC.getID(), 102),
    BAGPIPE(Categories.ETHNIC.getID(), 103),
    FIDDLE(Categories.ETHNIC.getID(), 104),
    SHANAI(Categories.ETHNIC.getID(), 105),
    TINKLE_BELL(Categories.PERCUSSIVE.getID(), 106),
    AGOGO(Categories.PERCUSSIVE.getID(), 107),
    STEEL_DRUMS(Categories.PERCUSSIVE.getID(), 108),
    WOODBLOCK(Categories.PERCUSSIVE.getID(), 109),
    TAIKO_DRUM(Categories.PERCUSSIVE.getID(), 110),
    MELODIC_TOM(Categories.PERCUSSIVE.getID(), 111),
    SYNTH_DRUM(Categories.PERCUSSIVE.getID(), 112),
    REVERSE_CYMBAL(Categories.PERCUSSIVE.getID(), 113),
    GUITAR_FRET_NOISE(Categories.SOUND_EFFECTS.getID(), 114),
    BREATH_NOISE(Categories.SOUND_EFFECTS.getID(), 115),
    SEASHORE(Categories.SOUND_EFFECTS.getID(), 116),
    BIRD_TWEET(Categories.SOUND_EFFECTS.getID(), 117),
    TELEPHONE_RING(Categories.SOUND_EFFECTS.getID(), 118),
    HELICOPTER(Categories.SOUND_EFFECTS.getID(), 119),
    APPLAUSE(Categories.SOUND_EFFECTS.getID(), 120),
    GUNSHOT(Categories.SOUND_EFFECTS.getID(), 121);
    
    private final int ID, CATEGORY_ID;
    private final String NAME;

    private Instruments(int CATEGORY_ID, int ID) {
        this.CATEGORY_ID = CATEGORY_ID;
        this.ID = ID;
        this.NAME = Strings.normalizeString(name(), "_");
    }

    public int getCATEGORY_ID() {
        return CATEGORY_ID;
    }

    public int getID() {
        return ID;
    }

    public String getNAME() {
        return NAME;
    }
    
    public static String getInstrumentName(int id) {
        return Stream.of(Instruments.values()).filter(c -> c.getID() == id).map(c -> c.getNAME()).findFirst().orElse("Undefined");
    }
    
}
