package ru.job4j.io.zip;


import org.junit.Test;

import java.io.*;

/**
 * Class ZipTest is testing class Zip (creating zip archive).
 */
public class ZipTest {

    @Test
    public void test2() {
        Zip zip = new Zip();
        File file = new File("temp.zip");
        File fileSource = new File("temp");
        String ext = "xml";
        zip.pack(zip.seekBy(fileSource.toString(), ext), file);
    }
}