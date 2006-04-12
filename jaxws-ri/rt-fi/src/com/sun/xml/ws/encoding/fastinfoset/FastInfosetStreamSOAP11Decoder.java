/*
 * The contents of this file are subject to the terms
 * of the Common Development and Distribution License
 * (the License).  You may not use this file except in
 * compliance with the License.
 * 
 * You can obtain a copy of the license at
 * https://glassfish.dev.java.net/public/CDDLv1.0.html.
 * See the License for the specific language governing
 * permissions and limitations under the License.
 * 
 * When distributing Covered Code, include this CDDL
 * Header Notice in each file and include the License file
 * at https://glassfish.dev.java.net/public/CDDLv1.0.html.
 * If applicable, add the following below the CDDL Header,
 * with the fields enclosed by brackets [] replaced by
 * you own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 * 
 * Copyright 2006 Sun Microsystems Inc. All Rights Reserved
 */
package com.sun.xml.ws.encoding.fastinfoset;

import com.sun.xml.stream.buffer.XMLStreamBuffer;
import com.sun.xml.ws.api.SOAPVersion;
import com.sun.xml.ws.message.stream.StreamHeader;
import com.sun.xml.ws.message.stream.StreamHeader11;

import javax.xml.stream.XMLStreamReader;

/**
 * A decoder that decodes SOAP 1.2 messages infosets encoded as fast infoset
 * documents.
 *
 * @author Paul.Sandoz@Sun.Com
 */
final class FastInfosetStreamSOAP11Decoder extends FastInfosetStreamSOAPDecoder {

    /*package*/ FastInfosetStreamSOAP11Decoder() {
        super(SOAPVersion.SOAP_11);
    }

    protected final StreamHeader createHeader(XMLStreamReader reader, XMLStreamBuffer mark) {
        return new StreamHeader11(reader, mark);
    }
}