package ru.job4j.sql.parser;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class DataCreatorTest tests classes Parser, DataCreator.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 14.06.2019.
 */
public class DataCreatorTest {

    @Test
    public void createDatabase() {
        DataCreator dataCreator = new DataCreator();
        dataCreator.getConnection();
        assertThat(dataCreator.checkTableExist(), is(false));
    }

    @Test
    public void parseVacancionsFromSqlRu() {
        Parser parser = new Parser();
        DataCreator dataCreator = new DataCreator();
        dataCreator.getConnection();
        dataCreator.fillDB(dataCreator.checkTableExist() ? parser.updateList(dataCreator.getListFromDB())
                : parser.fillList());
    }
}