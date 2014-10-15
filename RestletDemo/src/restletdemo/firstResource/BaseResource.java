package restletdemo.firstResource;

import java.util.Map;

import org.restlet.Context;
import org.restlet.data.Request;
import org.restlet.data.Response;
import org.restlet.resource.Resource;

/**
 * Base resource class that supports common behaviours or attributes shared by all resources.
 *
 */
public abstract class BaseResource extends Resource {

    public BaseResource(final Context context, final Request request, final Response response) {
        super(context, request, response);
    }

    /**
     * Returns the map of items managed by this application.
     *
     * @return the map of items managed by this application.
     */
    protected Map<String, Item> getItems() {
//        return ((FirstResourceApplication) getContext().getAttributes().get("Application.KEY!!!")).getItems();//1.0
        return ((FirstResourceApplication) getApplication()).getItems();
    }
}
