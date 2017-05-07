package com.jszybisty.fixmygrammar.repetition.sentence;

import com.jszybisty.fixmygrammar.repetition.word.Word;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * Created by jakub on 06.05.2017.
 */
@AllArgsConstructor
@Getter
public class Sentence {
    private String formattedSentence;
    private List<Word> words;

    public List<Word> getLastTwoWords() {
        if (words.size() > 2) {
            return words.subList(words.size() - 2, words.size());
        }
        return words;
    }

    public List<Word> getFirstTwoWords() {
        if (words.size() > 2) {
            return words.subList(0, 2);
        }
        return words;
    }


}
