package com.example.commands;

import com.example.WorkingDirectory;

public class DeleteSubDirectoriesCommand implements ICommand {

    @Override
    public void apply(WorkingDirectory wd) {
        wd.deleteSubDirectories();
    }
    
}
