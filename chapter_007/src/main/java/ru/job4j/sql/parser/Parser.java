package ru.job4j.sql.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class Parser parse, get information of vacancies from sql.ru.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 14.06.2019.
 */
public class Parser {

    /**
     * Last (end) page on the forum.
     */
    private final Integer endPage;

    /**
     * Last page on the forum with actual information in this year.
     */
    private final Integer lastActualPage;
    private static final String URL = "https://www.sql.ru/forum/job-offers/";
    private static final Integer FIRST_PAGE = 1;
    private final List<Vacancy> jobs = new ArrayList<>();

    /**
     * Constructor get end page and last actual page of the forum.
     */
    public Parser() {
        this.endPage = amountOfPages(getPage());
        this.lastActualPage = getLastActualPage();
    }

    /**
     * Fill list of vacancy with actual jobs from every page through "for".
     *
     * @return ready list of vacancy from every actual page.
     */
    public List<Vacancy> fillList() {
        for (int actualPage = FIRST_PAGE; actualPage <= lastActualPage; actualPage++) {
            parsePage(actualPage);
        }
        Collections.reverse(this.jobs);
        return this.jobs;
    }

    /**
     * Parse information about java vacancy from a specific page.
     *
     * @param actualPage - actual page for parse.
     */
    private void parsePage(Integer actualPage) {
        try {
            Document page = Jsoup.connect(URL + actualPage).get();
            Element element = page.selectFirst("table[class=forumTable]");
            Elements elements = element.select("td[class=postslisttopic]");
            for (Element innerElement : elements) {
                if (analysisOfVacancy(innerElement)) {
                    fillVacancyList(innerElement);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Analysis every vacancy from actual page. Is it Java vacancy or not.
     *
     * @param element specific element of page.
     * @return It is Java vacancy (true) or not (false).
     */
    private boolean analysisOfVacancy(Element element) {
        boolean result = false;
        if (element.text().toUpperCase().contains("JAVA")
                && !element.text().toUpperCase().contains("ЗАКРЫТ")
                && !element.text().toUpperCase().contains("JAVASCRIPT")
                && !element.text().toUpperCase().contains("JAVA SCRIPT")
                && uniqueName(element)) {

            result = true;
        }
        return result;
    }

    /**
     * Check this unique vacancy or it has already in our list of vacancy.
     *
     * @param element specific element from page - vacancy.
     * @return It is unique (true) or not (false).
     */
    private boolean uniqueName(Element element) {
        boolean result = true;
        for (Vacancy vacancy : jobs) {
            if (vacancy.getNameOfVacancy().equals(element.getElementsByAttribute("href").first().text())) {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * Put checked vacancy into list of vacancy.
     *
     * @param element checked vacancy.
     */
    private void fillVacancyList(Element element) {
        String nameOfVacancy = element.getElementsByAttribute("href").first().text();
        String link = element.getElementsByAttribute("href").attr("href");
        String textOfVacancy = parseTextOfVacancy(link);
        jobs.add(new VacancyBuilder()
                .nameOfVacancy(nameOfVacancy)
                .link(link)
                .textOfVacancy(textOfVacancy)
                .build());
    }

    /**
     * Get text of vacancy.
     *
     * @param linkOfVacancy - link of vacancy.
     * @return String text of vacancy.
     */
    private String parseTextOfVacancy(String linkOfVacancy) {
        String textOfVacancy = null;
        try {
            Document pageOfVacancy = Jsoup.connect(linkOfVacancy).get();
            Element element = pageOfVacancy.select("td[class=msgBody]").next().first();
            textOfVacancy = element.wholeText().trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return textOfVacancy;
    }

    /**
     * Get page.
     *
     * @return Document page after parsing.
     */
    private static Document getPage() {
        Document page = null;
        try {
            page = Jsoup.connect(URL).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return page;
    }

    /**
     * Get amount of pages on the forum.
     *
     * @param page start page of forum.
     * @return amount of page.
     */
    private Integer amountOfPages(Document page) {
        Element tableWith = page.selectFirst("table[style=font-weight: bold]");
        return Integer.parseInt(tableWith.getElementsByTag("a").last().text());
    }

    /**
     * Get last actual page, which has vacancy of last year
     *
     * @return last actual page.
     */
    private Integer getLastActualPage() {
        Integer result = null;
        for (int actualPage = FIRST_PAGE; actualPage < endPage; actualPage++) {
            try {
                Document page = Jsoup.connect(URL + actualPage).get();
                Element lastTdWithYear = page.selectFirst("table[class=forumTable]").select("td").last();
//                Integer yearOfLastVacancyOnPage = Integer.parseInt(lastTdWithYear.text().substring(6, 9).trim());
                Integer yearOfLastVacancyOnPage = parseYearFromVacancy(lastTdWithYear);
                Integer currentYear = Integer.parseInt(LocalDate.now().toString().substring(2, 4).trim());
                if (yearOfLastVacancyOnPage < currentYear) {
                    result = actualPage;
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return result;
    }

    private Integer parseYearFromVacancy(Element lastTdWithYear) {
        String date = lastTdWithYear.text();
        Integer yearOfVacancy = null;
        Matcher matcher = Pattern.compile("(\\s+)([1-9][0-9])(\\,)").matcher(date);
        while (matcher.find()) {
            yearOfVacancy = Integer.parseInt(matcher.group(2));
        }
        return yearOfVacancy;
    }

    /**
     * Compare two lists of vacancy and leave only unique and new elements.
     *
     * @param oldList list vacancy from database.
     * @return vacancies for update database.
     */
    public List<Vacancy> updateList(List<Vacancy> oldList) {
        List<Vacancy> freshList = fillList();
        freshList.sort(Comparator.comparing(Vacancy::getNameOfVacancy));
        oldList.sort(Comparator.comparing(Vacancy::getNameOfVacancy));
        Iterator<Vacancy> newVacancyList = freshList.listIterator();
        for (Vacancy oldVacancy : oldList) {
            while (newVacancyList.hasNext()) {
                if (newVacancyList.next().getNameOfVacancy().equals(oldVacancy.getNameOfVacancy())) {
                    newVacancyList.remove();
                    break;
                }
            }
        }
        return freshList;
    }
}
