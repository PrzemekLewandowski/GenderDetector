package com.myapp.detector.service;

import com.myapp.detector.service.exception.FileCrawlerException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;

@RequiredArgsConstructor
@Service
public class GenderService {
    private static final String DELIMITER = " ";
    private static final String MALE = "MALE";
    private static final String FEMALE = "FEMALE";
    private static final String INCONCLUSIVE = "INCONCLUSIVE";
    private final FileCrawler fileCrawler;

    public ResponseEntity<String> detectGenderByFirstToken(String input) {
        String name = input.split(DELIMITER)[0].toUpperCase();
        try {
            boolean maleFileContainsName = fileCrawler.doesMaleFileContainsName(name);
            boolean femaleFileContainsName = fileCrawler.doesFemaleFileContainsName(name);
            if (maleFileContainsName && !femaleFileContainsName) {
                return new ResponseEntity<>(MALE, HttpStatus.OK);
            } else if (!maleFileContainsName && femaleFileContainsName) {
                return new ResponseEntity<>(FEMALE, HttpStatus.OK);
            } else if (maleFileContainsName) {
                return new ResponseEntity<>(INCONCLUSIVE, HttpStatus.OK);
            }
        } catch (IOException | URISyntaxException e) {
            throw new FileCrawlerException("There is problem with file.", e);
        }
        return new ResponseEntity<>("There is no name in both files.", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> detectGenderByAllTokens(String input) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
