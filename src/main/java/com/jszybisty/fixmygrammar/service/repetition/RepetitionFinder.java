package com.jszybisty.fixmygrammar.service.repetition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by jakub on 15.04.2017.
 */
@Component
public class RepetitionFinder {

    private final WordMetadataProvider wordMetadataProvider;

    @Autowired
    public RepetitionFinder(WordMetadataProvider wordMetadataProvider) {
        this.wordMetadataProvider = wordMetadataProvider;
    }

    public Map<String, List<String>> findRepetitions(String text) {
        List<String> words = TextParser.parseTextToWords(text);
        return words
                .stream()
                .map(findWordMetadata())
                .filter(w -> w.isPresent())
                .map(o -> o.get())
                .collect(groupByBaseWord())
                .entrySet()
                .stream()
                .filter(isRepetition())
                .collect(toMap());
    }

    private Function<String, Optional<WordMetadata>> findWordMetadata() {
        return w -> wordMetadataProvider.provideWordMetadata(w);
    }

    private Collector<Map.Entry<String, List<String>>, ?, Map<String, List<String>>> toMap() {
        return Collectors.toMap(e -> e.getKey(), e -> e.getValue());
    }

    private Predicate<Map.Entry<String, List<String>>> isRepetition() {
        return e -> e.getValue().size() > 1;
    }

    private Collector<WordMetadata, ?, Map<String, List<String>>> groupByBaseWord() {
        return Collectors.groupingBy(WordMetadata::getWordBaseForm, Collectors.mapping(WordMetadata::getWordDeclension, Collectors.toList()));
    }
}
