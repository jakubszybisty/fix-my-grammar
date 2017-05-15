package com.jszybisty.fixmygrammar.repetition;

import com.jszybisty.fixmygrammar.repetition.report.RepetitionEntry;
import com.jszybisty.fixmygrammar.repetition.sentence.Sentence;
import com.jszybisty.fixmygrammar.repetition.sentence.SentenceParser;
import com.jszybisty.fixmygrammar.repetition.word.Word;
import com.jszybisty.fixmygrammar.repetition.word.WordMetadata;
import com.jszybisty.fixmygrammar.repetition.word.WordMetadataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Service class for finding repetitions
 */
@Component
public class RepetitionFinder {

    /** Instance of WordMetadataProvider */
    private final WordMetadataProvider wordMetadataProvider;
    /** Instance of SentenceParser */
    private final SentenceParser sentenceParser;

    @Autowired
    public RepetitionFinder(WordMetadataProvider wordMetadataProvider, SentenceParser sentenceParser) {
        this.wordMetadataProvider = wordMetadataProvider;
        this.sentenceParser = sentenceParser;
    }

    /** Takes in list of sentences. Returns collection of repetitions entries in all of these sentences */
    public List<RepetitionEntry> findRepetitions(List<? extends Sentence> sentences) {
        List<RepetitionEntry> repetitionEntries = new ArrayList<>();
        sentences.forEach(s ->
                s.getWords().stream()
                        .map(findWordMetadata())
                        .filter(w -> w.isPresent())
                        .map(o -> o.get())
                        .collect(groupByBaseWord())
                        .entrySet()
                        .stream()
                        .filter(isRepetition())
                        .map(e -> new RepetitionEntry(e.getKey(), e.getValue(), s.getFormattedSentence()))
                        .forEach(repetitionEntry -> repetitionEntries.add(repetitionEntry)));
        return repetitionEntries;
    }

    private Function<Word, Optional<WordMetadata>> findWordMetadata() {
        return w -> wordMetadataProvider.provideWordMetadata(w);
    }

    private Collector<Map.Entry<Word, List<Word>>, ?, Map<Word, List<Word>>> toMap() {
        return Collectors.toMap(e -> e.getKey(), e -> e.getValue());
    }

    private Predicate<Map.Entry<Word, List<Word>>> isRepetition() {
        return e -> e.getValue().size() > 1;
    }

    private Collector<WordMetadata, ?, Map<Word, List<Word>>> groupByBaseWord() {
        return Collectors.groupingBy(WordMetadata::getWordBaseForm, Collectors.mapping(WordMetadata::getWordDeclension, Collectors.toList()));
    }
}
