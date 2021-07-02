package com.ananyevmv.service;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

@Service
public class TextToWordServiceImpl implements TextToWordService {

    private static final String fileNameSuffix = "-output.docx";
    private static final String fileNamePrefix = "generated/";
    private static final int FONT_SIZE = 14;

    @Override
    public String convertStringToWord(String text) {
        try {
            // Метод createDirectories, в отличие от createDirectory, не бросает исключение, если директория уже существует
            Files.createDirectories(Path.of(fileNamePrefix));
            String fileName = fileNamePrefix + LocalDateTime.now() + fileNameSuffix;

            XWPFDocument document = new XWPFDocument();

            XWPFParagraph xwpfParagraph = document.createParagraph();
            xwpfParagraph.setAlignment(ParagraphAlignment.CENTER);

            XWPFRun paragraphRun = xwpfParagraph.createRun();
            paragraphRun.setFontSize(FONT_SIZE);
            paragraphRun.setFontFamily("Arial");
            paragraphRun.setText(text);


            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            document.write(fileOutputStream);
            fileOutputStream.close();
            document.close();

            return fileName;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
