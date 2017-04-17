package com.jszybisty.fixmygrammar.service.repetition;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by jakub on 15.04.2017.
 */
@Component
public class DeclensionsRepositoryDataProvider {

    private static final String DECLENSIONS_PL_FILENAME = "declensions-pl.txt";
    private static final String SPLIT_BY_COMMAS_REGEX = "\\s*,\\s*";

    Map<String, List<List<String>>> readContentFromFile() {
        try {
            URI uri = new ClassPathResource(DECLENSIONS_PL_FILENAME).getURI();
            Path dataFile = Paths.get(uri);
            return convertRawLinesToOrderedMap(Files.readAllLines(dataFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String, List<List<String>>> convertRawLinesToOrderedMap(List<String> strings) {
        List<String[]> collect = strings
                .stream()
                .map(s -> s.split(SPLIT_BY_COMMAS_REGEX)).collect(Collectors.toList());
        return collect.stream().collect(Collectors.groupingBy(e -> e[0], Collectors.mapping(e -> Arrays.asList(e), Collectors.toList())));
    }
}
