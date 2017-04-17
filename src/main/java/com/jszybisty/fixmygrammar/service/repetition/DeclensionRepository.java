package com.jszybisty.fixmygrammar.service.repetition;

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

    private final Map<String, List<List<String>>> declensions;
    private final DeclensionsRepositoryDataProvider declensionsRepositoryDataProvider;

    @Autowired
    public DeclensionRepository(DeclensionsRepositoryDataProvider declensionsRepositoryDataProvider) {
        this.declensionsRepositoryDataProvider = declensionsRepositoryDataProvider;
        declensions = declensionsRepositoryDataProvider.readContentFromFile();
    }
}
