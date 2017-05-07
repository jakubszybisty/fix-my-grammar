package com.jszybisty.fixmygrammar.repetition.sentence;

import com.jszybisty.fixmygrammar.repetition.word.Word;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by jakub on 07.05.2017.
 */
public class SentenceTest {

    @Test
    public void shouldReturnLast3Words() {
        List<Word> words = Arrays.asList(
                new Word("ala"),
                new Word("ma"),
                new Word("kota"),
                new Word("ala"));
        Sentence sentence = new Sentence("ala ma kota", words);
        List<Word> lastThreeWords = sentence.getLastTwoWords();
        assertEquals(lastThreeWords, Arrays.asList(
                new Word("ma"),
                new Word("kota"),
                new Word("ala")));
    }

    @Test
    public void shouldReturnAllWordsWhenSizeIsLessThan3() {
        List<Word> words = Arrays.asList(
                new Word("ala"),
                new Word("ma"));
        Sentence sentence = new Sentence("ala ma", words);
        List<Word> lastThreeWords = sentence.getLastTwoWords();
        assertEquals(lastThreeWords, Arrays.asList(
                new Word("ala"),
                new Word("ma")));
    }

}