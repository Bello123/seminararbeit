package com.daniel.semarbeit.user;

/**
 *
 * @author Daniel
 */
public class Category {
    
    public static final String CAT_1 = "Cat 1";
    public static final String CAT_2 = "Cat 2";
    public static final String CAT_3 = "Cat 3";
    public static final String CAT_4 = "Cat 4";
    public static final String CAT_5 = "Cat 5";
    public static final String CAT_6 = "Cat 6";
    public static final String CAT_7 = "Cat 7";
    public static final String CAT_8 = "Cat 8";
    public static final String CAT_9 = "Cat 9";
    public static final String CAT_10 = "Cat 10";
    public static final String CAT_11 = "Cat 11";
    public static final String CAT_12 = "Cat 12";
    public static final String CAT_13 = "Cat 13";
    public static final String CAT_14 = "Cat 14";
    public static final String CAT_15 = "Cat 15";
    public static final String CAT_16 = "Cat 16";
    
    public static String getCategoryName(int id) {
        switch(id) {
            case 1: return CAT_1;
            case 2: return CAT_2;
            case 3: return CAT_3;
            case 4: return CAT_4;
            case 5: return CAT_5;
            case 6: return CAT_6;
            case 7: return CAT_7;
            case 8: return CAT_8;
            case 9: return CAT_9;
            case 10: return CAT_10;
            case 11: return CAT_11;
            case 12: return CAT_12;
            case 13: return CAT_13;
            case 14: return CAT_14;
            case 15: return CAT_15;
            case 16: return CAT_16;
            default: return "UNDEFINED";
        }
    }
    
}
