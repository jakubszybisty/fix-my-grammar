package com.jszybisty.fixmygrammar.repetition.report;

import com.jszybisty.fixmygrammar.repetition.RepetitionFinder;
import com.jszybisty.fixmygrammar.repetition.sentence.EdgeSentenceParser;
import com.jszybisty.fixmygrammar.repetition.sentence.Sentence;
import com.jszybisty.fixmygrammar.repetition.sentence.SentenceParser;
import com.jszybisty.fixmygrammar.repetition.sentence.SentenceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Class responsible for creating repetition report for given text
 */
@Component
public class RepetitionReportService {

    /** SentenceParser instance */
    private final SentenceParser sentenceParser;
    /** EdgeSentenceParser instance */
    private final EdgeSentenceParser edgeSentenceParser;
    /** RepetitionFinder instance */
    private final RepetitionFinder repetitionFinder;

    @Autowired
    public RepetitionReportService(SentenceParser sentenceParser, EdgeSentenceParser edgeSentenceParser, RepetitionFinder repetitionFinder) {
        this.sentenceParser = sentenceParser;
        this.edgeSentenceParser = edgeSentenceParser;
        this.repetitionFinder = repetitionFinder;
    }

    /** Takes in text to find repetitions. Returns repetitions from each sentence and edge sentences */
    public RepetitionReport createRepetitionReport(String text) {
        List<Sentence> sentences = sentenceParser.parseTextToSentences(text);
        List<SentenceProxy> sentenceProxies = edgeSentenceParser.parseSentencesToEdgeSentences(sentences);
        List<RepetitionEntry> repetitionsFromNaturalSentences = repetitionFinder.findRepetitions(sentences);
        List<RepetitionEntry> repetitionsFromEdgeSentences = repetitionFinder.findRepetitions(sentenceProxies);
        final RepetitionReport repetitionReport = new RepetitionReport(repetitionsFromNaturalSentences);
        repetitionReport.addMoreRepetitionsEntries(repetitionsFromEdgeSentences);
        return repetitionReport;
    }
}
