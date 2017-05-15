package com.jszybisty.fixmygrammar.repetition.report;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jszybisty.fixmygrammar.repetition.word.Word;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * Immutable class. Contains repetitions of given base word from sentence
 */
@AllArgsConstructor
@Getter
public class RepetitionEntry {
    /** Base form of the word repetitions */
    private final Word baseWord;
    /** List of repetitions of base word from sentence*/
    private final List<Word> repetitions;
    /** Sentence in which repetitions occurred */
    @JsonProperty("sentence")
    private final String formattedSentence;
}
