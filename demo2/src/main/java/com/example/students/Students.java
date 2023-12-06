package com.example.students;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@JsonRootName("students")
@NoArgsConstructor
public
class Students {
    @JsonProperty("student")
    private List<Student> students;
}
