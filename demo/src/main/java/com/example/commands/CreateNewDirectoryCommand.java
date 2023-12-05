package com.example.commands;

import com.example.WorkingDirectory;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateNewDirectoryCommand implements ICommand {

    private String dir;

    @Override
    public void apply(WorkingDirectory wd) {
        if (wd.createNewDirectory(dir)) {
            System.out.println("Директория создана успешно");
        }else {
            System.out.println("Не удалось создать директорию");
        }
    }
}
