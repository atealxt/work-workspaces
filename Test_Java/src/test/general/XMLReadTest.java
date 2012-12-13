package test.general;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XMLReadTest {

    private static Logger logger = Logger.getLogger(XMLReadTest.class);

    public static void main(final String args[]) {
        read("/log4j.xml");
    }

    public static void read(final String fileName) {

        String stripped = fileName.startsWith("/") ? fileName.substring(1) : fileName;
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        InputStream rsrc = classLoader.getResourceAsStream(stripped);
        if (rsrc == null) {
            throw new RuntimeException("resource has not found: " + stripped);
        }
        dom4j(rsrc);
        try {
            rsrc.close();
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
        }

        System.out.println();

        rsrc = classLoader.getResourceAsStream(stripped);
        if (rsrc == null) {
            throw new RuntimeException("resource has not found: " + stripped);
        }
        stax(rsrc);
        try {
            rsrc.close();
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    private static void dom4j(final InputStream rsrc) {
        try {
            SAXReader saxReader = new SAXReader();
            Document doc = saxReader.read(rsrc);
            saxReader.setValidation(true);

            Element root = doc.getRootElement();
            @SuppressWarnings("unchecked") List<Element> list = root.elements();
            for (Element model : list) {
                logger.debug(model.getName());
            }
        } catch (DocumentException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    private static void stax(final InputStream rsrc) {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try {
            XMLEventReader xmlEventReader = inputFactory.createXMLEventReader(rsrc);
            while (xmlEventReader.hasNext()) {
                XMLEvent event = xmlEventReader.nextEvent();

                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    logger.debug("StartElement: " + startElement.getName().toString());
                } else if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    logger.debug("EndElement: " + endElement.getName().toString());
                } else if (event.isCharacters()) {
                    Characters text = event.asCharacters();
                    if (!text.isWhiteSpace()) {
                        logger.debug("\t" + text.getData());
                    }
                } else if (event.isAttribute()) {
                    logger.debug("Attribute! ");
                }
            }
        } catch (XMLStreamException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

}
