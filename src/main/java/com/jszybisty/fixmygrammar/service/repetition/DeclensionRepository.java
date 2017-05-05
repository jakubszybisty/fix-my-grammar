package com.jszybisty.fixmygrammar.service.repetition;

import com.jszybisty.fixmygrammar.data.DeclensionsContentReader;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by jakub on 15.04.2017.
 */
@Component
@Getter
public class DeclensionRepository {

    private static final String DECLENSIONS_PL_FILENAME = "declensions-pl.txt";
    private final Map<String, List<List<String>>> declensions;

    @Autowired
    public DeclensionRepository(DeclensionsContentReader declensionsContentReader) {
//        declensions = declensionsContentReader.readContentFromFile(DECLENSIONS_PL_FILENAME);
        declensions = null;
    }
}
