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
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.URIResolver;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.restlet.Context;
import org.restlet.data.Reference;
import org.restlet.data.Response;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLFilter;
import org.xml.sax.XMLReader;

/**
 * Representation able to apply an XSLT transformation. The internal JAXP
 * transformer is created when the getTransformer() method is first called. So,
 * if you need to specify a custom URI resolver, you need to do it before
 * actually using the representation for a transformation.<br>
 * <br>
 * This representation should be viewed as a wrapper representation that applies
 * a transform sheet on a source representation when it is read or written out.
 * Therefore, it isn't intended to be reused on different sources. For this use
 * case, you should instead use the {@link org.restlet.Transformer} filter.
 * 
 * @author Jerome Louvel
 */
public class TransformRepresentation extends OutputRepresentation {

    /**
     * Abstract SAX XML Reader.
     * 
     * @author Warren Janssens
     */
    private static abstract class AbstractXmlReader implements XMLReader {

        /** The features map. */
        private final HashMap<String, Boolean> features;

        /** The properties map. */
        private final HashMap<String, Object> properties;

        /** The entity resolver. */
        private EntityResolver entityResolver;

        /** The DTD handler. */
        private DTDHandler handler;

        /** The content handler. */
        private ContentHandler contentHandler;

        /** The error handler. */
        private ErrorHandler errorHandler;

        /**
         * Default constructor.
         */
        public AbstractXmlReader() {
            this.features = new HashMap<String, Boolean>();
            this.properties = new HashMap<String, Object>();
        }

        /**
         * Return the content handler.
         * 
         * @return The content handler.
         * @see XMLReader#getContentHandler()
         */
        public ContentHandler getContentHandler() {
            return contentHandler;
        }

        /**
         * Return the DTD handler.
         * 
         * @return The DTD handler.
         * @see XMLReader#getDTDHandler()
         */
        public DTDHandler getDTDHandler() {
            return handler;
        }

        /**
         * Return the entity resolver.
         * 
         * @return The entity resolver.
         * @see XMLReader#getEntityResolver()
         */
        public EntityResolver getEntityResolver() {
            return entityResolver;
        }

        /**
         * Return the error handler.
         * 
         * @return The error handler.
         * @see XMLReader#getErrorHandler()
         */
        public ErrorHandler getErrorHandler() {
            return errorHandler;
        }

        /**
         * Returns the feature by name.
         * 
         * @param name
         *            The feature name.
         * @return The feature.
         * @see XMLReader#getFeature(String)
         */
        public boolean getFeature(String name)
                throws SAXNotRecognizedException, SAXNotSupportedException {
            final Boolean result = features.get(name);
            return result == null ? false : result.booleanValue();
        }

        /**
         * Returns the property by name.
         * 
         * @param name
         *            The property name.
         * @return The property.
         * @see XMLReader#getProperty(String)
         */
        public Object getProperty(String name)
                throws SAXNotRecognizedException, SAXNotSupportedException {
            return properties.get(name);
        }

        /**
         * Sets the content handler.
         * 
         * @param contentHandler
         *            The content handler.
         * @see XMLReader#setContentHandler(ContentHandler)
         */
        public void setContentHandler(ContentHandler contentHandler) {
            this.contentHandler = contentHandler;
        }

        /**
         * Sets the DTD handler.
         * 
         * @param handler
         *            The DTD handler.
         * @see XMLReader#setDTDHandler(DTDHandler)
         */
        public void setDTDHandler(DTDHandler handler) {
            this.handler = handler;
        }

        /**
         * Sets the entity resolver.
         * 
         * @param entityResolver
         *            The entity resolver.
         * @see XMLReader#setEntityResolver(EntityResolver)
         */
        public void setEntityResolver(EntityResolver entityResolver) {
            this.entityResolver = entityResolver;
        }

        /**
         * Sets the error handler.
         * 
         * @param errorHandler
         *            The error handler.
         * @see XMLReader#setErrorHandler(ErrorHandler)
         */
        public void setErrorHandler(ErrorHandler errorHandler) {
            this.errorHandler = errorHandler;
        }

        /**
         * Sets a feature.
         * 
         * @param name
         *            The feature name.
         * @param value
         *            The feature value.
         * @see XMLReader#setFeature(String, boolean)
         */
        public void setFeature(String name, boolean value)
                throws SAXNotRecognizedException, SAXNotSupportedException {
            this.features.put(name, value);
        }

