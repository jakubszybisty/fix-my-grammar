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

    private static final String DECLENSIONS_PL_FILENAME = "declensions-pl.txt";
    private final List<DeclensionEntry> declensions;

    @Autowired
    public DeclensionRepository(DeclensionsContentReader declensionsContentReader) {
        declensions = declensionsContentReader.readContentFromFile(DECLENSIONS_PL_FILENAME);
    }
}
