package com.br.gestaodelicencadesoftware.utils;

public class TextFormatterUtil {

    public static String removeSpecialCharactersAndSpacesFromTexts(String input) {
        if (input == null || input.isBlank()) {
            return "";
        }

        return input.replaceAll("[^a-zA-Z0-9]", "")
                .trim()
                .toUpperCase();
    }

}


