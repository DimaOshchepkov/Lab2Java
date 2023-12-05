package com.example.commands;

import com.example.WorkingDirectory;

public class GoToParentDirectory implements ICommand {

    @Override
    public void apply(WorkingDirectory wd) {
        wd.goToParentDirectory();
        System.out.println(wd.getDirectoryName());
    }
    
}