        /**
         * Sets a property.
         * 
         * @param name
         *            The property name.
         * @param value
         *            The property value.
         * @see XMLReader#setProperty(String, Object)
         */
        public void setProperty(String name, Object value)
                throws SAXNotRecognizedException, SAXNotSupportedException {
            this.properties.put(name, value);
        }

    }

    /**
     * URI resolver based on a Restlet Context instance.
     * 
     * @author Jerome Louvel
     */
    private final static class ContextResolver implements URIResolver {
        /** The Restlet context. */
        private final Context context;

        /**
         * Constructor.
         * 
         * @param context
         *            The Restlet context.
         */
        public ContextResolver(Context context) {
            this.context = context;
        }

        /**
         * Resolves a target reference into a Source document.
         * 
         * @see javax.xml.transform.URIResolver#resolve(java.lang.String,
         *      java.lang.String)
         */
        public Source resolve(String href, String base)
                throws TransformerException {
            Source result = null;

            if (this.context != null) {
                Reference targetRef = null;

                if ((base != null) && !base.equals("")) {
                    // Potentially a relative reference
                    final Reference baseRef = new Reference(base);
                    targetRef = new Reference(baseRef, href);
                } else {
                    // No base, assume "href" is an absolute URI
                    targetRef = new Reference(href);
                }

                final String targetUri = targetRef.getTargetRef().toString();
                final Response response = this.context.getClientDispatcher()
                        .get(targetUri);
                if (response.getStatus().isSuccess()
                        && response.isEntityAvailable()) {
                    try {
                        result = new StreamSource(response.getEntity()
                                .getStream());
                        result.setSystemId(targetUri);

                    } catch (IOException e) {
                        this.context.getLogger().log(Level.WARNING,
                                "I/O error while getting the response stream",
                                e);
                    }
                }
            }

            return result;
        }
    }

    /** The JAXP transformer output properties. */
    private volatile Map<String, String> outputProperties;

    /** The JAXP transformer parameters. */
    private volatile Map<String, Object> parameters;

    /** The source representation to transform. */
    private volatile Representation sourceRepresentation;

    /** The template to be used and reused. */
    private volatile Templates templates;

    /** The XSLT transform sheet to apply to message entities. */
    private volatile Representation transformSheet;

    /** The URI resolver. */
    private volatile URIResolver uriResolver;

    /**
     * Constructor. Note that a default URI resolver will be created based on
     * the given context.
     * 
     * @param context
     *            The parent context.
     * @param source
     *            The source representation to transform.
     * @param transformSheet
     *            The XSLT transform sheet to apply.
     */
    public TransformRepresentation(Context context, Representation source,
            Representation transformSheet) {
        this((context == null) ? null : new ContextResolver(context), source,
                transformSheet);
    }

    /**
     * Default constructor.
     * 
     * @param source
     *            The source representation to transform.
     * @param transformSheet
     *            The XSLT transform sheet to apply.
     */
    public TransformRepresentation(Representation source,
            Representation transformSheet) {
        this((URIResolver) null, source, transformSheet);
    }

    /**
     * Constructor. Note that a default URI resolver will be created based on
     * the given context.
     * 
     * @param uriResolver
     *            The JAXP URI resolver.
     * @param source
     *            The source representation to transform.
     * @param transformSheet
     *            The XSLT transform sheet to apply.
     */
    public TransformRepresentation(URIResolver uriResolver,
            Representation source, Representation transformSheet) {
        this(uriResolver, source, transformSheet, null);
    }

    /**
     * Constructor.
     * 
     * @param uriResolver
     *            The optional JAXP URI resolver.
     * @param source
     *            The source representation to transform.
     * @param templates
     *            The precompiled JAXP template.
     */
    private TransformRepresentation(URIResolver uriResolver,
            Representation source, Representation transformSheet,
            Templates templates) {
        super(null);
        this.sourceRepresentation = source;
        this.templates = templates;
        this.transformSheet = transformSheet;
        this.uriResolver = uriResolver;
        this.parameters = new HashMap<String, Object>();
        this.outputProperties = new HashMap<String, String>();
    }

