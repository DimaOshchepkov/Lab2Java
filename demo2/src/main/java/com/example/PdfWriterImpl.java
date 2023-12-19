package com.example;


import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;


public class PdfWriterImpl implements IWriter {

    @Override
    public void write(String filePath, String message) {

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filePath));

            document.open();
            document.add(new Paragraph(message));

            document.close();
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getExtention() {
        return ".pdf";
    }

}
