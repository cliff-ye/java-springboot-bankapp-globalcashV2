package com.welltech.globalcash.V21.globalcash.helper;

import java.util.Locale;

public class NumberFormatter {
    public static String formatNum(double number){
        String formattedNumber = String.format(Locale.US, "%,.2f", number);

        return formattedNumber;
    }
}
