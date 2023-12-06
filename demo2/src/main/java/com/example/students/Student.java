package com.example.students;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonRootName("student")
public class Student {
    protected int id;
    protected String name;
    protected List<Subject> subjects;
}
