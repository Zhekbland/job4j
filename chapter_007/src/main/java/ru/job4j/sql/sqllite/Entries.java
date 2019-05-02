package ru.job4j.sql.sqllite;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Entries for JAXB.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 02.05.2019.
 */
@XmlRootElement
public class Entries {
    private List<Entry> entries;

    public Entries() {
    }

    public Entries(List<Entry> entries) {
        this.entries = entries;
    }

    @XmlElement(name = "entry")
    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }
}
