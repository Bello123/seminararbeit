package com.daniel.semarbeit.util;

/**
 *
 * @author Daniel
 */
public class Strings {
    
    public static String normalizeString(String s, String delimiter) {
        String output = "";
        String[] strings;
        if(delimiter != null) {
            strings = s.split(delimiter);
        } else {
            strings = new String[] {s};
        }
        
        for(String string : strings) {
            output += string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase() + " ";
        }
        
        return output.trim();
    }
    
    public static String serializeString(String s) {
        return s.trim().replaceAll(" ", "_");
    }
    
    public static String deserializeString(String s) {
        return s.replaceAll("_", " ");
    }

}
