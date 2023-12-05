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
            workingDirectory = new WorkingDirectory(path);
        }
        return workingDirectory;
    }

    public static WorkingDirectory getInstance() {
        if (workingDirectory == null) {
            throw new IllegalStateException("Рабочая директория не инициализирована\n Воспользуйтесь getInstance(String path)");
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
            throw new IllegalStateException("Нет родительского каталогоа");
        }
        directoryName = parent;
    }

    public boolean isChildDirectory(String child) {
        return Arrays.asList(
                (new File(directoryName))
                        .listFiles())
                .contains(new File(child));
    }

    public void createNewDirectory(String dirName) {
        File newDirectory = new File(directoryName, dirName);
        if (!newDirectory.exists()) {
            newDirectory.mkdir();
        }
    }

    public void goToChild(String child) {
        File chFile = new File(child);
        File dir = new File(directoryName);
        if (Arrays.stream(dir.listFiles()).anyMatch(file -> file.equals(chFile))) {
            directoryName = chFile.getAbsolutePath();
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
        for (File file : (new File(directoryName)).listFiles()) {
            deleteDirectory(file);
        }
    }

    public String[] find(String extention) {
        return Arrays.stream(new File(directoryName)
                .listFiles(file -> file.getName()
                        .endsWith(extention)))
                .toArray(String[]::new);
    }

    private void getSubDirectories(File dir, List<String> list) {
        for (File file : dir.listFiles(File::isDirectory)) {
            list.add(file.getAbsolutePath());
            getSubDirectories(file, list);
        }
    }

    public String[] getSubDirectories() {
        val responce = new ArrayList<String>();
        getSubDirectories(new File(directoryName), responce);
        return responce.toArray(String[]::new);
    }

    private boolean isExist(File searchDir, File disiredDir) {
        File[] listChild = searchDir.listFiles(File::isDirectory);
        for (File child : listChild) {
            if (child.equals(disiredDir)) {
                return true;
            }      
        }
        return false;
    }

    public boolean isExist(String sourceName) {
        return isExist(new File(directoryName), (new File(sourceName)));
    }

}
