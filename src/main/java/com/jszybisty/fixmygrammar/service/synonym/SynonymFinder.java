package com.jszybisty.fixmygrammar.service.synonym;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jakub on 20.03.2017.
 */
public abstract class SynonymFinder {

    abstract List<String> findSynonyms(String word);

    static List<String> readContentFromFile(String fileName) {
        try {
            URI uri = new ClassPathResource(fileName).getURI();
            Path dataFile = Paths.get(uri);
            return new ArrayList<>(Files.readAllLines(dataFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
