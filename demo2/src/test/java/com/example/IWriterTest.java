package com.example;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import lombok.val;

public class IWriterTest {

    @Test
    public void writePdfTest() {
        val writer = new PdfWriterImpl();
        val message = "Hello world!\nasfsf\nsdflksdj\n";
        val path = "src\\test\\resources\\out\\file" + writer.getExtention();
        writer.write(path, message);

        assertTrue(new File("src\\test\\resources\\out\\file" + writer.getExtention()).exists());
    }

    @Test
    public void writePdfTest2() {
        val writer = new PdfWriterImpl();
        String greeting = "Здравствуйте, уважаемый(ая) %s!\n"
        + "Вы проходите на следующие специальности:\n"
        + "%s\n"
        + "С уважением ФКиФМН.";

        String name = "Иван";
        String specialties = "Информационные технологии";

        String formattedGreeting = String.format(greeting, name, specialties);
        val path = "src\\test\\resources\\out\\file2" + writer.getExtention();
        writer.write(path, formattedGreeting);

        assertTrue(new File("src\\test\\resources\\out\\file2" + writer.getExtention()).exists());
    }

    @Test
    public void writeTxtTest() {
        val writer = new TxtWriterImpl();
        val message = "Hello world!";
        val path = "src\\test\\resources\\out\\file" + writer.getExtention();
        writer.write(path, message);

        assertTrue(new File("src\\test\\resources\\out\\file" + writer.getExtention()).exists());
    }
}
