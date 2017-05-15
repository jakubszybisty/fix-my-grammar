package com.jszybisty.fixmygrammar.synonym;

import com.jszybisty.fixmygrammar.data.BasicContentFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jakub on 20.03.2017.
 */
@Service
public class PolishSynonymFinder implements SynonymFinder {

    /** List of synonyms read from file */
    private final List<String> WORDS;
    /** Name of the file containing synonyms */
    private static final String SYNONYMS_PL = "synonyms-polish.txt";
    private static final String SEMICOLON = ";";

    /**
     * Reads file contents to WORDS field
     */
    @Autowired
    public PolishSynonymFinder(BasicContentFileReader basicContentFileReader) {
        WORDS = basicContentFileReader.readContentFromFile(SYNONYMS_PL);
    }

    /**
     * Looks for given word in the WORD fields and returns its synonyms in pretty-printed format
     *
     * @param word the word we want synonyms of
     * @return list of strings - synonyms of the word
     */
    @Override
    public List<String> findSynonyms(String word) {
        return WORDS.stream()
                .filter(row -> row.startsWith(word.concat(SEMICOLON).toLowerCase()))
                .map(row -> beautifyResponse(word, row))
                .collect(Collectors.toList());
    }

    /** Beautifies response. Changes semicolons to colons */
    private String beautifyResponse(String word, String row) {
        row = row.replace(word.concat(SEMICOLON), word.concat(" - "));
        row = row.replaceAll(SEMICOLON, ", ");
        return row;
    }
}
