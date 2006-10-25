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

package com.sun.xml.ws.server.sei;

import java.util.HashMap;
import java.util.Map;

import com.sun.xml.ws.api.WSBinding;
import com.sun.xml.ws.api.message.HeaderList;
import com.sun.xml.ws.api.message.Packet;
import com.sun.xml.ws.model.AbstractSEIModelImpl;
import com.sun.xml.ws.model.JavaMethodImpl;

/**
 * @author Arun Gupta
 */
public class ActionBasedDispatcher implements EndpointMethodDispatcher {
    private final WSBinding binding;
    private final Map<String, EndpointMethodHandler> actionMethodHandlers;
    private String dispatchKey;

    public ActionBasedDispatcher(AbstractSEIModelImpl model, WSBinding binding, SEIInvokerTube invokerTube) {
        this.binding = binding;
        actionMethodHandlers = new HashMap<String, EndpointMethodHandler>();

        for( JavaMethodImpl m : model.getJavaMethods() ) {
            EndpointMethodHandler handler = new EndpointMethodHandler(invokerTube,model,m,binding);
            String action = null;
            if (m.getOperation() != null)
                // Could be null for an SE-based endpoint with metadata explicitly set to null
                action = m.getOperation().getOperation().getInput().getAction();
            if (action != null)
                actionMethodHandlers.put(action, handler);
        }
    }

    public EndpointMethodHandler getEndpointMethodHandler(Packet request) {
        dispatchKey = null;

        HeaderList hl = request.getMessage().getHeaders();
        if (hl == null)
            return null;

        String action = null;
        if (binding.getAddressingVersion() != null)
            action = hl.getAction(binding.getAddressingVersion(), binding.getSOAPVersion());

        if (action == null)
            return null;

        dispatchKey = action;
        return actionMethodHandlers.get(action);
    }

    public String getDispatchKey() {
        return dispatchKey;
    }

    public String getName() {
        return "Action-based Dispatcher";
    }
}