    /**
     * Constructor.
     * 
     * @param uriResolver
     *            The optional JAXP URI resolver.
     * @param source
     *            The source representation to transform.
     * @param templates
     *            The precompiled JAXP template.
     */
    public TransformRepresentation(URIResolver uriResolver,
            Representation source, Templates templates) {
        this(uriResolver, source, null, templates);
    }

    /**
     * Returns the modifiable map of JAXP transformer output properties.
     * 
     * @return The JAXP transformer output properties.
     */
    public Map<String, String> getOutputProperties() {
        return this.outputProperties;
    }

    /**
     * Returns the modiable map of JAXP transformer parameters.
     * 
     * @return The JAXP transformer parameters.
     */
    public Map<String, Object> getParameters() {
        return this.parameters;
    }

    /**
     * Returns the SAX source associated to the source representation.
     * 
     * @return The SAX source associated to the source representation.
     * @throws IOException
     */
    public SAXSource getSaxSource() throws IOException {
        SAXSource result = null;

        if (getSourceRepresentation() instanceof XmlRepresentation) {
            result = ((XmlRepresentation) getSourceRepresentation())
                    .getSaxSource();
        } else if (getSourceRepresentation() instanceof TransformRepresentation) {
            XMLReader reader = new AbstractXmlReader() {

                public void parse(InputSource input) throws IOException,
                        SAXException {
                    try {
                        TransformRepresentation source = (TransformRepresentation) getSourceRepresentation();
                        source.getTransformer().transform(
                                source.getSaxSource(),
                                new SAXResult(getContentHandler()));
                    } catch (TransformerException te) {
                        throw new IOException("Transformer exception. "
                                + te.getMessage());
                    }
                }

                public void parse(String systemId) throws IOException,
                        SAXException {
                    throw new IllegalStateException("Not implemented");
                }
            };

            result = new SAXSource(reader, null);
        } else {
            // Prepare the source and result documents
            result = new SAXSource(new InputSource(getSourceRepresentation()
                    .getStream()));
        }

        if (getSourceRepresentation().getIdentifier() != null) {
            result.setSystemId(getSourceRepresentation().getIdentifier()
                    .getTargetRef().toString());
        }

        return result;
    }

    /**
     * Returns the default SAX transformer factory.
     * 
     * @return The default SAX transformer factory.
     */
    private SAXTransformerFactory getSaxTransformerFactory() {
        final SAXTransformerFactory result = (SAXTransformerFactory) TransformerFactory
                .newInstance();
        return result;
    }

    /**
     * Returns the source representation to transform.
     * 
     * @return The source representation to transform.
     */
    public Representation getSourceRepresentation() {
        return this.sourceRepresentation;
    }

    /**
     * Returns the templates to be used and reused. If no one exists, it creates
     * a new one based on the transformSheet representation and on the URI
     * resolver.
     * 
     * @return The templates to be used and reused.
     */
    public Templates getTemplates() throws IOException {
        if (this.templates == null) {
            try {
                // Prepare the XSLT transformer documents
                final StreamSource transformSource = new StreamSource(
                        getTransformSheet().getStream());

                if (getTransformSheet().getIdentifier() != null) {
                    transformSource.setSystemId(getTransformSheet()
                            .getIdentifier().getTargetRef().toString());
                }

                // Create the transformer factory
                final TransformerFactory transformerFactory = TransformerFactory
                        .newInstance();

                // Set the URI resolver
                if (getUriResolver() != null) {
                    transformerFactory.setURIResolver(getUriResolver());
                }

                // Create a new transformer
                this.templates = transformerFactory
                        .newTemplates(transformSource);
            } catch (TransformerConfigurationException tce) {
                throw new IOException("Transformer configuration exception. "
                        + tce.getMessage());
            }
        }

        return this.templates;
    }

    /**
     * Returns a new transformer to be used. Creation is based on the
     * {@link #getTemplates()}.newTransformer() method.
     * 
     * @return The new transformer to be used.
     */
    public Transformer getTransformer() throws IOException {
        Transformer result = null;

        try {
            final Templates templates = getTemplates();

            if (templates != null) {
                result = templates.newTransformer();

                if (this.uriResolver != null) {
                    result.setURIResolver(getUriResolver());
                }

                // Set the parameters
                for (final String name : getParameters().keySet()) {
                    result.setParameter(name, getParameters().get(name));
                }

                // Set the output properties
                for (final String name : getOutputProperties().keySet()) {
                    result.setOutputProperty(name, getOutputProperties().get(
                            name));
                }
            }
        } catch (TransformerConfigurationException tce) {
            throw new IOException("Transformer configuration exception. "
                    + tce.getMessage());
        } catch (TransformerFactoryConfigurationError tfce) {
            throw new IOException(
                    "Transformer factory configuration exception. "
                            + tfce.getMessage());
        }

        return result;
    }

