package com.jszybisty.fixmygrammar.synonym.statistic;

import com.jszybisty.fixmygrammar.Language;
import com.jszybisty.fixmygrammar.synonym.entity.SynonymSearch;
import com.jszybisty.fixmygrammar.synonym.repository.SynonymSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by jakub on 07.05.2017.
 */
@Component
public class SynonymSearchStatisticService {

    private final SynonymSearchRepository synonymSearchRepository;

    @Autowired
    public SynonymSearchStatisticService(SynonymSearchRepository synonymSearchRepository) {
        this.synonymSearchRepository = synonymSearchRepository;
    }

    public SynonymSearchDataSummary getSynonymSearchStatistics() {
        List<SynonymSearch> synonymSearches = synonymSearchRepository.findAll();
        Stream<SynonymSearch> polishSynonymSearches = synonymSearches.stream().filter(s -> s.getLanguage().equals(Language.PL));
        Stream<SynonymSearch> englishSynonymSearches = synonymSearches.stream().filter(s -> s.getLanguage().equals(Language.EN));
        return countSynonymSearches(polishSynonymSearches, englishSynonymSearches);
    }

    private SynonymSearchDataSummary countSynonymSearches(Stream<SynonymSearch> polishSynonymSearches, Stream<SynonymSearch> englishSynonymSearches) {
        return new SynonymSearchDataSummary(
                polishSynonymSearches.collect(countSearches()),
                englishSynonymSearches.collect(countSearches()));
    }

    private Collector<SynonymSearch, ?, Map<String, Long>> countSearches() {
        return Collectors.groupingBy(s -> s.getWord(), Collectors.counting());
    }
}
