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

package com.sun.tools.ws.processor.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.namespace.QName;

/**
 *
 * @author WS Development Team
 */
public abstract class Message extends ModelObject {

    public void addBodyBlock(Block b) {
        if (_bodyBlocks.containsKey(b.getName())) {
            throw new ModelException("model.uniqueness");
        }
        _bodyBlocks.put(b.getName(), b);
        b.setLocation(Block.BODY);
    }

    public Iterator<Block> getBodyBlocks() {
        return _bodyBlocks.values().iterator();
    }

    public int getBodyBlockCount() {
        return _bodyBlocks.size();
    }

    /* serialization */
    public Map<QName, Block> getBodyBlocksMap() {
        return _bodyBlocks;
    }

    /* serialization */
    public void setBodyBlocksMap(Map<QName, Block> m) {
        _bodyBlocks = m;
    }

    public boolean isBodyEmpty() {
        return getBodyBlocks().hasNext();
    }

    public boolean isBodyEncoded() {
        boolean isEncoded = false;
        for (Iterator iter = getBodyBlocks(); iter.hasNext();) {
            Block bodyBlock = (Block) iter.next();
            if (bodyBlock.getType().isSOAPType()) {
                isEncoded = true;
            }
        }
        return isEncoded;
    }

    public void addHeaderBlock(Block b) {
        if (_headerBlocks.containsKey(b.getName())) {
            throw new ModelException("model.uniqueness");
        }
        _headerBlocks.put(b.getName(), b);
        b.setLocation(Block.HEADER);
    }

    public Iterator<Block> getHeaderBlocks() {
        return _headerBlocks.values().iterator();
    }

    public Collection<Block> getHeaderBlockCollection() {
        return _headerBlocks.values();
    }

    public int getHeaderBlockCount() {
        return _headerBlocks.size();
    }

    /* serialization */
    public Map<QName, Block> getHeaderBlocksMap() {
        return _headerBlocks;
    }

    /* serialization */
    public void setHeaderBlocksMap(Map<QName, Block> m) {
        _headerBlocks = m;
    }

    /** attachment block */
    public void addAttachmentBlock(Block b) {
        if (_attachmentBlocks.containsKey(b.getName())) {
            throw new ModelException("model.uniqueness");
        }
        _attachmentBlocks.put(b.getName(), b);
        b.setLocation(Block.ATTACHMENT);
    }

    public void addUnboundBlock(Block b) {
        if (_unboundBlocks.containsKey(b.getName())) {
            return;
        }
        _unboundBlocks.put(b.getName(), b);
        b.setLocation(Block.UNBOUND);
    }

    public Iterator<Block> getUnboundBlocks() {
        return _unboundBlocks.values().iterator();
    }

    /* serialization */
    public Map<QName, Block> getUnboundBlocksMap() {
        return _unboundBlocks;
    }

    public int getUnboundBlocksCount() {
        return _unboundBlocks.size();
    }

    /* serialization */
    public void setUnboundBlocksMap(Map<QName, Block> m) {
        _unboundBlocks = m;
    }


    public Iterator<Block> getAttachmentBlocks() {
        return _attachmentBlocks.values().iterator();
    }

    public int getAttachmentBlockCount () {
        return _attachmentBlocks.size();
    }

        /* serialization */
    public Map<QName, Block> getAttachmentBlocksMap() {
        return _attachmentBlocks;
    }

    /* serialization */
    public void setAttachmentBlocksMap(Map<QName, Block> m) {
        _attachmentBlocks = m;
    }

    public void addParameter(Parameter p) {
        if (_parametersByName.containsKey(p.getName())) {
            throw new ModelException("model.uniqueness");
        }
        _parameters.add(p);
        _parametersByName.put(p.getName(), p);
    }

    public Parameter getParameterByName(String name) {
        if (_parametersByName.size() != _parameters.size()) {
            initializeParametersByName();
        }
        return (Parameter) _parametersByName.get(name);
    }

    public Iterator<Parameter> getParameters() {
        return _parameters.iterator();
    }

    /* serialization */
    public List<Parameter> getParametersList() {
        return _parameters;
    }

    /* serialization */
    public void setParametersList(List<Parameter> l) {
        _parameters = l;
    }

    private void initializeParametersByName() {
        _parametersByName = new HashMap();
        if (_parameters != null) {
            for (Iterator iter = _parameters.iterator(); iter.hasNext();) {
                Parameter param = (Parameter) iter.next();
                if (param.getName() != null &&
                    _parametersByName.containsKey(param.getName())) {

                    throw new ModelException("model.uniqueness");
                }
                _parametersByName.put(param.getName(), param);
            }
        }
    }

    public Set<Block> getAllBlocks(){
        Set<Block> blocks = new HashSet<Block>();
        blocks.addAll(_bodyBlocks.values());
        blocks.addAll(_headerBlocks.values());
        blocks.addAll(_attachmentBlocks.values());
        return blocks;
    }

    private Map<QName, Block> _attachmentBlocks = new HashMap<QName, Block>();
    private Map<QName, Block> _bodyBlocks = new HashMap<QName, Block>();
    private Map<QName, Block> _headerBlocks = new HashMap<QName, Block>();
    private Map<QName, Block> _unboundBlocks = new HashMap<QName, Block>();
    private List<Parameter> _parameters = new ArrayList<Parameter>();
    private Map<String, Parameter> _parametersByName = new HashMap<String, Parameter>();
}
