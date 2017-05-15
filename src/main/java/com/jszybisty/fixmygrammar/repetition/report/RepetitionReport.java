package com.jszybisty.fixmygrammar.repetition.report;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * Repetition report containing all repetitions from given text
 */
@AllArgsConstructor
@Getter
public class RepetitionReport {
    private List<RepetitionEntry> repetitionEntries;

    /** Adds new repetitionsEntries to the report */
    public void addMoreRepetitionsEntries(List<RepetitionEntry> repetitionEntries) {
        this.repetitionEntries.addAll(repetitionEntries);
    }
}


