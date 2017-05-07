package com.jszybisty.fixmygrammar.repetition.word;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Optional;

/**
 * Created by jakub on 07.05.2017.
 */
@Component
public class WordBaseFormComparator implements Comparator<Word> {

    private final WordMetadataProvider wordMetadataProvider;

    @Autowired
    public WordBaseFormComparator(WordMetadataProvider wordMetadataProvider) {
        this.wordMetadataProvider = wordMetadataProvider;
    }

    @Override
    public int compare(Word o1, Word o2) {
        Optional<WordMetadata> o1Metadata = wordMetadataProvider.provideWordMetadata(o1);
        Optional<WordMetadata> o2Metadata = wordMetadataProvider.provideWordMetadata(o2);
        if (o1Metadata.isPresent() && o2Metadata.isPresent()) {
            return compareWordBaseForms(o1Metadata.get(), o2Metadata.get());
        }
        return -1;
    }

    private int compareWordBaseForms(WordMetadata first, WordMetadata second) {
        if (first.getWordBaseForm().getWord().equalsIgnoreCase(second.getWordBaseForm().getWord())) {
            return 0;
        }
        return -1;
    }
}
