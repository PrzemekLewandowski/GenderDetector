package com.myapp.detector.utils;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class InputParserTest implements WithAssertions {

    private static final String FIRST_TOKEN_TEST_INPUT = "Magda Tomek Michał First Token";
    private static final String FIRST_TOKEN_TEST_INPUT_2 = "First Token";
    private static final String ALL_TOKENS_TEST_INPUT = "Magda Tomek Michał All Tokens";
    private static final String ALL_TOKENS_TEST_INPUT_2 = "All Tokens";
    private static final int EXPECTED_PARSED_INPUTS_SIZE_FOR_FIRST_TOKEN = 1;
    private static final int EXPECTED_PARSED_INPUTS_SIZE_FOR_ALL_TOKENS = 3;
    private static final String EXPECTED_PARSED_INPUT = "MAGDA";

    @Test
    void shouldParseFirstToken() {
        // when
        List<String> parsedInputs = InputParser.parseInputForFirstToken(FIRST_TOKEN_TEST_INPUT);

        // then
        assertThat(parsedInputs).hasSize(EXPECTED_PARSED_INPUTS_SIZE_FOR_FIRST_TOKEN);
        assertThat(parsedInputs).containsExactly(EXPECTED_PARSED_INPUT);
    }

    @Test
    void shouldParseAllTokens() {
        // when
        List<String> parsedInputs = InputParser.parseInputForAllTokens(ALL_TOKENS_TEST_INPUT);

        // then
        assertThat(parsedInputs).hasSize(EXPECTED_PARSED_INPUTS_SIZE_FOR_ALL_TOKENS);
    }

    @Test
    void shouldReturnEmptyListParseAllTokens() {
        // when
        List<String> parsedInputs = InputParser.parseInputForAllTokens(ALL_TOKENS_TEST_INPUT_2);

        // then
        assertThat(parsedInputs).isEmpty();
    }

    @Test
    void shouldReturnEmptyListParseFirstToken() {
        // when
        List<String> parsedInputs = InputParser.parseInputForFirstToken(FIRST_TOKEN_TEST_INPUT_2);

        // then
        assertThat(parsedInputs).isEmpty();
    }
}
