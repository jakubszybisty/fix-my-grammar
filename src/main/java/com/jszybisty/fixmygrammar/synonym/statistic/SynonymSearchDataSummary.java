package com.jszybisty.fixmygrammar.synonym.statistic;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

/**
 * Created by jakub on 07.05.2017.
 */
@AllArgsConstructor
@Getter
public class SynonymSearchDataSummary {
    private final Map<String, Long> polishSynonymSearches;
    private final Map<String, Long> englishSynonymSearches;
}
