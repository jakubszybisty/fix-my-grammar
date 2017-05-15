package com.jszybisty.fixmygrammar.synonym;

import com.jszybisty.fixmygrammar.data.BasicContentFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by jakub on 20.03.2017.
 */
@Service
public class EnglishSynonymFinder implements SynonymFinder {

    /** Name of the file containing synonyms */
    private static final String SYNONYMS_EN = "synonyms-english.txt";
    /** List of synonyms read from file */
    private final List<String> WORDS;

    @Autowired
    public EnglishSynonymFinder(BasicContentFileReader basicContentFileReader) {
        WORDS = basicContentFileReader.readContentFromFile(SYNONYMS_EN);
    }

    /**
     * Looks for given word in the WORD fields and returns its synonyms in pretty-printed format
     *
     * @param word the word we want synonyms of
     * @return list of strings - synonyms of the word
     */
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
