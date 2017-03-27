package com.jszybisty.fixmygrammar.service.synonym;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jakub on 20.03.2017.
 */
@Service
public class PolishSynonymFinder implements SynonymFinder {

    private static final String fileName = "synonyms-polish.txt";

    @Override
    public List<String> findSynonyms(String word) {
        try {
            URI uri = new ClassPathResource("synonyms-polish.txt").getURI();
            Path dataFile = Paths.get(uri);
            return Files.readAllLines(dataFile).stream()
                    //.map(line -> line.trim().split("(\\s)+"))
                    .filter(row -> row.startsWith(word))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
