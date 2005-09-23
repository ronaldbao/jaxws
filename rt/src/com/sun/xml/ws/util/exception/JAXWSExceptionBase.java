/*
 * $Id: JAXWSExceptionBase.java,v 1.5 2005-09-23 22:12:35 kohsuke Exp $
 */

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

package com.sun.xml.ws.util.exception;

import com.sun.xml.ws.util.localization.Localizable;
import com.sun.xml.ws.util.localization.Localizer;

import javax.xml.ws.WebServiceException;

/**
 * Represents a {@link WebServiceException} with
 * localizable message.
 * 
 * @author WS Development Team
 */
public abstract class JAXWSExceptionBase
    extends WebServiceException implements Localizable {

    private final String key;
    private final Object[] args;

    protected JAXWSExceptionBase(String key, Object... args) {
        super(findNestedException(args));
        if(args==null)  args = new Object[0];
        this.key = key;
        this.args = args;
    }

    /**
     * Creates a new exception that wraps the specified exception.
     */
    protected JAXWSExceptionBase(Throwable throwable) {
        super(throwable);
        this.key = Localizable.NOT_LOCALIZABLE;
        this.args = new Object[]{throwable.toString()};
    }

    private static Throwable findNestedException(Object[] args) {
        if (args == null)
            return null;

        for( Object o : args )
            if(o instanceof Throwable)
                return (Throwable)o;
        return null;
    }

    public String getKey() {
        return key;
    }

    public Object[] getArguments() {
        return args;
    }

    public String toString() {
        // for debug purposes only
        //return getClass().getName() + " (" + getKey() + ")";
        return getMessage();
    }

    public String getMessage() {
        Localizer localizer = new Localizer();
        return localizer.localize(this);
    }
}
