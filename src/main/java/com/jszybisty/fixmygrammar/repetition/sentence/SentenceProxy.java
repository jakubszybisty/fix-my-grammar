package com.jszybisty.fixmygrammar.repetition.sentence;

import com.jszybisty.fixmygrammar.repetition.word.Word;
import lombok.Getter;

import java.util.List;

/**
 * Created by jakub on 07.05.2017.
 */
@Getter
public class SentenceProxy extends Sentence {

    private String message;

    public SentenceProxy(String formattedSentence, List<Word> words, String message) {
        super(formattedSentence, words);
        this.message = message;
    }
}
