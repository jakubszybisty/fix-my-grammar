package com.jszybisty.fixmygrammar.synonym.entity;

import com.jszybisty.fixmygrammar.Language;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Entity class of the SynonymSearch
 */
@NoArgsConstructor
@Entity
@Data
public class SynonymSearch {

    /** Identifier */
    @Id
    @GeneratedValue
    private Long id;

    /** Language of the search */
    @Enumerated(EnumType.STRING)
    private Language language;

    /** Word of the search */
    private String word;

    public SynonymSearch(Language language, String word) {
        this.language = language;
        this.word = word;
    }
}
