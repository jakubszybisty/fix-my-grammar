package com.jszybisty.fixmygrammar.service.synonym;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by jakub on 20.03.2017.
 */
@Service
public class EnglishSynonymFinder extends SynonymFinder {

    private static final String SYNONYMS_EN = "synonyms-english.txt";
    private static final List<String> WORDS;

    static  {
       WORDS = readContentFromFile(SYNONYMS_EN);
    }

    @Override
    public List<String> findSynonyms(String word) {
        return WORDS.stream().filter(isCorrectWord(word)).collect(Collectors.toList());
    }

    private Predicate<String> isCorrectWord(String word) {
        return w -> isExactWordLine(w, word) || isCompoundWordLine(w, word);
    }

    private boolean isExactWordLine(String line, String word) {
        return line.startsWith(word.concat(","));
    }

    private boolean isCompoundWordLine(String line, String word) {
        return line.startsWith(word.concat(" "));
    }
}
