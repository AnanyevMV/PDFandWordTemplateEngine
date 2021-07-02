package com.ananyevmv.service;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

@Service
public class TextToPDFServiceImpl implements TextToPDFService {
    private static final String fileNameSuffix = "-output.pdf";
    private static final String fileNamePrefix = "generated/";
    private static final int FONT_SIZE = 14;

    @Override
    public String convertStringToPDF(String text) {
        try {
            Document document = new Document();
            // Метод createDirectories, в отличие от createDirectory, не бросает исключение, если директория уже существует
            Files.createDirectories(Path.of(fileNamePrefix));
            String fileName = fileNamePrefix + LocalDateTime.now() + fileNameSuffix;
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();
            Font font = FontFactory.getFont("arial.ttf", BaseFont.IDENTITY_H, FONT_SIZE);
            Chunk chunk = new Chunk(text, font);
            document.add(chunk);
            document.close();
            return fileName;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
