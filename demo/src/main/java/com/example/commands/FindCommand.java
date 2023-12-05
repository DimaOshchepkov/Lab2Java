package com.example.commands;

import java.util.Arrays;

import com.example.WorkingDirectory;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindCommand implements ICommand {

    private String extension;

    @Override
    public void apply(WorkingDirectory wd) {
        Arrays.stream(wd.find(extension))
            .forEach(System.out::println);
    }
}
