/**
 * Copyright 2005-2008 Noelios Technologies.
 * 
 * The contents of this file are subject to the terms of the following open
 * source licenses: LGPL 3.0 or LGPL 2.1 or CDDL 1.0 (the "Licenses"). You can
 * select the license that you prefer but you may not use this file except in
 * compliance with one of these Licenses.
 * 
 * You can obtain a copy of the LGPL 3.0 license at
 * http://www.gnu.org/licenses/lgpl-3.0.html
 * 
 * You can obtain a copy of the LGPL 2.1 license at
 * http://www.gnu.org/licenses/lgpl-2.1.html
 * 
 * You can obtain a copy of the CDDL 1.0 license at
 * http://www.sun.com/cddl/cddl.html
 * 
 * See the Licenses for the specific language governing permissions and
 * limitations under the Licenses.
 * 
 * Alternatively, you can obtain a royaltee free commercial license with less
 * limitations, transferable or non-transferable, directly at
 * http://www.noelios.com/products/restlet-engine
 * 
 * Restlet is a registered trademark of Noelios Technologies.
 */

package org.restlet.resource;

import java.io.IOException;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

import org.restlet.data.MediaType;
import org.restlet.util.ByteUtils;

/**
 * Representation based on a writable NIO byte channel. This class is a good
 * basis to write your own representations, especially for the dynamic and large
 * ones.<br>
 * <br>
 * For this you just need to create a subclass and override the abstract
 * Representation.write(WritableByteChannel) method. This method will later be
 * called back by the connectors when the actual representation's content is
 * needed.
 * 
 * @author Jerome Louvel
 */
public abstract class WritableRepresentation extends ChannelRepresentation {
    /**
     * Constructor.
     * 
     * @param mediaType
     *            The representation's media type.
     */
    public WritableRepresentation(MediaType mediaType) {
        super(mediaType);
    }

    @Override
    public ReadableByteChannel getChannel() throws IOException {
        return ByteUtils.getChannel(this);
    }

    /**
     * Calls parent's implementation.
     */
    @Override
    public void release() {
        super.release();
    }

    @Override
    public abstract void write(WritableByteChannel writableChannel)
            throws IOException;

}
