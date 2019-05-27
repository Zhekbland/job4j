package ru.job4j.patterns.singleton;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Testing TrackerSingleEnum, TrackerSingleLazyLoading,
 * TrackerSingleEagerLoading, TrackerSingleLazyLoadingInner.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 27.05.2019.
 */
public class TrackerSingle {

    @Test
    public void whenTrackerSingleEnumHasOnlyOneInstance() {
        boolean result = (TrackerSingleEnum.INSTANCE.equals(TrackerSingleEnum.INSTANCE));
        assertThat(result, is(true));
    }

    @Test
    public void whenTrackerSingleTwoHasOnlyOneInstance() {
        boolean result = (TrackerSingleLazyLoading.getInstance().equals(TrackerSingleLazyLoading.getInstance()));
        assertThat(result, is(true));
    }

    @Test
    public void whenTrackerSingleEagerLoadingHasOnlyOneInstance() {
        boolean result = (TrackerSingleEagerLoading.getInstance().equals(TrackerSingleEagerLoading.getInstance()));
        assertThat(result, is(true));
    }

    @Test
    public void whenTrackerSingleLazyLoadingFinalHasOnlyOneInstance() {
        boolean result = (TrackerSingleLazyLoadingInner.getInstance().equals(
                TrackerSingleLazyLoadingInner.getInstance()));
        assertThat(result, is(true));
    }

    @Test
    public void amountOfTrackerSingleLazyLoading() {
        TrackerSingleLazyLoading tracker1 = TrackerSingleLazyLoading.getInstance();
        TrackerSingleLazyLoading tracker2 = TrackerSingleLazyLoading.getInstance();
        int result = TrackerSingleLazyLoading.getAmountOfInstance();
        assertThat(result, is(1));
    }

    @Test
    public void amountOfTrackerSingleEagerLoading() {
        TrackerSingleEagerLoading tracker1 = TrackerSingleEagerLoading.getInstance();
        TrackerSingleEagerLoading tracker2 = TrackerSingleEagerLoading.getInstance();
        int result = TrackerSingleEagerLoading.getAmountOfInstance();
        assertThat(result, is(1));
    }

    @Test
    public void amountOfTrackerSingleLazyLoadingFinal() {
        TrackerSingleLazyLoadingInner tracker1 = TrackerSingleLazyLoadingInner.getInstance();
        TrackerSingleLazyLoadingInner tracker2 = TrackerSingleLazyLoadingInner.getInstance();
        int result = TrackerSingleLazyLoadingInner.getAmountOfInstance();
        assertThat(result, is(1));
    }
}