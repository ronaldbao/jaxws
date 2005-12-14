package com.sun.xml.ws.sandbox.message.impl.jaxb;

import com.sun.xml.ws.sandbox.XMLStreamWriterEx;
import com.sun.xml.ws.sandbox.message.Header;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.util.JAXBResult;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * {@link Header} whose physical data representation is a JAXB bean.
 *
 * @author Kohsuke Kawaguchi
 */
abstract class JAXBHeader implements Header {

    /**
     * The JAXB object that represents the header.
     */
    private final Object jaxbObject;

    private final Marshaller marshaller;

    // information about this header. lazily obtained.
    private String nsUri;
    private String localName;
    protected String role;

    /**
     * See the <tt>FLAG_***</tt> constants.
     */
    protected int flags;

    public JAXBHeader(Marshaller marshaller, Object jaxbObject) {
        this.marshaller = marshaller;
        this.jaxbObject = jaxbObject;

        if (jaxbObject instanceof JAXBElement) {
            JAXBElement e = (JAXBElement) jaxbObject;
            this.nsUri = e.getName().getNamespaceURI();
            this.localName = e.getName().getLocalPart();
        }
    }

    protected final boolean isSet(int flagMask) {
        return (flags&flagMask)!=0;
    }

    protected final void set(int flagMask) {
        flags |= flagMask;
    }

    /**
     * Lazily parse the first element to obtain attribute values on it.
     */
    protected final void parseIfNecessary() {
        if(isSet(FLAG_PARSED))
            return;

        try {
            marshaller.marshal(jaxbObject,new DefaultHandler() {
                public void startElement(String uri, String localName, String qName, Attributes a) throws SAXException {
                    JAXBHeader.this.nsUri = uri;
                    JAXBHeader.this.localName = localName;
                    checkHeaderAttribute(a);
                    // no need to parse any further.
                    throw aSAXException;
                }
            });
        } catch (JAXBException e) {
            // if it's due to us aborting the processing after the first element,
            // we can safely ignore this exception.
            //
            // if it's due to error in the object, the same error will be reported
            // when the readHeader() method is used, so we don't have to report
            // an error right now.

            // recover by setting dummies.
            nsUri = "##error";
            localName = "##error";
        }
    }

    /**
     * Checks for well-known SOAP attributes.
     *
     * Note that JAXB RI produces interned attribute names.
     */
    protected abstract void checkHeaderAttribute(Attributes a);

    protected final void checkMustUnderstand(String localName, Attributes a, int i) {
        if(localName=="mustUnderstand" && parseBool(a.getValue(i)))
            set(FLAG_MUST_UNDERSTAND);
    }

    protected final boolean parseBool(String value) {
        if(value.length()==0)
            return false;

        char ch = value.charAt(0);
        return ch=='t' || ch=='1';
    }



    public final boolean isMustUnderstood() {
        parseIfNecessary();
        return isSet(FLAG_MUST_UNDERSTAND);
    }

    public String getRole() {
        parseIfNecessary();
        return role;
    }

    public String getNamespaceURI() {
        if(nsUri==null)
            parseIfNecessary();
        return nsUri;
    }

    public String getLocalPart() {
        if(localName==null)
            parseIfNecessary();
        return localName;
    }

    public XMLStreamReader readHeader() {
        // TODO
        throw new UnsupportedOperationException();
    }

    public <T> T readAsJAXB(Unmarshaller unmarshaller) throws JAXBException {
        JAXBResult r = new JAXBResult(unmarshaller);
        marshaller.marshal(jaxbObject,r);
        return (T)r.getResult();
    }

    public void writeTo(XMLStreamWriterEx w) throws XMLStreamException {
        try {
            marshaller.marshal(jaxbObject,w.getBase());
        } catch (JAXBException e) {
            throw new XMLStreamException(e);
        }
    }

    public void writeTo(SOAPMessage saaj) throws SOAPException {
        try {
            marshaller.marshal(jaxbObject,saaj.getSOAPHeader());
        } catch (JAXBException e) {
            throw new SOAPException(e);
        }
    }

    protected static final int FLAG_PARSED            = 0x0001;
    protected static final int FLAG_MUST_UNDERSTAND   = 0x0002;
    protected static final int FLAG_RELAY             = 0x0004;

    private static final SAXException aSAXException = new SAXException();
}
