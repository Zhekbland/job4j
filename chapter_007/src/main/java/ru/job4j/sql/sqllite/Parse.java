package ru.job4j.sql.sqllite;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import org.xml.sax.Attributes;

/**
 * Parse from xslt and count sum of elements.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 02.05.2019.
 */
public class Parse extends DefaultHandler {

    private static int sum = 0;

    public static int getSum() {
        return sum;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        for (int i = 0; i < attributes.getLength(); i++) {
            sum = sum + Integer.parseInt(attributes.getValue(i));
        }
    }
}


