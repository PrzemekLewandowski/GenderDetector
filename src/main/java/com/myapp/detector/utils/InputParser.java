package com.myapp.detector.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class InputParser {

    private static final int FIRST_NAME_INDEX = 0;
    private static final String DELIMITER = " ";

    private InputParser() {
    }

    public static List<String> parseInputForFirstToken(String input) {
        List<String> names = Arrays.stream(input.split(DELIMITER))
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        return names.size() > 2 ? List.of(names.get(FIRST_NAME_INDEX)) : List.of();
    }

    public static List<String> parseInputForAllTokens(String input) {
        List<String> names = Arrays.stream(input.split(DELIMITER))
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        return names.subList(0, names.size() - 2);
    }
}
