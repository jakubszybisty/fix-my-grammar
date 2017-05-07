package com.jszybisty.fixmygrammar.synonym;

import java.util.List;

/**
 * Created by jakub on 20.03.2017.
 */
public interface SynonymFinder {

    List<String> findSynonyms(String word);
}
