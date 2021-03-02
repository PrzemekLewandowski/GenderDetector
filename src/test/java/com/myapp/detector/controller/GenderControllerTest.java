package com.myapp.detector.controller;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GenderControllerTest implements WithAssertions {

    private static final String UNSUPPORTED_OPERATION_EXCEPTION_MESSAGE = "You need to provide one of the two options at the end of the input: FIRST TOKEN or ALL TOKENS";


    @Autowired
    private GenderController genderController;

    @Test
    void shouldThrowUnsupportedOperationExceptionWhenInvalidInput() {
        // given
        String input = "invalid input";

        // then
        assertThatThrownBy(() -> genderController.detectGender(input)).isInstanceOf(UnsupportedOperationException.class)
                .hasMessageContaining(UNSUPPORTED_OPERATION_EXCEPTION_MESSAGE);
    }
}
