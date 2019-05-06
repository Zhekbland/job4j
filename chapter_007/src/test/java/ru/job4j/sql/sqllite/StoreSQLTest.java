package ru.job4j.sql.sqllite;

import org.junit.Ignore;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Testing StoreSQL, StoreXML, ConvertXSQT.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 02.05.2019.
 */
public class StoreSQLTest {
    @Test
    public void create() {
        StoreSQL sql = new StoreSQL(new Config());
        boolean result = sql.connection();
        assertThat(result, is(true));
    }

    @Test
    public void entryLoad() {
        StoreSQL sql = new StoreSQL(new Config());
        sql.connection();
        sql.generate(100);
        List<Entry> result = sql.load();
        List<Entry> expect = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            expect.add(new Entry(i));
        }
        assertThat(result, is(expect));
    }

    @Ignore
    public void toXML() {
        StoreSQL sql = new StoreSQL(new Config());
        sql.connection();
        sql.generate(100);
        List<Entry> result = sql.load();
        StoreXML storeXML = new StoreXML(new File("entries.xml"));
        storeXML.save(result);
    }

    @Ignore
    public void transform() throws TransformerException {
        File source = new File("entries.xml");
        File dest = new File("entries.xslt");
        File scheme = new File("entries.xsl");
        toXML();
        ConvertXSQT convert = new ConvertXSQT();
        convert.convert(source, dest, scheme);
    }

    @Test
    public void parseAndSum() throws IOException, SAXException, ParserConfigurationException, TransformerException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        toXML();
        transform();
        File file = new File("entries.xslt");
        if (file.exists()) {
            parser.parse(file, new Parse());
        }
        int result = Parse.getSum();
        int expect = 5050;
        assertThat(result, is(expect));
    }
}