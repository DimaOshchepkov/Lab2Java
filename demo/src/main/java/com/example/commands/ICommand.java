package com.example.commands;

import com.example.WorkingDirectory;

@FunctionalInterface
public interface ICommand {
    void apply(WorkingDirectory wd);
}
