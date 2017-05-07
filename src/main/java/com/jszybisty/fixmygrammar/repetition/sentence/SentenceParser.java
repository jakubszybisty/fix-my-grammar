package com.jszybisty.fixmygrammar.repetition.sentence;

import com.jszybisty.fixmygrammar.repetition.word.Word;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jakub on 15.04.2017.
 */
@Component
public class SentenceParser {

    private static final String SPLIT_BY_WORD_REGEXP = "[A-Za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ]+";
    private static final String SPLIT_BY_SENTENCE_REGEXP = "(?<=[a-z])\\.\\s+";

    public List<Sentence> parseTextToSentences(String text) {
        List<Sentence> sentences = new ArrayList<>();
        Pattern pattern = Pattern.compile(SPLIT_BY_WORD_REGEXP);

         parseTextToRawSentences(text)
                .forEach(parseToSentence(sentences, pattern));

        return sentences;
    }

    private Consumer<String> parseToSentence(List<Sentence> sentences, Pattern pattern) {
        return s -> {
            List<Word> words = new ArrayList<>();
            Matcher matcher = pattern.matcher(s);
            while (matcher.find()) {
                words.add(new Word(matcher.group()));
            }
            sentences.add(new Sentence(s, words));
        };
    }

    private List<String> parseTextToRawSentences(String text) {
        return Arrays.asList(text.split(SPLIT_BY_SENTENCE_REGEXP));
    }
}
