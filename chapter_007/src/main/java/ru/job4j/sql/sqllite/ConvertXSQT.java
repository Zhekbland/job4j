package ru.job4j.sql.sqllite;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

/**
 * Convert from xml with xsl to xslt.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 02.05.2019.
 */
public class ConvertXSQT {

    public void convert(File source, File dest, File scheme) throws TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(new StreamSource(scheme));
        transformer.transform(new StreamSource(source), new StreamResult(dest));
    }
}
