package ru.job4j.io.write;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class AnalysisTest is testing class Analysis.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 20.06.2019.
 */
public class AnalysisTest {
    @Test
    public void test() {
        Analysis analysis = new Analysis();
        analysis.unavailable("server.txt", "unavailable.csv");
        List<String> list = Arrays.asList("10:57:01;10:59:01", "11:01:02;11:02:02", "12:00:24;13:00:00");
        boolean result = list.equals(analysis.getListOfTarget("unavailable.csv"));
        assertThat(result, is(true));
    }
}