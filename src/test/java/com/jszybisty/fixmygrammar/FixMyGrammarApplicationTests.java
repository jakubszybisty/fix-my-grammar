package com.jszybisty.fixmygrammar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FixMyGrammarApplicationTests {

	private List<String> findWordsFromText(String text) {
		final List<String> words = new ArrayList<>();
		Pattern pattern = Pattern.compile("\\w+");
		Matcher matcher = pattern.matcher(text);
		while (matcher.find()) {
			words.add(matcher.group());
		}
		return words;
	}

	@Test
	public void xd() {
		List<String> wordsFromText = findWordsFromText("Mam na imie kuba. jutro ide grac w pilke? i hope so!");
		System.out.println(wordsFromText);
	}

}
