package com.myapp.detector.service;

import com.myapp.detector.service.exception.NameCounterException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Service
public class NameCounter {

    @Value("${file.names.male}")
    private Resource maleResource;

    @Value("${file.names.female}")
    private Resource femaleResource;

    public long countMaleNames(List<String> names) {
        return countNames(maleResource, names);
    }

    public long countFemaleNames(List<String> names) {
        return countNames(femaleResource, names);
    }

    private long countNames(Resource resource, List<String> names) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            return reader.lines().filter(names::contains).count();
        } catch (IOException e) {
            throw new NameCounterException("Couldn't read file", e);
        }
    }
}
