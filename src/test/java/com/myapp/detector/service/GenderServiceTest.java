package com.myapp.detector.service;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class GenderServiceTest implements WithAssertions {

    @MockBean
    private NameCounter nameCounter;

    private GenderService genderService;

    @BeforeAll
    public void init() {
        genderService = new GenderService(nameCounter);
    }

    @Test
    void shouldDetectMaleGender() throws IOException, URISyntaxException {
        // given
        String name = "Andrzej";

        // when
        when(nameCounter.countFemaleNames(any())).thenReturn(0L);
        when(nameCounter.countMaleNames(any())).thenReturn(1L);
        ResponseEntity<String> responseEntity = genderService.detectGenderByFirstToken(name);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo("MALE");
    }
}
