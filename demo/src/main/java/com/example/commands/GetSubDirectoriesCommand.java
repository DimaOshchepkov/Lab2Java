package com.example.commands;

import java.util.Arrays;

import com.example.WorkingDirectory;

public class GetSubDirectoriesCommand implements ICommand {

    @Override
    public void apply(WorkingDirectory wd) {
        Arrays.stream(wd.getSubDirectories())
            .forEach(System.out::println);
    }
    
}
