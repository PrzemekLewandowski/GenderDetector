package com.myapp.detector.service;

import com.myapp.detector.domain.Result;
import com.myapp.detector.utils.InputParser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GenderService {

    private final NameCounter nameCounter;

    public ResponseEntity<String> detectGenderByFirstToken(String input) {
        List<String> name = InputParser.parseInputForFirstToken(input);
        long maleNamesAmount = nameCounter.countMaleNames(name);
        long femaleNamesAmount = nameCounter.countFemaleNames(name);
        return getResult(maleNamesAmount, femaleNamesAmount);
    }

    public ResponseEntity<String> detectGenderByAllTokens(String input) {
        List<String> names = InputParser.parseInputForAllTokens(input);
        long maleNamesAmount = nameCounter.countMaleNames(names);
        long femaleNamesAmount = nameCounter.countFemaleNames(names);
        return getResult(maleNamesAmount, femaleNamesAmount);
    }

    private ResponseEntity<String> getResult(long maleNamesAmount, long femaleNamesAmount) {
        if (maleNamesAmount > femaleNamesAmount) {
            return new ResponseEntity<>(Result.MALE.name(), HttpStatus.OK);
        } else if (maleNamesAmount < femaleNamesAmount) {
            return new ResponseEntity<>(Result.FEMALE.name(), HttpStatus.OK);
        } else if (maleNamesAmount > 0) {
            return new ResponseEntity<>(Result.INCONCLUSIVE.name(), HttpStatus.OK);
        }
        return new ResponseEntity<>("There is no name in both files.", HttpStatus.NOT_FOUND);
    }
}
