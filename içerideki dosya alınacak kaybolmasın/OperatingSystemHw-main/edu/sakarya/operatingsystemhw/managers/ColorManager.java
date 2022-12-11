package edu.sakarya.operatingsystemhw.managers;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import edu.sakarya.operatingsystemhw.enums.Colors;

public class ColorManager {
    /*
    * liste kullanima uygun potansiyel tum renkleri saklar.
    */
    private static List<Colors> colors = new ArrayList<Colors>();
    /*
    * memory leak engellemek için saklanmış random instance
    */
    private static Random random = new SecureRandom();
    /*
    * 255^3 rgb degerini anlamli sekilde parcalayabilmek icin kullanilan sabit
    */

    private static int EFFECTIVE_COLOR_COUNT = 10;

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