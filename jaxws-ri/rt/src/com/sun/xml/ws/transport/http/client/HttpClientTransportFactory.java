/*
 * The contents of this file are subject to the terms
 * of the Common Development and Distribution License
 * (the "License").  You may not use this file except
 * in compliance with the License.
 * 
 * You can obtain a copy of the license at
 * https://jwsdp.dev.java.net/CDDLv1.0.html
 * See the License for the specific language governing
 * permissions and limitations under the License.
 * 
 * When distributing Covered Code, include this CDDL
 * HEADER in each file and include the License file at
 * https://jwsdp.dev.java.net/CDDLv1.0.html  If applicable,
 * add the following below this CDDL HEADER, with the
 * fields enclosed by brackets "[]" replaced with your
 * own identifying information: Portions Copyright [yyyy]
 * [name of copyright owner]
 */

package com.sun.xml.ws.transport.http.client;

import java.io.OutputStream;
import com.sun.xml.ws.spi.runtime.WSConnection;

import com.sun.xml.ws.spi.runtime.ClientTransportFactory;
import com.sun.xml.ws.api.message.Packet;

/**
 * @author WS Development Team
 */
public class HttpClientTransportFactory implements ClientTransportFactory {


    public HttpClientTransportFactory() {
        this(null);
    }

    public HttpClientTransportFactory(OutputStream logStream) {
        _logStream = logStream;
    }

    /*
    public WSConnection create() {
        Map<String, Object> context = new HashMap<String, Object>();
        context.put(BINDING_ID_PROPERTY, SOAPBinding.SOAP11HTTP_BINDING);
        
        return new HttpClientTransport(_logStream, context);
    }
     */

    /**
     * Binding Id, Endpoint address and other metadata is in the property bag
     */
    public WSConnection create(Packet context) {
        return new HttpClientTransport(_logStream, context);
    }

    private OutputStream _logStream;
}
