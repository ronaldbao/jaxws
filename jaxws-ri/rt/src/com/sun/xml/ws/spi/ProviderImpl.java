/**
 * $Id: ProviderImpl.java,v 1.5 2005-09-07 03:22:48 jitu Exp $
 *
 * Copyright 2005 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.sun.xml.ws.spi;


import com.sun.xml.ws.client.WSServiceDelegate;
import com.sun.xml.ws.transport.http.server.EndpointImpl;

import javax.xml.ws.Service;
import javax.xml.ws.Endpoint;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.spi.ServiceDelegate;
import javax.xml.ws.spi.Provider;
import javax.xml.namespace.QName;

/**
 *
 * @author WS Development Team
 */
public class ProviderImpl extends Provider {
    
    @Override
    public Endpoint createEndpoint(String bindingId, Object implementor) {
        return new EndpointImpl(bindingId, implementor);
    }
    
    @Override
    public ServiceDelegate createServiceDelegate(
                                    java.net.URL wsdlDocumentLocation,
                                    QName serviceName, Class serviceClass){
          return
              new WSServiceDelegate(wsdlDocumentLocation, serviceName, serviceClass);
    }
    
    @Override
    public Endpoint createAndPublishEndpoint(String address,
                          Object implementor) {
        Endpoint endpoint = new EndpointImpl(null, implementor);
        endpoint.publish(address);
        return endpoint;
    }
    
}
