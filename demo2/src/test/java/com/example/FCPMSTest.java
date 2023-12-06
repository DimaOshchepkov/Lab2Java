package com.example;

import java.util.List;

import org.junit.Test;

import com.example.students.Applicant;
import com.example.students.Subject;

import lombok.val;

public class FCPMSTest {

    

    @Test
    public void testNotifyApplicantAboutAdmission() {
        val faculty = new FCPMS();
        Applicant applicant1 = Applicant.builder()
                .name("Шалагинова Надежда Владимировна")
                .id(0)
                .subjects(List.of(
                        Subject.of("Информатика", 100),
                        Subject.of("Математика", 80),
                        Subject.of("Русский язык", 60)))
                .faculty(faculty)
                .build();

        Applicant applicant2 = Applicant.builder()
                .name("Шалагинова Надежда Владимировна")
                .id(1)
                .subjects(List.of(
                        Subject.of("Информатика", 20),
                        Subject.of("Математика", 80),
                        Subject.of("Русский язык", 60),
                        Subject.of("Физика", 60)))
                .faculty(faculty)
                .build();

        Applicant applicant3 = Applicant.builder()
                .name("Одинцов Алексей Александрович")
                .id(2)
                .subjects(List.of(
                        Subject.of("Информатика", 20),
                        Subject.of("Математика", 80),
                        Subject.of("Русский язык", 60),
                        Subject.of("Физика", 60)))
                .faculty(faculty)
                .build();
        
        faculty.notifyApplicantAboutAdmission();
    }
}
