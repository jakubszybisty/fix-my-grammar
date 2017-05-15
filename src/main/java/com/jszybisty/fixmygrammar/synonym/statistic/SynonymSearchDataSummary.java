package com.jszybisty.fixmygrammar.synonym.statistic;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

/**
 * Immutable class containing polish and english synonym search statistics
 */
@AllArgsConstructor
@Getter
public class SynonymSearchDataSummary {
    /** Polish synonym search statistics.  */
    private final Map<String, Long> polishSynonymSearches;
    /** English synonym search statistics.  */
    private final Map<String, Long> englishSynonymSearches;
}
