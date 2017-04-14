package com.jszybisty.fixmygrammar.service.synonym;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jakub on 20.03.2017.
 */
@Service
public class PolishSynonymFinder implements SynonymFinder {

    private static final String SYNONYMS_PL = "synonyms-polish.txt";
    private static final String SEMICOLON = ";";
    private static final List<String> WORDS;

    static {
        try {
            URI uri = new ClassPathResource(SYNONYMS_PL).getURI();
            Path dataFile = Paths.get(uri);
            WORDS = new ArrayList<>(Files.readAllLines(dataFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
