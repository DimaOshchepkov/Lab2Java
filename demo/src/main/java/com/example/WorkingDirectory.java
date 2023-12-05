package com.example;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.val;

public class WorkingDirectory {

    private static WorkingDirectory workingDirectory;
    private String directoryName;

    private WorkingDirectory(String path) {
        directoryName = path;
    }

    public static WorkingDirectory getInstance(String path) {
        if (workingDirectory == null) {
            File dir = new File(path);
            if (dir.exists() && dir.isDirectory())
                workingDirectory = new WorkingDirectory(path);
            else
                throw new IllegalArgumentException("Путь должен являться каталогом");
        }
        return workingDirectory;
    }

    public static WorkingDirectory getInstance() {
        if (workingDirectory == null) {
            throw new IllegalStateException(
                    "Рабочая директория не инициализирована\n Воспользуйтесь getInstance(String path)");
        }
        return workingDirectory;
    }

    public String[] getChilds() {
        return Arrays.stream((new File(directoryName)).listFiles())
                .map(File::getAbsolutePath)
                .toArray(String[]::new);

    }

    public String getDirectoryName() {
        return directoryName;
    }

    public String getParentName() {
        return (new File(directoryName)).getParent();
    }

    public void goToParentDirectory() {
        String parent = (new File(directoryName)).getParent();
        if (parent == null) {
            throw new IllegalStateException("Нет родительского каталога");
        }
        directoryName = parent;
    }

    public boolean isChildDirectory(String child) {
        return Arrays.asList(
                (new File(directoryName))
                        .listFiles())
                .contains(new File(directoryName + "\\" + child));
    }

    public boolean createNewDirectory(String dirName) {
        File newDirectory = new File(directoryName, dirName);
        if (!newDirectory.exists()) {
            newDirectory.mkdir();
            return true;
        }
        return false;
    }

    public void goToChild(String child) {
        File dir = new File(directoryName);
        if (Arrays.stream(dir.listFiles(File::isDirectory))
                .anyMatch(file -> file.getName().equals(child))) {
            directoryName = directoryName + "\\" + child;
        } else {
            throw new IllegalArgumentException("Нет подкаталога " + child + " в " + directoryName);
        }
    }

    private boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }

    public void deleteSubDirectories() {
        for (File file : (new File(directoryName)).listFiles(File::isDirectory)) {
            deleteDirectory(file);
        }
    }

    public String[] find(String extension) {
        return Arrays.stream(new File(directoryName).listFiles(file -> file.getName().endsWith(extension)))
                .map(File::getName)
                .toArray(String[]::new);
    }

    private void getSubDirectories(File dir, List<String> list) {
        for (File file : dir.listFiles(File::isDirectory)) {
            list.add(file.getAbsolutePath());
            getSubDirectories(file, list);
        }
    }

    public String[] getSubDirectories() {
        val response = new ArrayList<String>();
        getSubDirectories(new File(directoryName), response);
        return response.toArray(String[]::new);
    }

    private boolean isExist(File searchDir, String sourceName) {
        if (searchDir.getName().equals(sourceName))
            return true;
        if (searchDir.isDirectory()) {
            File[] files = searchDir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        if (isExist(file, sourceName)) {
                            return true;
                        }
                    } else if (file.getName().equals(sourceName)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isExist(String sourceName) {
        return isExist(new File(directoryName), sourceName);
    }

}
