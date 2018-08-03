package ru.job4j.professions;

/**
 * Class Doctor extends Profession and heal patient.
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru)
 * @version $Id$
 * @since  0.1
 */
public class Teacher extends Profession {

    public void name(Profession profession) {
    }

    public void profession(Profession profession) {
    }

    public Teaching teach(Student student) {
        Teaching teaching = new Teaching();
        return teaching;
    }
}
