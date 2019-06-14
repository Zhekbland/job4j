package ru.job4j.sql.parser;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

/**
 * Class QuartsJob execute main job of this application.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 14.06.2019.
 */
public class QuartsJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        Parser parser = new Parser();
        DataCreator dataCreator = new DataCreator();
        dataCreator.getConnection();
        dataCreator.fillDB(dataCreator.checkTableExist() ? parser.updateList(dataCreator.getListFromDB())
                : parser.fillList());
        System.out.println("Done");
    }
}
