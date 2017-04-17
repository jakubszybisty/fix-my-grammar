package com.jszybisty.fixmygrammar.service.synonym;

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

    private final List<String> WORDS;
    private static final String SYNONYMS_PL = "synonyms-polish.txt";
    private static final String SEMICOLON = ";";

    @Autowired
    public PolishSynonymFinder(BasicContentFileReader basicContentFileReader) {
        WORDS = basicContentFileReader.readContentFromFile(SYNONYMS_PL);
    }

    @Override
    public List<String> findSynonyms(String word) {
        return WORDS.stream()
                .filter(row -> row.startsWith(word.concat(SEMICOLON).toLowerCase()))
                .map(row -> beautifyResponse(word, row))
                .collect(Collectors.toList());
    }

    private String beautifyResponse(String word, String row) {
        row = row.replace(word.concat(SEMICOLON), word.concat(" - "));
        row = row.replaceAll(SEMICOLON, ", ");
        return row;
    }
}