    /**
     * Returns the SAX transformer handler associated to the transform sheet.
     * 
     * @return The SAX transformer handler.
     * @throws IOException
     */
    public TransformerHandler getTransformerHandler() throws IOException {
        TransformerHandler result = null;
        final Templates templates = getTemplates();

        if (templates != null) {
            try {
                result = getSaxTransformerFactory().newTransformerHandler(
                        templates);
            } catch (TransformerConfigurationException tce) {
                throw new IOException("Transformer configuration exception. "
                        + tce.getMessage());
            }
        }

        return result;
    }

    /**
     * Returns the XSLT transform sheet to apply to the source representation.
     * 
     * @return The XSLT transform sheet to apply.
     */
    public Representation getTransformSheet() {
        return this.transformSheet;
    }

    /**
     * Returns the URI resolver.
     * 
     * @return The URI resolver.
     */
    public URIResolver getUriResolver() {
        return this.uriResolver;
    }

    /**
     * Returns the URI resolver.
     * 
     * @return The URI resolver.
     * @deprecated Use the getUriResolver method instead.
     */
    @Deprecated
    public URIResolver getURIResolver() {
        return this.uriResolver;
    }

    /**
     * Returns the SAX XML filter applying the transform sheet to its input.
     * 
     * @return The SAX XML filter.
     * @throws IOException
     */
    public XMLFilter getXmlFilter() throws IOException {
        XMLFilter result = null;
        final Templates templates = getTemplates();

        if (templates != null) {
            try {
                result = getSaxTransformerFactory().newXMLFilter(templates);
            } catch (TransformerConfigurationException tce) {
                throw new IOException("Transformer configuration exception. "
                        + tce.getMessage());
            }
        }

        return result;
    }

    /**
     * Releases the source and transform sheet representations, the transformer
     * and the URI resolver.
     */
    @Override
    public void release() {
        if (this.sourceRepresentation != null) {
            this.sourceRepresentation.release();
            this.sourceRepresentation = null;
        }

        if (this.templates != null) {
            this.templates = null;
        }

        if (this.transformSheet != null) {
            this.transformSheet.release();
            this.transformSheet = null;
        }

        if (this.uriResolver != null) {
            this.uriResolver = null;
        }

        super.release();
    }

    /**
     * Sets the modifiable map of JAXP transformer output properties.
     * 
     * @param outputProperties
     *            The JAXP transformer output properties.
     */
    public void setOutputProperties(Map<String, String> outputProperties) {
        this.outputProperties = outputProperties;
    }

    /**
     * Sets the JAXP transformer parameters.
     * 
     * @param parameters
     *            The JAXP transformer parameters.
     */
    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    /**
     * Sets the source representation to transform.
     * 
     * @param source
     *            The source representation to transform.
     */
    public void setSourceRepresentation(Representation source) {
        this.sourceRepresentation = source;
    }

    /**
     * Sets the templates to be used and reused.
     * 
     * @param templates
     *            The templates to be used and reused.
     */
    public void setTemplates(Templates templates) {
        this.templates = templates;
    }

    /**
     * Sets the XSLT transform sheet to apply to message entities.
     * 
     * @param transformSheet
     *            The XSLT transform sheet to apply to message entities.
     */
    public void setTransformSheet(Representation transformSheet) {
        this.transformSheet = transformSheet;
    }

    /**
     * Sets the URI resolver.
     * 
     * @param uriResolver
     *            The URI resolver.
     */
    public void setUriResolver(URIResolver uriResolver) {
        this.uriResolver = uriResolver;
    }

    @Override
    public void write(OutputStream outputStream) throws IOException {
        try {
            // Generates the result of the transformation
            getTransformer().transform(getSaxSource(),
                    new StreamResult(outputStream));
        } catch (TransformerException te) {
            throw new IOException("Transformer exception. " + te.getMessage());
        }
    }
}
