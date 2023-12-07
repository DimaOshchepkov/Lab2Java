package com.example;


import static org.junit.Assert.assertTrue;

import com.example.students.Applicant;
import com.example.students.Student;
import com.example.students.Students;
import com.example.students.Subject;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.val;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() throws IOException {
        val stud = Student.builder()
                .name("Шалагинова Надежда Владимировна")
                .id(0)
                .subject(List.of(
                        Subject.of("Информатика", 20),
                        Subject.of("Математика", 100),
                        Subject.of("Русский язык", 100)))
                .build();

        Students students = new Students(List.of(stud));
        ObjectMapper objMapper = new ObjectMapper();
        objMapper.writeValue(new File("src/test/resources/out/test.json"), students);
    }
}
