package com.jszybisty.fixmygrammar.repetition.report;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * Created by jakub on 06.05.2017.
 */
@AllArgsConstructor
@Getter
public class RepetitionReport {
    private List<RepetitionEntry> repetitionEntries;

    public void addMoreRepetitionsEntries(List<RepetitionEntry> repetitionEntries) {
        this.repetitionEntries.addAll(repetitionEntries);
    }
}


