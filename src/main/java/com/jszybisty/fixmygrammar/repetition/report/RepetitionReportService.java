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
 * Created by jakub on 07.05.2017.
 */
@Component
public class RepetitionReportService {

    private final SentenceParser sentenceParser;
    private final EdgeSentenceParser edgeSentenceParser;
    private final RepetitionFinder repetitionFinder;

    @Autowired
    public RepetitionReportService(SentenceParser sentenceParser, EdgeSentenceParser edgeSentenceParser, RepetitionFinder repetitionFinder) {
        this.sentenceParser = sentenceParser;
        this.edgeSentenceParser = edgeSentenceParser;
        this.repetitionFinder = repetitionFinder;
    }

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
