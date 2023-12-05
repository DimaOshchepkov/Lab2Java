package com.example.commands;

import com.example.WorkingDirectory;

public class CommandsInvoker {
    ICommand command;

    WorkingDirectory wd = WorkingDirectory.getInstance();

    CommandsInvoker(ICommand command) {
        this.command = command;
    }

    public void setCommand(ICommand command) {
        this.command = command;
    }

    public void apply() {
        command.apply(wd);
    }
}
