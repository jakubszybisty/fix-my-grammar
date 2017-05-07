package com.jszybisty.fixmygrammar.data;

import com.jszybisty.fixmygrammar.repetition.DeclensionEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

/**
 * Created by jakub on 17.04.2017.
 */
@Component
public class DeclensionsContentReader {

    private static final String SPLIT_BY_COMMAS_REGEX = "\\s*,\\s*";

    private final BasicContentFileReader basicContentFileReader;

    @Autowired
    public DeclensionsContentReader(BasicContentFileReader basicContentFileReader) {
        this.basicContentFileReader = basicContentFileReader;
    }

    public List<DeclensionEntry> readContentFromFile(String fileName) {
        return basicContentFileReader.readContentFromFile(fileName)
                .stream()
                .map(s -> s.split(SPLIT_BY_COMMAS_REGEX))
                .collect(Collectors.toMap(e -> e[0], e -> Arrays.asList(e), mergeForDuplicateKeys()))
                .entrySet().stream().map(e -> new DeclensionEntry(e.getKey(), e.getValue())).collect(Collectors.toList());
    }

    private BinaryOperator<List<String>> mergeForDuplicateKeys() {
        return (l1, l2) -> {
            List<String> mergedList = new ArrayList<>();
            mergedList.addAll(l1);
            mergedList.addAll(l2);
            return mergedList;
        };
    }
}
