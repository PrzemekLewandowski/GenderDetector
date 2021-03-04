package com.myapp.detector.service;

import com.myapp.detector.domain.Result;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class GenderServiceTest implements WithAssertions {

    private static final String MALE_NAME = "Andrzej";
    private static final String FEMALE_NAME = "Anita";
    private static final String INCONCLUSIVE_NAME = "Damian";
    private static final String NOT_EXISTING_NAME = "Noname";
    private static final String NAME_NOT_FOUND_MESSAGE = "There is no name in both files.";

    @MockBean
    private FileService fileService;

    private GenderService genderService;

    @BeforeAll
    public void init() {
        genderService = new GenderService(fileService);
    }

    @Test
    void shouldDetectMaleGender() {
        // when
        when(fileService.countFemaleNames(any())).thenReturn(0L);
        when(fileService.countMaleNames(any())).thenReturn(1L);
        ResponseEntity<String> responseEntity = genderService.detectGenderByFirstToken(MALE_NAME);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(Result.MALE.name());
    }

    @Test
    void shouldDetectFemaleGender() {
        // when
        when(fileService.countFemaleNames(any())).thenReturn(1L);
        when(fileService.countMaleNames(any())).thenReturn(0L);
        ResponseEntity<String> responseEntity = genderService.detectGenderByFirstToken(FEMALE_NAME);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(Result.FEMALE.name());
    }

    @Test
    void shouldDetectInconclusiveGender() {
        // when
        when(fileService.countFemaleNames(any())).thenReturn(1L);
        when(fileService.countMaleNames(any())).thenReturn(1L);
        ResponseEntity<String> responseEntity = genderService.detectGenderByFirstToken(INCONCLUSIVE_NAME);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(Result.INCONCLUSIVE.name());
    }

    @Test
    void shouldReturnResponseEntityWithNotFoundHttpStatus() {
        // when
        when(fileService.countFemaleNames(any())).thenReturn(0L);
        when(fileService.countMaleNames(any())).thenReturn(0L);
        ResponseEntity<String> responseEntity = genderService.detectGenderByFirstToken(NOT_EXISTING_NAME);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(responseEntity.getBody()).isEqualTo(NAME_NOT_FOUND_MESSAGE);
    }
}
