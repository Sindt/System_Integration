package loanbroker.recipientlist.translator.boundary;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.JSONObject;

@Path("rcpt")
@Produces(MediaType.APPLICATION_JSON)
public class RcptStarter {

	MessageReceiver receiver = new MessageReceiver();

	@GET
	public Response getRunning() {
		receiver.onMessage();
		JSONObject input = new JSONObject().put("Status", "Credit Closing...");
		return Response.status(Status.OK).entity(input).build();
	}
}
