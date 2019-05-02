package ru.job4j.sql.sqllite;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

/**
 * Create xml from List.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 02.05.2019.
 */
public class StoreXML {

    private File target;

    public StoreXML(File target) {
        this.target = target;
    }

    public void save(List<Entry> list) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(new Entries(list), target);
        } catch (JAXBException e) {
            System.out.println("JAXB error " + e);
        }
    }
}
