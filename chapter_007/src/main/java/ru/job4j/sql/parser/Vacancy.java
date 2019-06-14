package ru.job4j.sql.parser;

import java.util.Objects;

/**
 * Class Vacancy creates via VacancyBuilder.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 14.06.2019.
 */
public class Vacancy {
    private final String nameOfVacancy;
    private final String textOfVacancy;
    private final String link;

    Vacancy(VacancyBuilder vacancyBuilder) {
        this.nameOfVacancy = vacancyBuilder.getNameOfVacancy();
        this.textOfVacancy = vacancyBuilder.getTextOfVacancy();
        this.link = vacancyBuilder.getLink();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vacancy vacancy = (Vacancy) o;
        return Objects.equals(nameOfVacancy, vacancy.nameOfVacancy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameOfVacancy);
    }
}