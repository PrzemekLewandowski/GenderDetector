package com.myapp.detector.controller;

import com.myapp.detector.service.GenderService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GenderController {

    private static final String FIRST_TOKEN_OPTION = "FIRST TOKEN";
    private static final String ALL_TOKENS_OPTION = "ALL TOKENS";
    private static final String UNSUPPORTED_OPERATION_EXCEPTION_MESSAGE = "You need to provide one of the two options at the end of the input: FIRST TOKEN or ALL TOKENS";

    private final GenderService genderService;

    @GetMapping("/detectGender")
    public ResponseEntity<String> detectGender(String input) {
        if (StringUtils.containsIgnoreCase(input, FIRST_TOKEN_OPTION)) {
            return genderService.detectGenderByFirstToken(input);
        } else if (StringUtils.containsIgnoreCase(input, ALL_TOKENS_OPTION)) {
            return genderService.detectGenderByAllTokens(input);
        }
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_EXCEPTION_MESSAGE);
    }
}
