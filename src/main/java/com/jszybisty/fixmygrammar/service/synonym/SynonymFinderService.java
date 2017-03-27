package com.jszybisty.fixmygrammar.service.synonym;

import com.jszybisty.fixmygrammar.service.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.List;

/**
 * Created by jakub on 20.03.2017.
 */
@Service
public class SynonymFinderService {

    private EnumMap<Language, SynonymFinder> synonymFinders = new EnumMap<>(Language.class);

    @Autowired
    public SynonymFinderService(PolishSynonymFinder polishSynonymFinder,
                                EnglishSynonymFinder englishSynonymFinder) {
        synonymFinders.put(Language.PL, polishSynonymFinder);
        synonymFinders.put(Language.EN, englishSynonymFinder);
    }

    public List<String> findSynonyms(Language language, String word) {
        return synonymFinders.get(language).findSynonyms(word);
    }
}
