package com.jszybisty.fixmygrammar.data;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jakub on 17.04.2017.
 */
@Component
public class BasicContentFileReader {

    /**
     * Reads all lines from given file
     *
     * @param fileName name of the file
     * @return Collection of lines from file
     */
    public List<String> readContentFromFile(String fileName) {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
        return new BufferedReader(new InputStreamReader(resourceAsStream,
                StandardCharsets.UTF_8)).lines().collect(Collectors.toList());
    }
}
