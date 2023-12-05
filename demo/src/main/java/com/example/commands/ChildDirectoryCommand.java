package com.example.commands;

import com.example.WorkingDirectory;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ChildDirectoryCommand implements ICommand {

    private String supposeChild;

    @Override
    public void apply(WorkingDirectory wd) {
        if (wd.isChildDirectory(supposeChild)) {
            System.out.println(supposeChild + " содержится в " + wd.getDirectoryName());
        } else {
            System.out.println(supposeChild + " не содержится в " + wd.getDirectoryName());
        }
    }
    
}
