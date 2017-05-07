package com.jszybisty.fixmygrammar.synonym.repository;

import com.jszybisty.fixmygrammar.synonym.entity.SynonymSearch;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jakub on 07.05.2017.
 */
public interface SynonymSearchRepository extends JpaRepository<SynonymSearch, Long> {

}
