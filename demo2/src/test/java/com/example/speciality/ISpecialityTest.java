package com.example.speciality;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.example.FCPMS;

import com.example.students.Applicant;
import com.example.students.Subject;

public class ISpecialityTest {

    @Test
    public void testIsPass() {
        Applicant applicant = Applicant.builder()
                .name("Шалагинова Надежда Владимировна")
                .id(0)
                .subjects(List.of(
                        Subject.of("Информатика", 100),
                        Subject.of("Математика", 80),
                        Subject.of("Русский язык", 60)))
                .faculty(new FCPMS())
                .build();
        
        assertTrue((new TeacherMathsInformatics()).isPass(applicant));
    }

    @Test
    public void testIsNotPass() {
        Applicant applicant = Applicant.builder()
                .name("Шалагинова Надежда Владимировна")
                .id(0)
                .subjects(List.of(
                        Subject.of("Информатика", 20),
                        Subject.of("Математика", 100),
                        Subject.of("Русский язык", 100)))
                .faculty(new FCPMS())
                .build();
        
        assertTrue(!(new TeacherMathsInformatics()).isPass(applicant));
    }

    @Test
    public void testIsNotPass2() {
        Applicant applicant = Applicant.builder()
                .name("Шалагинова Надежда Владимировна")
                .id(0)
                .subjects(List.of(
                        Subject.of("Информатика", 100),
                        Subject.of("Математика", 100),
                        Subject.of("Английский язык", 100)))
                .faculty(new FCPMS())
                .build();
        
        assertTrue(!(new TeacherMathsInformatics()).isPass(applicant));
    }

    @Test
    public void testIsPass2() {
        Applicant applicant = Applicant.builder()
                .name("Шалагинова Надежда Владимировна")
                .id(0)
                .subjects(List.of(
                        Subject.of("Информатика", 20),
                        Subject.of("Математика", 100),
                        Subject.of("Английский язык", 100),
                        Subject.of("Русский язык", 100),
                        Subject.of("Физика", 100)))
                .faculty(new FCPMS())
                .build();
        
        assertTrue(!(new TeacherMathsInformatics()).isPass(applicant));
        assertTrue((new PMI()).isPass(applicant));
    }
}
