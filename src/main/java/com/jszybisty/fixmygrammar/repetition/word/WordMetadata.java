package com.jszybisty.fixmygrammar.repetition.word;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by jakub on 16.04.2017.
 */
@AllArgsConstructor
@Getter
@ToString
public class WordMetadata {
    private final Word wordBaseForm;
    private final Word wordDeclension;
}
