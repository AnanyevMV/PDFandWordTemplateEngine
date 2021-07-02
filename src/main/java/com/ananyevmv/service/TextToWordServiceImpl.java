package com.ananyevmv.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TextToWordServiceImpl implements TextToWordService {

    private static final String fileNameSuffix = "-output.pdf";

    @Override
    public String convertStringToWord(String text) {
        String fileName = LocalDateTime.now() + fileNameSuffix;
        System.out.println(fileName);
        return fileName;
    }
}
