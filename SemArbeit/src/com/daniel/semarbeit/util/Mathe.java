package com.daniel.semarbeit.util;

import java.text.DecimalFormat;
import java.util.Random;

/**
 *
 * @author Daniel
 */
public class Mathe {
    
    public static int randomInt(int min, int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }

    public static long randomLong(long min, long max) {
        return min + (long)(Math.random() * ((max - min) + 1));
    }
    
    public static double randomDouble(double min, double max) {
        Random r = new Random();
        return min + (max - min) * r.nextDouble();
    }

    public static float randomFloat(float min, float max) {
        Random r = new Random();
        return min + (max - min) * r.nextFloat();
    }
    
    public static boolean randomBoolean() {
        return Math.random() > 0.5;
    }
    
    public static long randomOutOfList(long...list) {
        return list[randomInt(0, list.length-1)];
    }
    
    public static double randomOutOfList(double...list) {
        return list[randomInt(0, list.length-1)];
    }
    
    public static int randomOutOfList(int...list) {
        return list[randomInt(0, list.length-1)];
    }
    
    public static boolean isBetween(double x1, double x2, double value) {
        return x1 < value && x2 > value;
    }
    public static boolean isBetweenEqual(double x1, double x2, double value) {
        return x1 <= value && x2 >= value;
    }
    
    public static double angleToRadiens(double angle) {
        return (angle*Math.PI/180);
    }
    
    public static double clamp01(double value) {
        if(value < 0) return 0;
        if(value > 1) return 1;
        return value;
    }
    
    public static float clamp01(float value) {
        if(value < 0) return 0;
        if(value > 1) return 1;
        return value;
    }
    
    public static double clamp(double min, double max, double value) {
        if(value < min) return min;
        if(value > max) return max;
        return value;
    }
    
    public static float clamp(float min, float max, float value) {
        if(value < min) return min;
        if(value > max) return max;
        return value;
    }
    
    public static double percentOf(double max, double x) {
        return x/max;
    }
    
    public static float sigmoid(float x) {
        float et = (float)Math.pow(Math.E, x);
        et = (et / (1 + et))*2 - 1;
        return et;
    }
    
    public static String truncateToString(double x, int decimals) {
        String pattern = "#0";
        
        if(decimals > 0) pattern += ".";
        for(int i=0;i<decimals;i++) pattern += "0";
        
        DecimalFormat format = new DecimalFormat(pattern);
        
        return format.format(x);
    }
    
    public static double truncateToDouble(double x, int decimals) {
        return Double.valueOf(truncateToString(x, decimals));
    }
    
    
}
