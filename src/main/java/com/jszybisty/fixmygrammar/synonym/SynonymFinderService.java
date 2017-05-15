package com.jszybisty.fixmygrammar.synonym;

import com.jszybisty.fixmygrammar.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.List;

/**
 * Strategy pattern to the SynonymFinder
 */
@Service
public class SynonymFinderService {

    /**Hold language and its implementation for finding synonyms */
    private EnumMap<Language, SynonymFinder> synonymFinders = new EnumMap<>(Language.class);

    @Autowired
    public SynonymFinderService(PolishSynonymFinder polishSynonymFinder,
                                EnglishSynonymFinder englishSynonymFinder) {
        synonymFinders.put(Language.PL, polishSynonymFinder);
        synonymFinders.put(Language.EN, englishSynonymFinder);
    }

    /**
     * Depending on the language, selects proper synonym finder instance and returns synonyms of the given word
     *
     * @param language  enum representation of language of the word
     * @param word synonyms of this word are returned
     * @return list of synonyms
     */
    public List<String> findSynonyms(Language language, String word) {
        return synonymFinders.get(language).findSynonyms(word);
    }
}
