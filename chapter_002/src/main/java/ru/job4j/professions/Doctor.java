package ru.job4j.professions;

/**
 * Class Doctor extends Profession and heal patient.
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru)
 * @version $Id$
 * @since  0.1
 */
public class Doctor extends Profession {

    public Diagnose heal(Patient patient) {
        Diagnose diagnose = new Diagnose();
        return diagnose;
    }
}
