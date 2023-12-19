package com.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TxtWriterImpl implements IWriter {

    @Override
    public void write(String filePath, String message) {
        
        try {
            FileWriter writer = new FileWriter(filePath);
            BufferedWriter bufferWriter = new BufferedWriter(writer);
            bufferWriter.write(message);

            bufferWriter.close();

        } catch (IOException e) {
            System.out.println("Ошибка при создании файла: " + e.getMessage());
        }
    }

    @Override
    public String getExtention() {
        return ".txt";
    }
}
