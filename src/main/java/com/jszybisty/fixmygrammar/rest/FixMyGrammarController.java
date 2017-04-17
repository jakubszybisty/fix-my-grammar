package com.jszybisty.fixmygrammar.rest;

import com.jszybisty.fixmygrammar.service.Language;
import com.jszybisty.fixmygrammar.service.repetition.RepetitionFinder;
import com.jszybisty.fixmygrammar.service.synonym.SynonymFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.jszybisty.fixmygrammar.rest.AbstractController.*;

/**
 * Created by jakub on 20.03.2017.
 */
@RestController
public class FixMyGrammarController {

    private final SynonymFinderService synonymFinderService;
    private final RepetitionFinder repetitionFinder;

    @Autowired
    public FixMyGrammarController(SynonymFinderService synonymFinderService, RepetitionFinder repetitionFinder) {
        this.synonymFinderService = synonymFinderService;
        this.repetitionFinder = repetitionFinder;
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
        return new ResponseEntity<>(synonymFinderService.findSynonyms(Language.valueOf(language.toUpperCase()), word), HttpStatus.OK);
    }

    /**
     * Finds all word repetitions in given text. Takes word varations into consideration.
     * Supports only two polish language at the moment.
     *
     * @param text the text to be analyzed.
     * @return map of words and its repetitions.
     */
    @GetMapping(value = REPETITIONS, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Map<String, List<String>>> checkForRepetitions(@RequestParam(TEXT) String text) {
        return new ResponseEntity(repetitionFinder.findRepetitions(text), HttpStatus.OK);
    }

}
