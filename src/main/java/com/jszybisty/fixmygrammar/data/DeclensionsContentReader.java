package com.jszybisty.fixmygrammar.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by jakub on 17.04.2017.
 */
@Component
public class DeclensionsContentReader {

    private static final String SPLIT_BY_COMMAS_REGEX = "\\s*,\\s*";

    @Autowired
    private final BasicContentFileReader basicContentFileReader;

    public DeclensionsContentReader(BasicContentFileReader basicContentFileReader) {
        this.basicContentFileReader = basicContentFileReader;
    }

    public Map<String, List<List<String>>> readContentFromFile(String fileName) {
        List<String> strings = basicContentFileReader.readContentFromFile(fileName);
        List<String[]> collect = strings
                .stream()
                .map(s -> s.split(SPLIT_BY_COMMAS_REGEX)).collect(Collectors.toList());
        return collect.stream().collect(Collectors.groupingBy(e -> e[0], Collectors.mapping(e -> Arrays.asList(e), Collectors.toList())));
    }
}
