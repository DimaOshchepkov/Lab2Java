package com.example.students;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.example.FCPMS;
import com.example.speciality.ISpeciality;

import lombok.Builder;
import lombok.Getter;
import lombok.val;

@Builder
@Getter
public class Applicant {

    protected int id;
    protected String name;
    protected List<Subject> subjects;
    protected FCPMS faculty;

    public Applicant(int id, String name, List<Subject> subjects, FCPMS faculty) {
        this.id = id;
        this.name = name;
        this.subjects = subjects;
        this.faculty = faculty;
        faculty.addApplicant(this);
    }

    public void getLetter(List<ISpeciality> specialitys, String filePath) {
        String textBlock = """
                Здравствуйте, уважаемый(ая) %s!
                Вы проходите на следующие спецальности:
                %s
                С уважением ФКиФМН.
                """;

        String textBlockNotPass = """
                Здравствуйте, уважаемый(ая) %s!
                К сожалению, вы не проходите ни на одну спецальность.
                С уважением ФКиФМН.
                """;
        String specialitysStr = "";

        boolean isPass = false;
        for (val speciality : specialitys) {
            if (speciality.isPass(this)) {
                specialitysStr += speciality.toString() + "\n";
                isPass = true;
            }
        }
        String message;
        if (!isPass) {
            message = String.format(textBlockNotPass, name);
        }
        else {
            message = String.format(textBlock, name, specialitysStr);
        }
        filePath += "\\" + name + "_" + Integer.toString(id) + ".txt";
        try {
            FileWriter writer = new FileWriter(filePath);
            BufferedWriter bufferWriter = new BufferedWriter(writer);
            bufferWriter.write(message);
            bufferWriter.close();

            System.out.println("Файл успешно создан и записан.");
        } catch (IOException e) {
            System.out.println("Ошибка при создании файла: " + e.getMessage());
        }
    }

}
