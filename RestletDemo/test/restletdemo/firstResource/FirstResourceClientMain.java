package restletdemo.firstResource;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.restlet.Client;
import org.restlet.data.Form;
import org.restlet.data.Protocol;
import org.restlet.data.Reference;
import org.restlet.data.Response;
import org.restlet.resource.Representation;

public class FirstResourceClientMain {

    private static Logger logger = Logger.getLogger(FirstResourceClientMain.class.getName());

    public static void main(final String[] args) throws IOException {
        // Define our Restlet HTTP client.
        Client client = new Client(Protocol.HTTP);

        // The URI of the resource "list of items".
        Reference itemsUri = new Reference("http://localhost:8080/RestletDemo/items");

        // Create a new item
        Item item = new Item("item1", "this is an item.");
        Reference itemUri = createItem(item, client, itemsUri);
        if (itemUri != null) {
            // Prints the representation of the newly created resource.
            get(client, itemUri);
        }

        // Prints the list of registered items.
        get(client, itemsUri);

        // Update the item
        item.setDescription("This is an other description");
        updateItem(item, client, itemUri);

        // Prints the list of registered items.
        get(client, itemsUri);

        // delete the item
        deleteItem(client, itemUri);

        // Print the list of registered items.
        get(client, itemsUri);

    }

    /**
     * Try to create a new item.
     *
     * @param item the new item.
     * @param client the Restlet HTTP client.
     * @param itemsUri where to POST the data.
     * @return the Reference of the new resource if the creation succeeds, null otherwise.
     * @throws IOException
     */
    public static Reference createItem(final Item item, final Client client, final Reference itemsUri)
            throws IOException {
        // Gathering informations into a Web form.
        Form form = new Form();
        form.add("name", item.getName());
        form.add("description", item.getDescription());
        Representation rep = form.getWebRepresentation();

        // Launch the request
        Response response = client.post(itemsUri, rep);
        if (!response.getStatus().isSuccess()) {
            logger.warn("createItem fail");
        }
        if (response.getStatus().isSuccess()) {
            return response.getEntity().getIdentifier();
        }
        return null;
    }

    /**
     * Prints the resource's representation.
     *
     * @param client client Restlet.
     * @param reference the resource's URI
     * @throws IOException
     */
    public static void get(final Client client, final Reference reference) throws IOException {
        Response response = client.get(reference);
        if (!response.getStatus().isSuccess()) {
            logger.warn("get fail");
        }
        if (response.isEntityAvailable()) {
            logger.debug("response.getEntity().getText(): " + response.getEntity().getText());
        }
    }

    /**
     * Try to update an item.
     *
     * @param item the item.
     * @param client the Restlet HTTP client.
     * @param itemUri the resource's URI.
     * @throws IOException
     */
    public static boolean updateItem(final Item item, final Client client, final Reference itemUri) throws IOException {
        // Gathering informations into a Web form.
        Form form = new Form();
        form.add("name", item.getName());
        form.add("description", item.getDescription());
        Representation rep = form.getWebRepresentation();

        Response response = client.put(itemUri, rep);
        return response.getStatus().isSuccess();
    }

    /**
     * Try to delete an item.
     *
     * @param client the Restlet HTTP client.
     * @param itemUri the resource's URI.
     * @throws IOException
     */
    public static boolean deleteItem(final Client client, final Reference itemUri) throws IOException {
        Response response = client.delete(itemUri);
        return response.getStatus().isSuccess();
    }

}
