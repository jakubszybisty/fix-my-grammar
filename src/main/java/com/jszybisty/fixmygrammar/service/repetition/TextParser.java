package com.jszybisty.fixmygrammar.service.repetition;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jakub on 15.04.2017.
 */
public abstract class TextParser {

    public static List<String> parseTextToWords(String text) {
        final List<String> words = new ArrayList<>();
        Pattern pattern = Pattern.compile("[A-Za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ]+");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            words.add(matcher.group());
        }
        return words;
    }
}
