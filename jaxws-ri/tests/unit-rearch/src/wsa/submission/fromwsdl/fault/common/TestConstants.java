/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2006-2013 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * http://glassfish.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

package wsa.submission.fromwsdl.fault.common;

/**
 * @author Arun Gupta
 */
public class TestConstants {
    public static final String ADD_NUMBERS_IN_ACTION = "http://example.com/AddNumbersPortType/add";
    public static final String ADD_NUMBERS2_IN_ACTION = "http://example.com/AddNumbersPortType/add2";
    public static final String ADD_NUMBERS3_IN_ACTION = "http://example.com/AddNumbersPortType/addNumbers3Request";
    public static final String ADD_NUMBERS4_IN_ACTION = "http://example.com/AddNumbersPortType/addNumbers4Request";
    public static final String ADD_NUMBERS5_IN_ACTION = "http://example.com/AddNumbersPortType/addNumbers5Request";
    public static final String ADD_NUMBERS6_IN_ACTION = "http://example.com/AddNumbersPortType/addNumbers6Request";

    public static final String ADD_NUMBERS_ADDNUMBERS_ACTION = "http://example.com/AddNumbersPortType/addNumbers/Fault/addFault";
    public static final String ADD_NUMBERS_TOOBIGNUMBERS_ACTION = "http://example.com/AddNumbersPortType/addNumbers/Fault/tooBigFault";
    public static final String ADD_NUMBERS2_ADDNUMBERS_ACTION = "add2fault";
    public static final String ADD_NUMBERS2_TOOBIGNUMBERS_ACTION = "toobig2fault";
    public static final String ADD_NUMBERS3_ADDNUMBERS_ACTION = "add3fault";
    public static final String ADD_NUMBERS3_TOOBIGNUMBERS_ACTION = "http://example.com/AddNumbersPortType/addNumbers3/Fault/tooBig3Fault";
    public static final String ADD_NUMBERS4_ADDNUMBERS_ACTION = "http://example.com/AddNumbersPortType/addNumbers4/Fault/add4Fault";
    public static final String ADD_NUMBERS4_TOOBIGNUMBERS_ACTION = "toobig4fault";
    public static final String ADD_NUMBERS5_ADDNUMBERS_ACTION = "fault5";
    public static final String ADD_NUMBERS6_ADDNUMBERS_ACTION = "http://example.com/AddNumbersPortType/addNumbers6/Fault/add6Fault";
}
