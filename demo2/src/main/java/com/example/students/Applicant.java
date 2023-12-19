package com.example.students;

import java.util.List;

import com.example.FCPMS;
import com.example.IWriter;
import com.example.PdfWriterImpl;
import com.example.TxtWriterImpl;
import com.example.speciality.ISpeciality;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.val;

@Builder
@Getter
public class Applicant {

    protected int id;
    protected String name;
    protected List<Subject> subjects;
    protected FCPMS faculty;

    @Setter
    @Builder.Default
    protected IWriter writer = new PdfWriterImpl();

    public Applicant(int id, String name, List<Subject> subjects, FCPMS faculty) {
        this(id, name, subjects, faculty, new TxtWriterImpl());
    }

    public Applicant(int id, String name, List<Subject> subjects, FCPMS faculty, IWriter writer) {
        this.id = id;
        this.name = name;
        this.subjects = subjects;
        this.faculty = faculty;
        faculty.addApplicant(this);
        this.writer = writer;
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
        filePath += "\\" + name + "_" + Integer.toString(id) + writer.getExtention();
        writer.write(filePath, message);
    }

}
