package com.example.students;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    protected int id;
    protected String name;
    protected List<Subject> subjects;
}
