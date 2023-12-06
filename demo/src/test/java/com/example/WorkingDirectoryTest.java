package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WorkingDirectoryTest {

    protected final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    protected final PrintStream standardOut = System.out;
    protected WorkingDirectory wd = WorkingDirectory
            .getInstance("src\\test\\resources\\testFolder");

    private void createTestFolder() {
        File testFolder = new File("src/test/resources/testFolder");

        if (!testFolder.exists()) {
            testFolder.mkdirs();

        }

        createFileIfNotExists("src/test/resources/testFolder/file1.txt");
        createFileIfNotExists("src/test/resources/testFolder/file2.bmp");
        createFileIfNotExists("src/test/resources/testFolder/file3.docx");

        File innerFolder = new File("src/test/resources/testFolder/InnerFolder");
        if (!innerFolder.exists()) {
            innerFolder.mkdirs();
        }

        createFileIfNotExists("src/test/resources/testFolder/InnerFolder/file4.xlsx");

        File innerFolder2 = new File("src/test/resources/testFolder/InnerFolder/InnerFolder2");
        if (!innerFolder2.exists()) {
            innerFolder2.mkdirs();
        }

        createFileIfNotExists("src/test/resources/testFolder/InnerFolder/InnerFolder2/file5.txt");
        createFileIfNotExists("src/test/resources/testFolder/InnerFolder/InnerFolder2/file6.txt");
    }

    private void createFileIfNotExists(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @BeforeEach
    public void setUp() {
        createTestFolder();
        wd = WorkingDirectory.getInstance("src\\test\\resources\\testFolder");
    }

    @AfterEach
    public void tearDown() {
        createTestFolder();
        wd = WorkingDirectory.getInstance("src\\test\\resources\\testFolder");
    }

    @Test
    void testCreateNewDirectory() {

    }

    @Test
    void testDeleteSubDirectories() throws IOException {
        wd.deleteSubDirectories();
        assertTrue(Arrays.stream((new File(wd.getDirectoryName())).listFiles())
                .allMatch(file -> !file.equals("InnerFolder")));

        assertTrue(Arrays.stream((new File(wd.getDirectoryName())).listFiles())
                .anyMatch(file -> !file.equals("file1.txt")));
    }

    @Test
    void testFind() {
        assertEquals(1, wd.find(".txt").length);
        assertEquals(0, wd.find(".img").length);
        assertEquals(1, wd.find(".bmp").length);
    }

    @Test
    void testGetChilds() {
        assertEquals(4, wd.getChilds().length);
    }

    @Test
    void testGetDirectoryName() {
        assertTrue(wd.getDirectoryName().endsWith("testFolder"));
    }

    @Test
    void testGetInstance() {

    }

    @Test
    void testGetInstance2() {

    }

    @Test
    void testGetParentName() {
        assertTrue(wd.getParentName().endsWith("resources"));
    }

    @Test
    void testGetSubDirectories() {
        assertEquals(2, wd.getSubDirectories().length);
    }

    @Test
    void testGoToChildExistChild() {
        wd.goToChild("InnerFolder");
        assertTrue(wd.getDirectoryName().endsWith("InnerFolder"));
    }

    @Test
    void testGoToParentDirectory() {
        wd.goToParentDirectory();
        assertTrue(wd.getDirectoryName().endsWith("resources"));
    }

    @Test
    void testIsChildDirectory() {
        assertTrue(wd.isChildDirectory("InnerFolder"));
        assertTrue(!wd.isChildDirectory("Inner"));
    }

    @Test
    void testIsExist() {
        assertTrue(wd.isExist("file6.txt"));
        assertTrue(wd.isExist("InnerFolder2"));
        assertTrue(!wd.isExist("file7.txt"));
    }
}
