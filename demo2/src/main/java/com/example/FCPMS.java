package com.example;

import java.util.ArrayList;
import java.util.List;

import com.example.students.Students;
import com.example.speciality.ISpeciality;
import com.example.speciality.PMI;
import com.example.speciality.TeacherMathsInformatics;
import com.example.students.Applicant;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.val;

public class FCPMS {

    private ObjectMapper objectMapper;
    private List<Applicant> applicants;
    private List<ISpeciality> specialities;

    public FCPMS() {
        applicants = new ArrayList<>();
        specialities = List.of(new PMI(), new TeacherMathsInformatics());
        objectMapper = new ObjectMapper();
        try {
            val json = getClass().getClassLoader().getResourceAsStream("applicants.json");
            Students students = objectMapper.readValue(json, Students.class);
            applicants = students.getStudents().stream().map(Applicant.class::cast).toList();
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
