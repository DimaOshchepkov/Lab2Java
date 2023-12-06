package com.example.students;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Subject implements Comparable<Subject>{
    private String name;
    private int grade;

    public Subject(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }

    @JsonIgnore
    public static Subject of(String name, int grade) {
        return new Subject(name, grade);
    }

    @JsonIgnore
    @Override
    public int compareTo(Subject other) {
        if (this.name.equals(other.name))
            return Integer.compare(this.grade, other.grade);
        else
            return -1;
    }


}
