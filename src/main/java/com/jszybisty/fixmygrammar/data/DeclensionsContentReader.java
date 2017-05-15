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
 * Reads all lines from declension file. Decorator to the BasicContentFileReader class.
 */
@Component
public class DeclensionsContentReader {

    /** Regexp splitting string by comma */
    private static final String SPLIT_BY_COMMAS_REGEX = "\\s*,\\s*";

    /**Instance of BasicContentFileReader */
    private final BasicContentFileReader basicContentFileReader;

    @Autowired
    public DeclensionsContentReader(BasicContentFileReader basicContentFileReader) {
        this.basicContentFileReader = basicContentFileReader;
    }

    /**
     * Reads all lines from declension file and merges them to the list of declension entries
     *
     * @param fileName name of the file
     * @return list of DeclensionEntries
     */
    public List<DeclensionEntry> readContentFromFile(String fileName) {
        return basicContentFileReader.readContentFromFile(fileName)
                .stream()
                .map(s -> s.split(SPLIT_BY_COMMAS_REGEX))
                .collect(Collectors.toMap(e -> e[0], e -> Arrays.asList(e), mergeForDuplicateKeys()))
                .entrySet().stream().map(e -> new DeclensionEntry(e.getKey(), e.getValue())).collect(Collectors.toList());
    }

    /**
     * Merges duplicate keys from file
     */
    private BinaryOperator<List<String>> mergeForDuplicateKeys() {
        return (l1, l2) -> {
            List<String> mergedList = new ArrayList<>();
            mergedList.addAll(l1);
            mergedList.addAll(l2);
            return mergedList;
        };
    }
}
