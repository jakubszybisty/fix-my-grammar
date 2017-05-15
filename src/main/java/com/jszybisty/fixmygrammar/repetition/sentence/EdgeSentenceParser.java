package com.jszybisty.fixmygrammar.repetition.sentence;

import com.jszybisty.fixmygrammar.repetition.word.Word;
import com.jszybisty.fixmygrammar.repetition.word.WordBaseFormComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by jakub on 07.05.2017.
 */
@Component
public class EdgeSentenceParser {

    /** WordBaseFormComparator instance */
    private final WordBaseFormComparator wordBaseFormComparator;

    @Autowired
    public EdgeSentenceParser(WordBaseFormComparator wordBaseFormComparator) {
        this.wordBaseFormComparator = wordBaseFormComparator;
    }


    /** Creates edge sentences from sentence list*/
    public List<SentenceProxy> parseSentencesToEdgeSentences(List<Sentence> sentences) {
        return IntStream.range(0, sentences.size() - 1).map(i -> i)
                .mapToObj(i -> {
                    List<Word> words = new ArrayList<>();
                    Set<Word> lastTwoWordsFromFirstSentence = new TreeSet<>(wordBaseFormComparator);
                    Set<Word> firsTwoWordsFromSecondSentence = new TreeSet<>(wordBaseFormComparator);
                    lastTwoWordsFromFirstSentence.addAll(sentences.get(i).getLastTwoWords());
                    firsTwoWordsFromSecondSentence.addAll(sentences.get(i + 1).getFirstTwoWords());
                    words.addAll(lastTwoWordsFromFirstSentence);
                    words.addAll(firsTwoWordsFromSecondSentence);
                    return new SentenceProxy("Edge sentence", words, "Message");
                }).collect(Collectors.toList());
    }
}
