package com.jszybisty.fixmygrammar.service.repetition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Created by jakub on 16.04.2017.
 */
@Component
public class WordMetadataProvider {

    private final DeclensionRepository declensionRepository;

    @Autowired
    public WordMetadataProvider(DeclensionRepository declensionRepository) {
        this.declensionRepository = declensionRepository;
    }

    public Optional<WordMetadata> provideWordMetadata(String wordDeclension) {
        Optional<List<List<String>>> declensions = declensionRepository.getDeclensions().values().stream().filter(isPresentInDeclensionLists(wordDeclension)).findFirst();
        return declensions.map(d -> new WordMetadata(getWordBaseForm(d), wordDeclension));
    }

    private Predicate<List<List<String>>> isPresentInDeclensionLists(String word) {
        return declension -> declension.stream().filter(containsIgnoreCase(word)).findAny().isPresent();
    }

    private Predicate<List<String>> containsIgnoreCase(String word) {
        return c -> c.stream().filter(i -> i.equalsIgnoreCase(word)).count() > 0;
    }

    private String getWordBaseForm(List<List<String>> d) { return d.get(0).get(0); }
}
