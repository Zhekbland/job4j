package ru.job4j.io.scan;

import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SearchTest {

    @After
    public void deleteStructure() {
        CreateStructures structures = new CreateStructures();
        structures.recursiveDelete(new File(structures.getParentFolder()));
    }

    @Ignore
    public void whenWeSearchFileWithExtensionsTXTAndPDF() {
        CreateStructures createStructures = new CreateStructures();
        List<File> expected = createStructures.create();
        List<String> extensions = Arrays.asList(".txt", ".pdf");
        Search search = new Search();
        List<File> result = search.files(createStructures.getParentFolder(), extensions);
        assertThat(result, is(expected));
    }

    @Test
    public void whenWeSearchFileWithExtensionsTXTAndPDFforTravisCI() {
        CreateStructures createStructures = new CreateStructures();
        List<File> expected = createStructures.create();
        List<String> extensions = Arrays.asList(".txt", ".pdf");
        Search search = new Search();
        List<File> result = search.files(createStructures.getParentFolder(), extensions);
        assertThat(result.size(), is(expected.size()));
    }
}