package edu.unsw.comp9321.util;

import org.xml.sax.SAXException;

/**
 * Use for exit SAX parse.
 * Created by Langley on 8/26/16.
 */
public class SAXExitException extends SAXException {

    public SAXExitException(String message) {
        super(message);
    }
}
