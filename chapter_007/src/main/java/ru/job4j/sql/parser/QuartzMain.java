package ru.job4j.sql.parser;

import org.quartz.*;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.impl.StdSchedulerFactory.getDefaultScheduler;

/**
 * Class QuartzMain create a schedule.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 14.06.2019.
 */
public class QuartzMain {
    public static void main(String[] args) throws SchedulerException {
        Config config = new Config();
        config.init();
        JobDetail job = newJob(QuartsJob.class)
                .withIdentity("JobParser", "group1")
                .build();
        Trigger trigger = newTrigger()
                .withIdentity("CronTrigger", "group1")
                .withSchedule(cronSchedule(config.get("cron.time")))
                .build();
        Scheduler scheduler = getDefaultScheduler();
        scheduler.scheduleJob(job, trigger);
        scheduler.start();
    }
}
