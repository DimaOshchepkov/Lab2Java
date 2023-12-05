package com.example.commands;

import com.example.WorkingDirectory;

public class GetParantName implements ICommand {

    @Override
    public void apply(WorkingDirectory wd) {
        System.out.println(wd.getParentName());   
    }
    
}
