package com.example.commands;

import com.example.WorkingDirectory;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExistCommand implements ICommand {

    private String source;

    @Override
    public void apply(WorkingDirectory wd) {
        if (wd.isExist(source))
            System.out.println("Файл или директория содержится в " + wd.getDirectoryName());
        else
            System.out.println("Файл или директория не содержится в " + wd.getDirectoryName());
    }
    
}
