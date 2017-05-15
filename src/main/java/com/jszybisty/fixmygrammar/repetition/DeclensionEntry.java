package com.jszybisty.fixmygrammar.repetition;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * Immutable class for word and its declensions
 */
@AllArgsConstructor
@Getter
public final class DeclensionEntry {
    /** Base form of the word */
    private final String normalForm;
    /** All declensions of the word */
    private final List<String> declensions;
}
