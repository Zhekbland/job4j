package ru.job4j.sql.parser;

/**
 * Class VacancyBuilder builds class Vacancy.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 14.06.2019.
 */
public class VacancyBuilder {
    private String nameOfVacancy;
    private String textOfVacancy;
    private String link;

    public VacancyBuilder nameOfVacancy(final String nameOfVacancy) {
        this.nameOfVacancy = nameOfVacancy;
        return this;
    }

    public VacancyBuilder textOfVacancy(final String textOfVacancy) {
        this.textOfVacancy = textOfVacancy;
        return this;
    }

    public VacancyBuilder link(final String link) {
        this.link = link;
        return this;
    }

    public String getNameOfVacancy() {
        return nameOfVacancy;
    }

    public String getTextOfVacancy() {
        return textOfVacancy;
    }

    public String getLink() {
        return link;
    }

    public Vacancy build() {
        return new Vacancy(this);
    }
}
