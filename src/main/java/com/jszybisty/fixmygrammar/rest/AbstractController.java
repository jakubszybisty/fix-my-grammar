package com.jszybisty.fixmygrammar.rest;

/**
 * Created by jakub on 20.03.2017.
 */
public abstract class AbstractController {
    /**
     * Api Url
     */
    static final String API = "/api/v1";
    static final String SYNONYMS = API + "/synonyms";

    /**
     * Path Variable
     */
    static final String LANGUAGE_PATH_PARAM = "/{language}";

    /**
     * Constants
     */
    static final String WORD = "word";
    static final String LANGUAGE = "language";
}
