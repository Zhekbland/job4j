package ru.job4j.io.scan;

import java.io.File;
import java.util.*;

/**
 * Class Search searches file with specific extensions.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 03.07.2019.
 */
public class Search {

    /**
     * Search and return files with specific extensions.
     *
     * @param parent folder of structures, root folder
     * @param exst   which method searches
     * @return list of files with specific extensions
     */
    public List<File> files(String parent, List<String> exst) {
        File rootFolder = new File(parent);
        List<File> result = new ArrayList<>();
        Queue<File> queueOfFiles = new LinkedList<>(Collections.singleton(rootFolder));
        while (!queueOfFiles.isEmpty()) {
            for (File currentFile : queueOfFiles.poll().listFiles()) {
                if (currentFile.isDirectory()) {
                    queueOfFiles.add(currentFile);
                } else if (currentFile.isFile()) {
                    fileSearching(currentFile, exst, result);
                }
            }
        }
        return result;
    }

    /**
     * Searches files with specific extensions
     *
     * @param file   current file
     * @param exst   required extensions
     * @param result list of required extensions
     */
    private void fileSearching(File file, List<String> exst, List<File> result) {
        for (String ext : exst) {
            if (file.getName().contains(ext)) {
                result.add(file);
            }
        }
    }
}
