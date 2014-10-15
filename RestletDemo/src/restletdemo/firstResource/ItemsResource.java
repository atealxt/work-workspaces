package restletdemo.firstResource;

import java.io.IOException;
import java.util.Collection;

import org.restlet.Context;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.data.Request;
import org.restlet.data.Response;
import org.restlet.data.Status;
import org.restlet.resource.DomRepresentation;
import org.restlet.resource.Representation;
import org.restlet.resource.StringRepresentation;
import org.restlet.resource.Variant;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Resource that manages a list of items.
 *
 */
public class ItemsResource extends BaseResource {

	/** List of items. */
	Collection<Item> items;

	public ItemsResource(final Context context, final Request request, final Response response) {
		super(context, request, response);

		// Get the items directly from the "persistence layer".
		items = getItems().values();

		// Declare the kind of representations supported by this resource.
		getVariants().add(new Variant(MediaType.TEXT_XML));
	}

	@Override
	public boolean allowPost() {
		return true;
	}

	/**
	 * Returns a listing of all registered items.
	 */
	@Override
	public Representation getRepresentation(final Variant variant) {
		// Generate the right representation according to its media type.
		if (MediaType.TEXT_XML.equals(variant.getMediaType())) {
			try {
				DomRepresentation representation = new DomRepresentation(
						MediaType.TEXT_XML);
				// Generate a DOM document representing the list of
				// items.
				Document d = representation.getDocument();
				Element r = d.createElement("items");
				d.appendChild(r);
				for (Item item : items) {
					Element eltItem = d.createElement("item");

					Element eltName = d.createElement("name");
					eltName.appendChild(d.createTextNode(item.getName()));
					eltItem.appendChild(eltName);

					Element eltDescription = d.createElement("description");
					eltDescription.appendChild(d.createTextNode(item
							.getDescription()));
					eltItem.appendChild(eltDescription);

					r.appendChild(eltItem);
				}
				d.normalizeDocument();

				// Returns the XML representation of this document.
				return representation;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	/**
	 * Handle POST requests: create a new item.
	 */
	@Override
	public void post(final Representation entity) {
		// Parse the given representation and retrieve pairs of
		// "name=value" tokens.
		Form form = new Form(entity);
		String itemName = form.getFirstValue("name");
		String itemDescription = form.getFirstValue("description");

		// Check that the item is not already registered.
		if (getItems().containsKey(itemName)) {
			generateErrorRepresentation(
					"Item " + itemName + " already exists.", "1", getResponse());
		} else {
			// Register the new item
			getItems().put(itemName, new Item(itemName, itemDescription));

			// Set the response's status and entity
			getResponse().setStatus(Status.SUCCESS_CREATED);
			Representation rep = new StringRepresentation("Item created",
					MediaType.TEXT_PLAIN);
			// Indicates where is located the new resource.
			rep.setIdentifier(getRequest().getResourceRef().getIdentifier()
					+ "/" + itemName);
			getResponse().setEntity(rep);
		}
	}

	/**
	 * Generate an XML representation of an error response.
	 *
	 * @param errorMessage
	 *            the error message.
	 * @param errorCode
	 *            the error code.
	 */
	private void generateErrorRepresentation(final String errorMessage,
			final String errorCode, final Response response) {
		// This is an error
		response.setStatus(Status.CLIENT_ERROR_NOT_FOUND);
		try {
			// Generate the output representation
			DomRepresentation representation = new DomRepresentation(
					MediaType.TEXT_XML);
			Document d = representation.getDocument();
			Element eltError = d.createElement("error");

			Element eltCode = d.createElement("code");
			eltCode.appendChild(d.createTextNode(errorCode));
			eltError.appendChild(eltCode);

			Element eltMessage = d.createElement("message");
			eltMessage.appendChild(d.createTextNode(errorMessage));
			eltError.appendChild(eltMessage);

			response.setEntity(new DomRepresentation(MediaType.TEXT_XML, d));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
