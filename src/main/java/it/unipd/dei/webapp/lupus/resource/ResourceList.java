package it.unipd.dei.webapp.lupus.resource;

import com.fasterxml.jackson.core.JsonGenerator;

import java.io.*;

/**
 * Represents a list of resources that can be serialized to JSON.
 *
 * @param <T> The type of resource contained in the list.
 *
 * @author LupusInFabula Group
 * @version 1.0
 * @since 1.0
 */
public final class ResourceList<T extends Resource> extends AbstractResource {

    /**
     * The list of {@code AbstractResource}s.
     */
    private final Iterable<T> list;

    /**
     * Creates a list of {@code AbstractResource}s.
     *
     * @param list the list of {@code AbstractResource}s.
     */
    public ResourceList(final Iterable<T> list) {

        if (list == null) {
            LOGGER.error("Resource list cannot be null.");
            throw new NullPointerException("Resource list cannot be null.");
        }

        this.list = list;
    }

    @Override
    protected void writeJSON(final OutputStream out) throws IOException {

        final JsonGenerator jg = JSON_FACTORY.createGenerator(out);

        jg.writeStartObject();

        jg.writeFieldName("resource-list");

        jg.writeStartArray();

        jg.flush();

        boolean firstElement = true;

        for (final Resource r : list) {

            // very bad work-around to add commas between resources
            if (firstElement) {
                r.toJSON(out);
                jg.flush();
                firstElement = false;
            } else {
                jg.writeRaw(',');
                jg.flush();

                r.toJSON(out);
                jg.flush();
            }
        }

        jg.writeEndArray();

        jg.writeEndObject();

        jg.flush();
    }

}
