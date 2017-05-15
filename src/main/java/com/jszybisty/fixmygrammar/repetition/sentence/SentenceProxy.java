package com.jszybisty.fixmygrammar.repetition.sentence;

import com.jszybisty.fixmygrammar.repetition.word.Word;
import lombok.Getter;

import java.util.List;

/**
 * Proxy class pretending to be sentence. Used for grammatical rules of finding repetitions in sentences
 */
@Getter
public class SentenceProxy extends Sentence {

    /** Message that tells which grammatical rule this repetition broken */
    private String message;

    public SentenceProxy(String formattedSentence, List<Word> words, String message) {
        super(formattedSentence, words);
        this.message = message;
    }
}
