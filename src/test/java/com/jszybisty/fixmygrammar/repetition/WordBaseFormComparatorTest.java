package com.jszybisty.fixmygrammar.repetition;

import com.jszybisty.fixmygrammar.repetition.word.Word;
import com.jszybisty.fixmygrammar.repetition.word.WordBaseFormComparator;
import com.jszybisty.fixmygrammar.repetition.word.WordMetadata;
import com.jszybisty.fixmygrammar.repetition.word.WordMetadataProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by jakub on 07.05.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class WordBaseFormComparatorTest {

    @Mock
    private WordMetadataProvider wordMetadataProvider;
    @InjectMocks
    private WordBaseFormComparator wordBaseFormComparator;

    @Before
    public void setUp() {
        when(wordMetadataProvider.provideWordMetadata(any(Word.class))).thenReturn(Optional.of(new WordMetadata(new Word("test"), new Word("whatever"))));
    }

    @Test
    public void shouldFindDuplicateBaseForms() {
        Set<Word> words = new TreeSet<>(wordBaseFormComparator);
        words.add(new Word("xd"));
        words.add(new Word("xd2"));
        System.out.println("xd");
    }
}