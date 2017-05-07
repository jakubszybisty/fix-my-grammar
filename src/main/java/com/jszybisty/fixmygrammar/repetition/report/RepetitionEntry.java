package com.jszybisty.fixmygrammar.repetition.report;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jszybisty.fixmygrammar.repetition.word.Word;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * Created by jakub on 06.05.2017.
 */
@AllArgsConstructor
@Getter
public class RepetitionEntry {
    private final Word baseWord;
    private final List<Word> repetitions;
    @JsonProperty("sentence")
    private final String formattedSentence;
}
