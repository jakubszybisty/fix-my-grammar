package com.jszybisty.fixmygrammar.synonym.entity;

import com.jszybisty.fixmygrammar.Language;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by jakub on 07.05.2017.
 */
@NoArgsConstructor
@Entity
@Data
public class SynonymSearch {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private Language language;

    private String word;

    public SynonymSearch(Language language, String word) {
        this.language = language;
        this.word = word;
    }
}
