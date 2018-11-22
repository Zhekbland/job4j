package ru.job4j.sort;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Class SortTest is testing sort of department.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 22.11.2018.
 */
public class SortTest {
    @Test
    public void whenSortDirectOrder() {
        Sort sort = new Sort();
        String[] inputData = {"K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2", "K2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"};
        String[] expect = {"K1", "K1\\SK1", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2",
                "K1\\SK2", "K2", "K2\\SK1", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"};
        String[] result = sort.directSort(inputData);
        assertThat(result, is(expect));
    }

    @Test
    public void whenSortReverseOrder() {
        Sort sort = new Sort();
        String[] inputData = {"K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2", "K2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"};
        String[] expect = {"K2",
                "K2\\SK1", "K2\\SK1\\SSK2", "K2\\SK1\\SSK1", "K1",
                "K1\\SK2", "K1\\SK1", "K1\\SK1\\SSK2", "K1\\SK1\\SSK1"};
        String[] result = sort.reverseSort(inputData);
        assertThat(result, is(expect));
    }

    @Test
    public void whenRecoverDepartments() {
        Sort sort = new Sort();
        String[] inputData = {"K1\\SK2", "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"};
        String[] expect = {"K1", "K1\\SK1", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2",
                "K1\\SK2", "K2", "K2\\SK1", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"};
        String[] result = sort.recoveryDepartments(inputData);
        assertThat(result, is(expect));
    }
}
