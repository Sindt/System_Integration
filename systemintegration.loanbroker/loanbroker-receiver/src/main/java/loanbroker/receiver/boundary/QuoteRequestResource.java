package loanbroker.receiver.boundary;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.JSONObject;

import loanbroker.receiver.controller.QouteRequestController;

@Path("quote")
@Produces(MediaType.APPLICATION_JSON)
public class QuoteRequestResource {

	private QouteRequestController controller = new QouteRequestController();

	@GET
	public Response getRunning() {
		JSONObject input = new JSONObject().put("Status", "Running...");
		return Response.status(Status.OK).entity(input).build();
	}

	@POST
	@Path("send")
	public Response getQuoteRequest(@QueryParam("ssn") String ssn, @QueryParam("amount") int amount,
			@QueryParam("duration") int duration) {

		JSONObject input = new JSONObject().put("ssn", ssn).put("loanAmount", amount).put("loanDuration", duration);
		return controller.sendMessageBasic(input).build();
	}

}
