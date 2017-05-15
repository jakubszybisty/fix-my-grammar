package com.jszybisty.fixmygrammar.repetition;

import com.jszybisty.fixmygrammar.data.DeclensionsContentReader;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jakub on 15.04.2017.
 */
@Component
@Getter
public class DeclensionRepository {

    /** Filename of the file containing declensions */
    private static final String DECLENSIONS_PL_FILENAME = "declensions-pl.txt";
    /** Collection of all declensions */
    private final List<DeclensionEntry> declensions;

    /** Constructor. Populates declensions field with all declensions from file */
    @Autowired
    public DeclensionRepository(DeclensionsContentReader declensionsContentReader) {
        declensions = declensionsContentReader.readContentFromFile(DECLENSIONS_PL_FILENAME);
    }
}
