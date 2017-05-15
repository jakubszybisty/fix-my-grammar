package com.jszybisty.fixmygrammar.repetition.word;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Immutable class for word and its declensions.
 */
@AllArgsConstructor
@Getter
@ToString
public class WordMetadata {
    /** word base form*/
    private final Word wordBaseForm;
    /** given declension of the word */
    private final Word wordDeclension;
}
