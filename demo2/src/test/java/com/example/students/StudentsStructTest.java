package com.example.students;

import com.example.FCPMS;
import org.junit.Test;

import java.util.List;

public class StudentsStructTest {

    @Test
    void testStruct() {
        Applicant applicant = Applicant.builder()
                .name("Шалагинова Надежда Владимировна")
                .id(0)
                .subjects(List.of(
                        Subject.of("Информатика", 100),
                        Subject.of("Математика", 80),
                        Subject.of("Русский язык", 60)))
                .faculty(new FCPMS())
                .build();
    }
}
