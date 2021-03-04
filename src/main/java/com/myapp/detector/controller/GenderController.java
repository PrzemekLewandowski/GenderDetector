package com.myapp.detector.controller;

import com.myapp.detector.controller.exception.ResponseStreamException;
import com.myapp.detector.service.FileService;
import com.myapp.detector.service.GenderService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class GenderController {

    private static final String FIRST_TOKEN_OPTION = "FIRST TOKEN";
    private static final String ALL_TOKENS_OPTION = "ALL TOKENS";
    private static final String UNSUPPORTED_OPERATION_EXCEPTION_MESSAGE = "You need to provide one of the two options at the end of the input: FIRST TOKEN or ALL TOKENS";
    private static final String RESPONSE_STREAM_EXCEPTION_MESSAGE = "Can't write response to output stream.";
    private static final String FILE_NAME = "names.zip";
    private static final String CONTENT_DISPOSITION = "Content-Disposition";
    private static final String ATTACHMENT_FILENAME = "attachment; filename=";

    private final GenderService genderService;
    private final FileService fileService;

    @GetMapping("/detectGender")
    public ResponseEntity<String> detectGender(String input) {
        if (StringUtils.containsIgnoreCase(input, FIRST_TOKEN_OPTION)) {
            return genderService.detectGenderByFirstToken(input);
        } else if (StringUtils.containsIgnoreCase(input, ALL_TOKENS_OPTION)) {
            return genderService.detectGenderByAllTokens(input);
        }
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_EXCEPTION_MESSAGE);
    }

    @GetMapping("/getTokens")
    public void getTokens(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_OK);
        response.addHeader(CONTENT_DISPOSITION, ATTACHMENT_FILENAME + "\"" + FILE_NAME + "\"");
        byte[] zipByteArray = fileService.createZipByteArrayFromResources();
        try {
            response.getOutputStream().write(zipByteArray);
        } catch (IOException e) {
            throw new ResponseStreamException(RESPONSE_STREAM_EXCEPTION_MESSAGE, e);
        }
    }
}

