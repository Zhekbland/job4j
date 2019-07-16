package ru.job4j.io.scan;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Class CreateStructures creates structure of folders and files.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 03.07.2019.
 */
public class CreateStructures {

    /**
     * Amount of folders into the current folder
     */
    private static final int INNER_FOLDER_AMOUNT = 2;

    /**
     * Counter of folders
     */
    private int count = 1;

    /**
     * root folder
     */
    private String parentFolder;

    /**
     * List of expected files extensions.
     */
    private final List<File> expected = new ArrayList<>();

    /**
     * Constructor set parent's folder from two paths
     */
    public CreateStructures() {
        setParentFolder();
    }

    /**
     * Create structure of folders and files.
     *
     * @return List of files with specific extensions.
     */
    public List<File> create() {
        Path parentPath = Paths.get(System.getProperty("java.io.tmpdir"));
        Path filesPath = parentPath.resolve("rootFolder");
        for (int i = 1; i < 5; i++) {
            if (Files.notExists(filesPath)) {
                fillDirectoryWithFiles(filesPath);
            }
            fillWithAnotherFolder(filesPath);
            filesPath = filesPath.resolve("folder" + i);
        }
        return this.expected;
    }

    /**
     * Fills current folder with files
     *
     * @param filesPath path of current folder
     */
    private void fillDirectoryWithFiles(Path filesPath) {
        try {
            Files.createDirectory(filesPath);
            for (int j = 1; j < 5 + 1; j++) {
                File file = new File(filesPath.toString(), "file" + j
                        + (j % 2 == 0 ? ".txt" : j > 3 ? ".pdf" : ".xml"));
                file.createNewFile();
                if (file.getName().contains(".txt") || file.getName().contains(".pdf")) {
                    expected.add(file);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Fills the current folder with folders and files
     *
     * @param folderPath current folder for filling with folders.
     */
    private void fillWithAnotherFolder(Path folderPath) {
        while (count <= INNER_FOLDER_AMOUNT) {
            fillDirectoryWithFiles(folderPath.resolve("folder" + count++));
        }
        count = 1;
    }

    /**
     * Delete structure of folders and files.
     *
     * @param file parent folder, root of structure.
     */
    public void recursiveDelete(File file) {
        if (!file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            for (File f : Objects.requireNonNull(file.listFiles())) {
                recursiveDelete(f);
            }
        }
        file.delete();
    }

    public String getParentFolder() {
        return this.parentFolder;
    }

    /**
     * Create String path from two path.
     */
    private void setParentFolder() {
        Path parentPath = Paths.get(System.getProperty("java.io.tmpdir"));
        Path filesPath = parentPath.resolve("rootFolder");
        this.parentFolder = filesPath.toAbsolutePath().toString();
    }
}
