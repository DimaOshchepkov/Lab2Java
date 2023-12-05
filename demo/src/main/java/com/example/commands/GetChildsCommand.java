package com.example.commands;

import java.util.Arrays;

import com.example.WorkingDirectory;

public class GetChildsCommand implements ICommand {

    @Override
    public void apply(WorkingDirectory wd) {
        Arrays.stream(wd.getChilds()).forEach(System.out::println);
    }
    
}
