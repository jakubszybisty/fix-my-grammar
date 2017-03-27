package com.jszybisty.fixmygrammar.rest;

import com.jszybisty.fixmygrammar.service.Language;
import com.jszybisty.fixmygrammar.service.synonym.SynonymFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.jszybisty.fixmygrammar.rest.AbstractController.*;

/**
 * Created by jakub on 20.03.2017.
 */
@RestController
public class FixMyGrammarController {

    @Autowired
    private SynonymFinderService synonymFinderService;

    /**
     * Returns all the synonyms of given word in given language.
     * Supports only two languages at the moment - english and polish.
     * @param language the language of the word
     * @param word synonyms of this word are returned
     * @return list of synonyms
     */
    @GetMapping(value = SYNONYMS + LANGUAGE_PATH_PARAM, produces = MediaType.APPLICATION_JSON_VALUE)
    List<String> findSynonyms(@PathVariable(LANGUAGE) String language,
                              @RequestParam(WORD) String word) {
        return synonymFinderService.findSynonyms(Language.valueOf(language.toUpperCase()), word);
    }
}
