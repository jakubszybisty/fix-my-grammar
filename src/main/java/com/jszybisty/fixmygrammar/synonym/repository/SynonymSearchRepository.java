package com.jszybisty.fixmygrammar.synonym.repository;

import com.jszybisty.fixmygrammar.synonym.entity.SynonymSearch;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Dao class to the SynonymSearch entity.
 */
public interface SynonymSearchRepository extends JpaRepository<SynonymSearch, Long> {

}
