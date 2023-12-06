package com.example.speciality;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.students.Applicant;
import com.example.students.Subject;


public class TeacherMathsInformatics implements ISpeciality {

    private Set<Set<String>> subjects = Set.of(
            Set.of("Математика"),
            Set.of("Русский язык"),
            Set.of("Информатика"));

    private Map<String, Integer> requiredGrades = Map.of(
            "Математика", 30,
            "Русский язык", 40,
            "Информатика", 35,
            "Физика", 36);

    @Override
    public boolean isPass(Applicant applicant) {
        Set<String> applicantSubjectSet = applicant.getSubjects().stream()
                .map(Subject::getName)
                .collect(Collectors.toSet());

        Map<String, Integer> applicantGradesMap = applicant.getSubjects().stream()
                .collect(Collectors.toMap(Subject::getName, Subject::getGrade));

        return subjects.stream()
                .allMatch(specialitySubjSet -> specialitySubjSet.stream()
                        .anyMatch(subj -> applicantSubjectSet.contains(subj)
                                && requiredGrades.get(subj) <= applicantGradesMap.get(subj)));
    }

    @Override
    public String toString() {
        return "Учитель математики и информатики";
    }

}
