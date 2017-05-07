package com.jszybisty.fixmygrammar.repetition;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * Created by jakub on 06.05.2017.
 */
@AllArgsConstructor
@Getter
public final class DeclensionEntry {
    private final String normalForm;
    private final List<String> declensions;
}
