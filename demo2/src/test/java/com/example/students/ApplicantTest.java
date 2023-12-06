package com.example.students;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.example.FCPMS;
import com.example.speciality.PMI;
import com.example.speciality.TeacherMathsInformatics;


public class ApplicantTest {

    @Test
    public void testGetLetter() {
        Applicant applicant = Applicant.builder()
                .name("Шалагинова Надежда Владимировна")
                .id(0)
                .subjects(List.of(
                        Subject.of("Информатика", 100),
                        Subject.of("Математика", 80),
                        Subject.of("Русский язык", 60)))
                .faculty(new FCPMS())
                .build();
        
        applicant.getLetter(List.of(new PMI(), new TeacherMathsInformatics()),
            "src\\test\\resources\\out");
        assertTrue((new File("src\\test\\resources\\out\\Шалагинова Надежда Владимировна_0.txt")).exists());
    }
}
