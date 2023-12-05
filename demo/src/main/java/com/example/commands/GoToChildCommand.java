package com.example.commands;

import com.example.WorkingDirectory;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GoToChildCommand implements ICommand {

    private String child;
    @Override
    public void apply(WorkingDirectory wd) {
        wd.goToChild(child);
        System.out.println("Текущая директория: " + wd.getDirectoryName());
    }
    
}
