package edu.sakarya.operatingsystemhw.managers;

import edu.sakarya.operatingsystemhw.enums.Colors;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ColorManager {
    /*
    * Liste kullanıma uygun potansiyel tüm renkleri saklar.
    */
    private static List<Colors> colors = new ArrayList<Colors>();
    /*
    * Memory leak engellemek için saklanmış random instance
    */
    private static Random random = new SecureRandom();
    /*
    * 255^3 rgb değerini anlamlı şekilde parçalayabilmek için kullanılan sabit
    */

    static {
        ColorManager.fillColors();   
    }
    
    private static void fillColors() {
        Collections.addAll(ColorManager.colors, Colors.values());
    }
    public static Colors takeRandomColor() {
        int index = random.nextInt(colors.size());
        return colors.remove(index);
    }

    public static void freeColor(Colors color) {
        colors.add(color);
    }
}