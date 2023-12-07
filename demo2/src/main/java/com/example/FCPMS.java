package com.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.students.Students;
import com.example.speciality.ISpeciality;
import com.example.speciality.PMI;
import com.example.speciality.TeacherMathsInformatics;
import com.example.students.Applicant;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.val;

public class FCPMS {

    private ObjectMapper objectMapper;
    private List<Applicant> applicants = new ArrayList<>();
    private List<ISpeciality> specialities;

    public FCPMS() {
        specialities = List.of(new PMI(), new TeacherMathsInformatics());
        objectMapper = new ObjectMapper();
        try {
            val json = getClass().getClassLoader().getResourceAsStream("applicants.json");
            Students students = objectMapper.readValue(new File("src/main/resources/applicants.json"), Students.class);
            applicants = students.getStudents().stream().map(stud -> Applicant.builder()
                    .name(stud.getName())
                    .id(stud.getId())
                    .faculty(this)
                    .subjects(stud.getSubject())
                    .build()).collect(Collectors.toList());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void addApplicant(Applicant applicant) {
        applicants.add(applicant);
    }

    public void removeApplicant(Applicant applicant) {
        applicants.remove(applicant);
    }

    private String defaultOutPath = "src\\test\\resources\\out";
    public void notifyApplicantAboutAdmission() {
        for (val applicant : applicants) {
            applicant.getLetter(specialities, defaultOutPath);
        }
    }

}
