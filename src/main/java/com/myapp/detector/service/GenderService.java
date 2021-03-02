package com.myapp.detector.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GenderService {
    public ResponseEntity<String> detectGenderByFirstToken(String input) {
        return null;
    }

    public ResponseEntity<String> detectGenderByAllTokens(String input) {
        return null;
    }
}
