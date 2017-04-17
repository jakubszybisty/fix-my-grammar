package com.jszybisty.fixmygrammar.service.repetition;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by jakub on 16.04.2017.
 */
@AllArgsConstructor
@Getter
public class WordMetadata {
    private final String wordBaseForm;
    private final String wordDeclension;
}
