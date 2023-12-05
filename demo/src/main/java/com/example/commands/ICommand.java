package com.example.commands;

import com.example.WorkingDirectory;

public interface ICommand {
    void apply(WorkingDirectory wd);
}
