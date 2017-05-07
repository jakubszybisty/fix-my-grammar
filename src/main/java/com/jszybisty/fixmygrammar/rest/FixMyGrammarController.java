package com.jszybisty.fixmygrammar.rest;

import com.jszybisty.fixmygrammar.Language;
import com.jszybisty.fixmygrammar.repetition.report.RepetitionReport;
import com.jszybisty.fixmygrammar.repetition.report.RepetitionReportService;
import com.jszybisty.fixmygrammar.synonym.SynonymFinderService;
import com.jszybisty.fixmygrammar.synonym.entity.SynonymSearch;
import com.jszybisty.fixmygrammar.synonym.repository.SynonymSearchRepository;
import com.jszybisty.fixmygrammar.synonym.statistic.SynonymSearchDataSummary;
import com.jszybisty.fixmygrammar.synonym.statistic.SynonymSearchStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.jszybisty.fixmygrammar.rest.AbstractController.*;

/**
 * Created by jakub on 20.03.2017.
 */
@RestController
public class FixMyGrammarController {

    private final SynonymFinderService synonymFinderService;
    private final RepetitionReportService repetitionReportService;
    private final SynonymSearchRepository synonymSearchRepository;
    private final SynonymSearchStatisticService synonymSearchStatisticService;

    @Autowired
    public FixMyGrammarController(SynonymFinderService synonymFinderService, RepetitionReportService repetitionReportService, SynonymSearchRepository synonymSearchRepository, SynonymSearchStatisticService synonymSearchStatisticService) {
        this.synonymFinderService = synonymFinderService;
        this.repetitionReportService = repetitionReportService;
        this.synonymSearchRepository = synonymSearchRepository;
        this.synonymSearchStatisticService = synonymSearchStatisticService;
    }

    /**
     * Returns all the synonyms of given word in given language.
     * Supports only two languages at the moment - english and polish.
     *
     * @param language the language of the word.
     * @param word     synonyms of this word are returned.
     * @return list of synonyms.
     */
    @GetMapping(value = SYNONYMS + LANGUAGE_PATH_PARAM, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<String>> findSynonyms(@PathVariable(LANGUAGE) String language,
                                              @RequestParam(WORD) String word) {
        synonymSearchRepository.save(new SynonymSearch(getLanguage(language), word));
        return new ResponseEntity<>(synonymFinderService.findSynonyms(getLanguage(language), word), HttpStatus.OK);
    }

    /**
     * Finds all word repetitions in given text. Takes word varations into consideration.
     * Supports only two polish language at the moment.
     *
     * @param text the text to be analyzed.
     * @return map of words and its repetitions.
     */
    @GetMapping(value = REPETITIONS, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<RepetitionReport> checkForRepetitions(@RequestParam(TEXT) String text) {
        return new ResponseEntity(repetitionReportService.createRepetitionReport(text), HttpStatus.OK);
    }

    /**
     * Returns statistics about all synonym search api calls.
     *
     * @return SynonymSearchDataSummary - wrapper for maps of counted synonym search api calls for PL and EN language.
     */
    @GetMapping(value = SYNONYMS + STATISTICS, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SynonymSearchDataSummary> getSynonymSearchStatistics() {
        return new ResponseEntity<>(synonymSearchStatisticService.getSynonymSearchStatistics(), HttpStatus.OK);
    }

    private static Language getLanguage(String language) {
        return Language.valueOf(language.toUpperCase());
    }

}
