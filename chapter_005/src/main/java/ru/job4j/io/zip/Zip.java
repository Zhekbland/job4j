package ru.job4j.io.zip;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Class Zip creates zip archive without ext elements.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 16.07.2019.
 */
public class Zip {

    /**
     * List for save elements without ext elements.
     */
    private List<File> fileList = new ArrayList<>();

    public List<File> seekBy(String root, String ext) {
        Path path = Paths.get(root);
        try {
            Files.walkFileTree(path, new Visitor(ext));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.fileList;
    }

    /**
     * Method Pack creates Zip archive from sourceList and put zip into target folder.
     *
     * @param sourceList - List of files without ext files.
     * @param target     - folder where be our zip.
     */
    public void pack(List<File> sourceList, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File source : sourceList) {
                if (source.isDirectory()) {
                    System.out.println("Directory added: " + source);
                    continue;
                }
                try (FileInputStream fis = new FileInputStream(source)) {
                    System.out.println("File added: " + source);
                    zip.putNextEntry(new ZipEntry(source.getPath()));
                    byte[] buffer = new byte[fis.available()];
                    int length;
                    while ((length = fis.read(buffer)) > 0) {
                        zip.write(buffer, 0, length);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Inner class Visitor creates Object Visitor which creates tree of folder.
     */
    private class Visitor extends SimpleFileVisitor<Path> {

        private String ext;

        public Visitor(String ext) {
            this.ext = ext;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
            fileList.add(dir.toFile());
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            if (!file.toFile().getName().contains(this.ext)) {
                fileList.add(file.toFile());
            }
            return FileVisitResult.CONTINUE;
        }
    }
}
