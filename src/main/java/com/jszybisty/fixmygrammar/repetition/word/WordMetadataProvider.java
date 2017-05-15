package com.jszybisty.fixmygrammar.repetition.word;

import com.jszybisty.fixmygrammar.repetition.DeclensionEntry;
import com.jszybisty.fixmygrammar.repetition.DeclensionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * Created by jakub on 16.04.2017.
 */
@Component
public class WordMetadataProvider {

    /** Instance of DeclensionRepository */
    private final DeclensionRepository declensionRepository;

    @Autowired
    public WordMetadataProvider(DeclensionRepository declensionRepository) {
        this.declensionRepository = declensionRepository;
    }

    /** Provides word metadata for given declension of the word */
    public Optional<WordMetadata> provideWordMetadata(Word wordDeclension) {
        Optional<DeclensionEntry> declensionEntry = declensionRepository.getDeclensions().stream().filter(containsIgnoreCase(wordDeclension)).findFirst();
        return declensionEntry.map(d -> new WordMetadata(new Word(d.getNormalForm()), wordDeclension));
    }

    private Predicate<DeclensionEntry> containsIgnoreCase(Word word) {
        return d -> d.getDeclensions().stream().filter(i -> i.equalsIgnoreCase(word.getWord())).count() > 0;
    }
}
