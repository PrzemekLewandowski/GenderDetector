package com.myapp.detector.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class FileCrawler {

    @Value(value = "classpath:/names/male_polish_names.txt")
    private Resource maleResource;

    @Value(value = "classpath:/names/female_polish_names.txt")
    private Resource femaleResource;

    public boolean doesMaleFileContainsName(String name) throws IOException, URISyntaxException {
        URL url = maleResource.getURL();
        return doesFileContainName(url, name);
    }

    public boolean doesFemaleFileContainsName(String name) throws IOException, URISyntaxException {
        URL url = femaleResource.getURL();
        return doesFileContainName(url, name);
    }

    private boolean doesFileContainName(URL url, String name) throws IOException, URISyntaxException {
        //TODO: consider this option from Apache Commons IO API : FileUtils.readFileToString(file).contains(stringToFind)
        Path filePath = Paths.get(url.toURI());
        try (Stream<String> file = Files.lines(filePath)) {
            return file.anyMatch(s -> s.equals(name));
        }
    }
}
