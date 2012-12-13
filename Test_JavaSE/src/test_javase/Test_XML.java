/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test_javase;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 *
 * @author Administrator
 */
public class Test_XML {

    public static void main(String args[]) {
        try {
            read("/readin.xml");
        } catch (MappingNotFoundException ex) {
            Logger.getLogger(Test_XML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void read(String fileName) throws MappingNotFoundException {

        String stripped = fileName.startsWith("/") ? fileName.substring(1) : fileName;
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream rsrc = classLoader.getResourceAsStream(stripped);
        if (rsrc == null) {
            throw new MappingNotFoundException("resource has not found: " + stripped);
        }

        dom4j(rsrc);
        //stax(rsrc);

        try {
            rsrc.close();
        } catch (IOException ex) {
            Logger.getLogger(Test_XML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void stax(InputStream rsrc) {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try {
            XMLEventReader xmlEventReader = inputFactory.createXMLEventReader(rsrc);
            while (xmlEventReader.hasNext()) {
                XMLEvent event = xmlEventReader.nextEvent();

                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    System.out.println("StartElement: " + startElement.getName().toString());
                } else if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    System.out.println("EndElement: " + endElement.getName().toString());
                } else if (event.isCharacters()) {
                    Characters text = event.asCharacters();
                    if (!text.isWhiteSpace()) {
                        System.out.println("\t" + text.getData());
                    }
                } else if (event.isAttribute()) {
                    System.out.println("Attribute! ");
                }
            }
        } catch (XMLStreamException ex) {
            Logger.getLogger(Test_XML.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static void dom4j(InputStream rsrc) {
        try {
            SAXReader saxReader = new SAXReader();
            Document doc = saxReader.read(rsrc);
            saxReader.setValidation(true);

            Element root = doc.getRootElement();
            List list = root.elements();
            Element model = null;
            for (Iterator it = list.iterator(); it.hasNext();) {
                model = (Element) it.next();
                System.out.println(model);                
            }

        } catch (DocumentException ex) {
            Logger.getLogger(Test_XML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

class MappingNotFoundException extends Exception {

    public MappingNotFoundException(Throwable root) {
        super(root);
    }

    public MappingNotFoundException(String string, Throwable root) {
        super(string, root);
    }

    public MappingNotFoundException(String s) {
        super(s);
    }
}
