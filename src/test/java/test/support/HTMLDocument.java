package test.support;

import org.cyberneko.html.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by L.x on 15-5-28.
 */
public class HTMLDocument {
    public static Element toElement(InputStream html) throws SAXException, IOException {
        return from(html).getDocumentElement();
    }

    public static Document from(InputStream html) throws SAXException, IOException {
        DOMParser parser = new DOMParser();
        parser.parse(new InputSource(html));
        return parser.getDocument();
    }
}
