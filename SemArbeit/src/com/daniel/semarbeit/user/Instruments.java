package com.daniel.semarbeit.user;

import java.util.stream.Stream;

/**
 *
 * @author Daniel
 */
public enum Instruments {
    
    PIANO(1, "Piano"),
    BRIGHT_ACOUSTIC(2, "Bright Acoustic"),
    ELECTRIC_GRAND(3, "Electric Grand"),
    HONKEY_TONK(4, "Honkey Tonk"),
    ELECTRIC_PIANO(5, "Electric Piano"),
    CLAVINET(6, "Clavinet"),
    CELESTA(7, "Celesta"),
    GLOCKENSPIEL(8, "Glockenspiel"),
    MUSIK_BOX(9, "Musik Box"),
    VIBRAPHONE(10, "Vibraphone"),
    MARIMBA(11, "Marimba"),
    XYLOPHONE(12, "Xylophone"),
    TUBULAR_BELLS(13, "Tubular Bells"),
    DULCIMER(14, "Dulcimer"),
    DRAWBAR_ORGAN(15, "Drawbar Organ"),
    PERCUSSIVE_ORGAN(16, "Percussive Organ"),
    ROCK_ORGAN(17, "Rock Organ"),
    CHURCH_ORGAN(18, "Church Organ"),
    REED_ORGAN(19, "Reed Organ"),
    ACCORDIAN(20, "Accordian"),
    HARMONICA(21, "Harmonica"),
    TANGO_ACCORDIAN(22, "Tango Accordian"),
    GUITAR(23, "Guitar"),
    STEEL_STRING_GUITAR(24, "Steel String Guitar"),
    ELECTRIC_JAZZ_GUITAR(25, "Electric Jazz Guitar"),
    ELECTRIC_CLEAN_GUITAR(26, "Electric Clean Guitar"),
    ELECTRIC_MUTED_GUITAR(27, "Electric Muted Guitar"),
    OVERDRIVEN_GUITAR(28, "Overdriven Guitar"),
    DISTORTION_GUITAR(29, "Distortion Guitar"),
    GUITAR_HARMONICS(30, "Guitar Harmonics"),
    ACOUSTIC_BASS(31, "Acoustic Bass"),
    ELECTRIC_BASS_FINGER(32, "Electric Bass Finger"),
    ELECTRIC_BASS_PICK(33, "Electric Bass Pick"),
    FRETLESS_BASS(34, "Fretless Bass"),
    SLAP_BASS_1(35, "Slap Bass 1"),
    SLAP_BASS_2(36, "Slap Bass 2"),
    SYNTH_BASS_1(37, "Synth Bass 1"),
    SYNTH_BASS_2(38, "Synth Bass 2"),
    VIOLIN(39, "Violin"),
    VIOLA(40, "Viola"),
    CELLO(41, "Cello"),
    CONTRABASS(42, "Contrabass"),
    TREMOLO_STRINGS(43, "Tremolo Strings"),
    PIZZICATO_STRINGS(44, "Pizzicato Strings"),
    ORCHESTRAL_STRINGS(45, "Orchestral Strings"),
    TIMPANI(46, "Timpani"),
    STRING_ENSEMBLE_1(47, "String Ensemble 1"),
    STRING_ENSEMBLE_2(48, "String Ensemble 2"),
    SYNTH_STRINGS_1(49, "Synth Strings 1"),
    SYNTH_STRINGS_2(50, "Synth Strings 2"),
    CHOIR_AAHS(51, "Choir \"aahs\""),
    VOICE_OOHS(52, "Voice \"oohs\""),
    SYNTH_VOICE(53, "Synth Voice"),
    ORCHESTRA_HIT(54, "Orchestral Hit"),
    TRUMPET(55, "Trumpet"),
    TROMBONE(56, "Trombone"),
    TUBA(57, "Tuba"),
    MUTED_TRUMPET(58, "Muted Trumped"),
    FRENCH_HORN(59, "French Horn"),
    BRASS_SECTION(60, "Brass Section"),
    SOPRANO_SAX(61, "Sorphan Sax"),
    ALTO_SAX(62, "Alto Sax"),
    TENOR_SAX(63, "Tenor Sax"),
    BARITONE_SAX(64, "Baritone Sax"),
    OBOE(65, "Oboe"),
    ENGLISH_HORN(66, "English Horn"),
    BASSOON(67, "Bassoon"),
    CLARINET(68, "Clarinet"),
    PICCOLO(69, "Piccolo"),
    FLUTE(70, "Flute"),
    RECORDER(71, "Recorder"),
    PAN_FLUTE(72, "Pan Flute"),
    BLOWN_BOTTLE(73, "Blown Bottle"),
    SKAKUHACHI(74, "Skakuhachi"),
    WHISTLE(75, "Whistle"),
    OCARINA(76, "Ocarina"),
    SQUARE(77, "Square"),
    SAWTOOTH(78, "Sawtooth"),
    CALLIOPE(79, "Calliope"),
    CHIFF(80, "Chiff"),
    CHARANG(81, "Charang"),
    VOICE(82, "Voice"),
    FIFTHS(83, "Fifths"),
    NEW_AGE(84, "New Age"),
    WARM(85, "Warm"),
    CHOIR(86, "Choir"),
    BOWED(87, "Bowed"),
    METALLIC(88, "Metallic"),
    HALO(89, "Halo"),
    SWEEP(90, "Sweep"),
    RAIN(91, "Rain"),
    SOUNDTRACK(92, "Soundtrack"),
    CRYSTAL(93, "Crystal"),
    ATMOSPHERE(94, "Atmosphere"),
    BRIGHTNESS(95, "Brightness"),
    GOBLINS(96, "Goblins"),
    ECHOES(97, "Echoes"),
    SITAR(98, "Sitar"),
    BANJO(99, "Banjo"),
    SHAMISEN(100, "Shamisen"),
    KOTO(101, "koto"),
    KALIMBA(102, "Kalimba"),
    BAGPIPE(103, "Bagpipe"),
    FIDDLE(104, "Fiddle"),
    SHANAI(105, "Shanai"),
    TINKLE_BELL(106, "Tinkle Bell"),
    AGOGO(107, "Agogo"),
    STEEL_DRUMS(108, "Steel Drums"),
    WOODBLOCK(109, "Woodblock"),
    TAIKO_DRUM(110, "Taiko Drum"),
    MELODIC_TOM(111, "Melodic Tom"),
    SYNTH_DRUM(112, "Synth Drum"),
    REVERSE_CYMBAL(113, "Reverse Cymbal"),
    GUITAR_FRET_NOISE(114, "Guitar Fret Noise"),
    BREATH_NOISE(115, "Breath Noise"),
    SEASHORE(116, "Seashore"),
    BIRD_TWEET(117, "Bird Tweet"),
    TELEPHONE_RING(118, "Telephone Ring"),
    HELICOPTER(119, "Helicopter"),
    APPLAUSE(120, "Applause"),
    GUNSHOT(121, "Gunshot");
    
    private final int ID;
    private final String NAME;

    private Instruments(int ID, String NAME) {
        this.ID = ID;
        this.NAME = NAME;
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
